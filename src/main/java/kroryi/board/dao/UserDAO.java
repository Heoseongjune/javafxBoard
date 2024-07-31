package kroryi.board.dao;

import kroryi.board.dto.User;

import java.sql.SQLException;

public class UserDAO extends JDBConnection {

    public User select(String userid) {
        User user = new User();
        String sql = "select * from User where userid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("userpassword"));

            } else{
                System.out.println(userid+"사용자가 없습니다.");
                userid = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("사용자 조회 실패");
        }
        return user;
    }

    public int insert(User user) {
        int result = 0;
        String sql = "INSERT into User(userid, username, userpassword) values(?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserid());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            result = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("사용자 db 등록 실패.");
        }
        return result;
    }

    public int update(User user) {
        return 0;
    }
    public int delete(String userid) {
        return 0;
    }
}
