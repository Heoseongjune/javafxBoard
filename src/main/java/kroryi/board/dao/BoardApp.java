package kroryi.board.dao;

import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;

import java.sql.SQLException;

public class BoardApp {
    static BoardService boardService = new BoardServiceImpl();
    static BoardDAO boardDAO = new BoardDAO();
    static JDBConnection jdbc = new JDBConnection();
    public static void main(String[] args) {


        //목록보기

//        for (Board board : boardService.list()) {
//            System.out.println(board.getNo() + " " + board.getTitle() + " "
//                    + " " + board.getContent() + " " + board.getWriter());
//        }
        //게시글 등록.1
//        Board board = new Board();
//        board.setTitle("시험");
//        board.setWriter("답안");
//        board.setContent("작성용.0730_허성준");
//        boardService.insert(board);


//        //37번 게시글 수정.
//        String sql = "update Board set title = ?, writer = ?, content = ?,up_date=now() where no = ?";
//        int result = 0;
//        try {
//            jdbc.pstmt = jdbc.con.prepareStatement(sql);
//            jdbc.pstmt.setString(1,"시험게시물수정" );
//            jdbc.pstmt.setString(2, "허성준");
//            jdbc.pstmt.setString(3, "해당게시물의 내용을 변경합니다. 0730");
//            jdbc.pstmt.setInt(4, 37);
//
//            result = jdbc.pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("게시물 수정 시 에러발생");
//        }

        //37번 게시글 상세보기
//        System.out.println(boardService.select(37).toString());


        //37번 게시글 삭제.
        int result = 0;
        String sql = "delete from Board where no=?";
        try {
            jdbc.pstmt = jdbc.con.prepareStatement(sql);
            jdbc.pstmt.setInt(1, 37);
            result = jdbc.pstmt.executeUpdate();
            System.out.println("37번 게시글 삭제완료.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("삭제 실패");
        }

    }
}

