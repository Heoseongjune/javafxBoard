package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;
import kroryi.board.util.SceneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadController {
    public TextField tfTitle;
    public TextField tfWriter;
    public TextField tfRegDate;
    public TextArea taContent;
    List<Integer> numArr = new ArrayList<>();
    int targetValue;
    int prevValue = -1;
    int nextValue = -1;

    private BoardService boardService = new BoardServiceImpl();
    int boardNo;

    public void read(int boardNo) {
        this.boardNo = boardNo;
        this.targetValue = boardNo;
        Board board = boardService.select(boardNo);
        tfTitle.setText(board.getTitle());
        tfWriter.setText(board.getWriter());
        tfRegDate.setText(board.getRogDate());
        taContent.setText(board.getContent());
    }

    //글 목록으로 가기
    public void moveToList(ActionEvent actionEvent) throws IOException {
        SceneUtil.getInstance().switchScene(actionEvent, UI.LIST.getPath());
    }

    //업데이트로 가기
    public void moveToUpdate(ActionEvent actionEvent) throws IOException {
        UpdateController updateController = (UpdateController) SceneUtil.getInstance().getController(UI.UPDATE.getPath());
        updateController.read(boardNo);
        Parent root = SceneUtil.getInstance().getRoot();
        SceneUtil.getInstance().switchScene(actionEvent, UI.UPDATE.getPath(), root);
    }

    //이전글 보기
    public void moveToPrev(ActionEvent actionEvent) {
        numArr = listNum();
        System.out.println(numArr);
        read(prevValue);
    }

    //다음 글 보기
    public void moveToNext(ActionEvent actionEvent) throws IOException {
        numArr = listNum();
        read(nextValue);
    }

    public void moveToDelete(ActionEvent actionEvent) throws IOException {
        showAlert(actionEvent);
    }
    //삭제 알람 띄우기.
    private void showAlert(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ㄹㅇㄹ?");
        alert.setHeaderText("진짜 지울거임? 글번호 : " + boardNo + "번 인데?");
        alert.setContentText("삭제 후 복구 안됨 ㅅㄱ");

        int result = 0;
        if (alert.showAndWait().get() == ButtonType.OK) {

            result = boardService.delete(boardNo);
        }
        if (result > 0) {
            System.out.println("삭제완료.");
            SceneUtil.getInstance().switchScene(event, UI.LIST.getPath());
        }
    }

    //게시글 boardNo 구하기
    public List<Integer> listNum() {
        List<Board> boardlist = new ArrayList<>();
        boardlist = boardService.list();
        numArr.clear();
        for (Board board : boardlist) {
            numArr.add(board.getNo());
        }

        targetValue = boardNo;

        int idx = numArr.indexOf(targetValue);
        if (idx > 0) {
            prevValue = numArr.get(idx - 1);
        }
        if (idx < numArr.size() - 1) {
            nextValue = numArr.get(idx + 1);
        }

        return numArr;
    }
}
