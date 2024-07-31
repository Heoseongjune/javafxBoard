package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;
import kroryi.board.util.SceneUtil;

import java.io.IOException;

public class UpdateController {
    @FXML
    public TextField tfTitle;

    @FXML
    public TextField tfWriter;
    @FXML
    public TextField tfRegDate;
    @FXML
    public TextArea taContent;

    int boardNo;
    private BoardService boardService = new BoardServiceImpl();

    public void moveToUpdate(ActionEvent actionEvent) throws IOException {
        Board board = new Board(tfTitle.getText(), tfWriter.getText(), taContent.getText());
        System.out.println(board.toString());
        board.setNo(boardNo);
        int result = boardService.update(board);
        if (result > 0) {
            System.out.println("글 수정 완료.");
            SceneUtil.getInstance().switchScene(actionEvent, UI.LIST.getPath());
        }
    }

    public void read(int boardNo) {
        this.boardNo = boardNo;
        Board board = boardService.select(boardNo);
        System.out.println(board.getTitle());
        tfTitle.setText(board.getTitle());
        tfWriter.setText(board.getWriter());
        tfRegDate.setText(board.getRogDate());
        taContent.setText(board.getContent());
    }

    public void moveToPrev(ActionEvent actionEvent) {
    }

    public void moveToDelete(ActionEvent actionEvent) throws IOException {
        showAlert(actionEvent);
    }

    public void moveToNext(ActionEvent actionEvent) {
    }

    public void moveToList(ActionEvent actionEvent) throws IOException {
        SceneUtil.getInstance().switchScene(actionEvent, UI.LIST.getPath());

    }

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
}
