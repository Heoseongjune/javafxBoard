package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kroryi.board.dto.User;
import kroryi.board.service.UserService;
import kroryi.board.service.UserServiceImpl;
import kroryi.board.util.SceneUtil;

import java.io.IOException;

public class RegisterController {
    @FXML
    public TextField tfid;
    @FXML
    public TextField tfusername;
    @FXML
    public PasswordField pfpassword;
    @FXML
    public PasswordField pfrepassword;

    private UserService userService = new UserServiceImpl();
    public void register(ActionEvent actionEvent) throws IOException {
        String userid = tfid.getText();
        String username = tfusername.getText();
        String password = pfpassword.getText();
        String repassword = pfrepassword.getText();
        if(userid == null || userid.trim().isEmpty()){
            showAlert("아이디를 입력하시오. ");
            return;
        }
        if(username == null || username.trim().isEmpty()){
            showAlert("이름을 입력하시오");
            return;
        }
        if(password == null || password.trim().isEmpty()){
            showAlert("비밀번호를 입력하시오");
            return;
        }
        if(repassword == null || repassword.trim().isEmpty()){
            showAlert("비밀번호를 재입력하시오");
            return;
        }
        if(!password.trim().equals(repassword.trim())){
            showAlert("비밀번호가 일치하지 않습니다.");
            return;
        } else{
            User user = new User(userid, username, password);
            int result = userService.insert(user);
            if(result >0){
                showAlert("회원가입 성공");
                SceneUtil.getInstance().switchScene(actionEvent,UI.LOGIN.getPath());
            } else{
                showAlert("회원가입에 실패.");
            }
        }

    }

    public void goToLogin(ActionEvent actionEvent) {

    }

    private void showAlert(String messege){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("입력오류");
        alert.setHeaderText(null);
        alert.setContentText(messege);
        alert.showAndWait();
    }
}
