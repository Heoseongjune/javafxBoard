package kroryi.board.controller;

public enum UI {

    LIST("/kroryi/board/BoardList-view.fxml"),
    INSERT("/kroryi/board/INSERT.fxml"),
    READ("/kroryi/board/READ.fxml"),
    UPDATE("/kroryi/board/UPDATE.fxml"),
    REGISTER("/kroryi/board/Register.fxml"),
    LOGIN("/kroryi/board/LOGIN.fxml");

    private final String path;

    UI(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
