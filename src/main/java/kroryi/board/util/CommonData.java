package kroryi.board.util;

public class CommonData {
    private static String userid;

    public static String getUserid() {
        return CommonData.userid;
    }

    public static void setUserid(String user_id) {
        CommonData.userid = user_id;
    }
}
