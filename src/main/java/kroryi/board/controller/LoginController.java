package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kroryi.board.Controller;
import kroryi.board.dto.User;
import kroryi.board.service.UserService;
import kroryi.board.service.UserServiceImpl;
import kroryi.board.util.CommonData;
import kroryi.board.util.SceneUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    @FXML
    private TextField tfid;

    @FXML
    private PasswordField tfpw;

    private UserService userService = new UserServiceImpl();
    private String userdata;

    public void login(ActionEvent event) throws IOException {
        if (tfid.getText().isEmpty() || tfpw.getText().isEmpty()) {
            showAlert(ErrorCode.LOGIN_INPUT_ERROR, null, Alert.AlertType.ERROR);
            return;
        } else {
            User user = userService.select((tfid.getText()));
//            System.out.println(user.getUserid());

            if (user.getUserid().isEmpty()) {
                showAlert(ErrorCode.USER_NOT_FOUND, null, Alert.AlertType.ERROR);
            } else {
                //추후 암호가 추가되어야함.
                if (!user.getPassword().equals(tfpw.getText())) {
                    showAlert(ErrorCode.LOGIN_FAILED, null, Alert.AlertType.ERROR);
                } else {
//                    System.out.println(tfid.getText());
                    userdata = tfid.getText();

                    showAlert(ErrorCode.LOGIN_SUCCESS, user.getUserid(), Alert.AlertType.INFORMATION);
                    CommonData.setUserid(user.getUserid());
                    Controller controller = (Controller) SceneUtil.getInstance().getController(UI.LIST.getPath());
                    Parent root = SceneUtil.getInstance().getRoot();
                    SceneUtil.getInstance().switchScene(event, "", root);
                }
            }
        }
    }



    private Alert alert = new Alert(Alert.AlertType.WARNING);

    public void showAlert(String title, String header, String content) {

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showAlert(ErrorCode errorCode, String parameter, Alert.AlertType alertType) {
        alert = new Alert(alertType);
        String message = StringResource.getErrorMessage(errorCode);
        if (parameter != null) {
            message = String.format(message, parameter);
        }
        showAlert("로그인" + (alertType == Alert.AlertType.ERROR ? "오류 " : "알림"), null, message);
    }

    public void goToRegister(ActionEvent actionEvent) throws IOException {
        RegisterController registerController = (RegisterController) SceneUtil.getInstance().getController(UI.REGISTER.getPath());
        Parent root = SceneUtil.getInstance().getRoot();
        SceneUtil.getInstance().switchScene(actionEvent, UI.REGISTER.getPath(), root);
    }

    public class StringResource {
        private static final Map<ErrorCode, String> errorMessages = new HashMap<>();

        static {
            errorMessages.put(ErrorCode.LOGIN_INPUT_ERROR, "id 또는 password를 입력하지 않았습니다.");
            errorMessages.put(ErrorCode.USER_NOT_FOUND, "사용자가 없습니다.");
            errorMessages.put(ErrorCode.LOGIN_FAILED, "비밀번호가 일치하지 않습니다.\n 다시 확인하시기 바랍니다.");
            errorMessages.put(ErrorCode.LOGIN_SUCCESS, "&s 님 환영합니다. \n 프로그램에 접속합니다.");
        }

        public static String getErrorMessage(ErrorCode errorCode) {
            return errorMessages.get(errorCode);
        }
    }

    public enum ErrorCode {
        LOGIN_INPUT_ERROR,
        USER_NOT_FOUND,
        LOGIN_FAILED,
        LOGIN_SUCCESS;
    }

}
