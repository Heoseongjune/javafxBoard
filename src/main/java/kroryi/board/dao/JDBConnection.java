package kroryi.board.dao;

import javafx.beans.property.SimpleStringProperty;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;

import java.sql.*;

public class JDBConnection {

    public Connection con;

    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;

    public JDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javafxBoard?useSSl=false&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "123456";

            con = DriverManager.getConnection(url, username, password);
            System.out.println("연결성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB연결 실패");
        }
    }

    public static void main(String[] args) {
//        JDBConnection jdbc = new JDBConnection();
//
//        BoardDAO boardDAO = new BoardDAO();
//        boardDAO.selectList();
//        System.out.println(boardDAO.select(1).toString());
//        Board board = new Board(new SimpleStringProperty("오늘은 뭐하니"), new SimpleStringProperty("유관순"), new SimpleStringProperty("날씨 좋다"));
//        boardDAO.insert(board);
//        boardDAO.selectList();

//        Board board = new Board(new SimpleStringProperty("오늘은 뭐하니"),
//                new SimpleStringProperty("유관순"),
//                new SimpleStringProperty("날씨 좋다"));
//        board.setNo(6);
//        boardDAO.update(board);
//        boardDAO.delete(7);

        BoardService boardService = new BoardServiceImpl();
//        System.out.println(boardService.list().toString());
    }
}
