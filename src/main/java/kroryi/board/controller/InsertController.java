package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;
import kroryi.board.util.SceneUtil;

import java.io.IOException;
import java.sql.SQLException;

public class InsertController {
    public TextField tfTitle;
    public TextField tfWriter;
    public TextArea taContent;
    Stage stage;
    Scene scene;
    Parent root;
    BoardService boardService = new BoardServiceImpl();




    public void insert(ActionEvent actionEvent) throws IOException {

      String title = tfTitle.getText();
      String writer = tfWriter.getText();
      String content = taContent.getText();
      if(title == null || title.trim().isEmpty()){
          showAlert("제목을 입력하시오.");
          return;
      }
        if(writer == null || writer.trim().isEmpty()){
            showAlert("작성자를 입력하시오.");
            return;
        }
        if(content == null || content.trim().isEmpty()){
            showAlert("내용을 입력하시오.");
            return;
        }
        Board board = new Board(tfTitle.getText(),tfWriter.getText(),taContent.getText());
        int result = boardService.insert(board);
        if(result >0) {
            System.out.println("글쓰기 성공");
            SceneUtil.getInstance().switchScene(actionEvent,UI.LIST.getPath());
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(UI.LIST.getPath()));
//            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            root = loader.load();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();

        }else{
            showAlert("글 저장에 문제가 있습니다.");
        }
    }

    public void moveToList(ActionEvent actionEvent) throws IOException {
            SceneUtil.getInstance().switchScene(actionEvent,UI.LIST.getPath());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(UI.LIST.getPath()));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        root = loader.load();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    private void showAlert(String messege){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("입력오류");
        alert.setHeaderText(null);
        alert.setContentText(messege);
        alert.showAndWait();
    }

}
