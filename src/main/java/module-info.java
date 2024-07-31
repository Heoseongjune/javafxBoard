module kroryi.board {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.base;
    requires javafx.swing;


    opens kroryi.board to javafx.fxml;
    opens kroryi.board.controller to javafx.fxml;
    opens kroryi.board.dto to javafx.base;
    opens kroryi.board.util to javafx.fxml;
    exports kroryi.board;
}