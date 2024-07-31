package kroryi.board.dto;

public class User {
    private int uid;
    private String userid;
    private String password;
    private String username;


   public User(){
       this.userid = "";
       this.password = "";
       this.username = "";
   }

   public User( String userid, String password, String username) {

       this.userid = userid;
       this.password = password;
       this.username = username;
   }



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserid() {

        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
