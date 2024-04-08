package 데이터베이스;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyInfoDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public MemberVO myInfo() {
        MemberVO vo = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT RPAD(SUBSTR(USER_PW,1,3),LENGTH (USER_PW),'*') AS \"USER_PW\",USER_NAME,USER_NICK, USER_JUMIN FROM MEMBER WHERE USER_ID = '" + Main.myId + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("USER_NAME");
                String pw = rs.getString("USER_PW");
                String nickName = rs.getString("USER_NICK");
                String jumin = rs.getString("USER_JUMIN");

                vo = new MemberVO();
                vo.setName(name);
                vo.setPw(pw);
                vo.setNickName(nickName);
                vo.setJumin(jumin);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    void updateMyInfo(int sel,String data) {
        String query = null;
        switch (sel) {
            case 1:
                query = "UPDATE MEMBER "
                        + " SET USER_NAME = " + "'" + data + "'"
                        + " WHERE USER_ID = " + "'" + Main.myId + "'";
                break;
            case 2:
                query = "UPDATE MEMBER "
                        + " SET USER_PW = " + "'" + data + "'"
                        + " WHERE USER_ID = " + "'" + Main.myId + "'";
                break;

            case 3:
                query = "UPDATE MEMBER "
                        + " SET USER_NICK = " + "'" + data + "'"
                        + " WHERE USER_ID = " + "'" + Main.myId + "'";
                Main.myNickName = data;
                break;
        }
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
    void deleteMyInfo() {
        BoardDAO board = new BoardDAO();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "DELETE FROM MEMBER WHERE USER_ID = '" + Main.myId + "'";
            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
            String query1 = "DELETE FROM BOARD WHERE USER_ID = '" + Main.myId + "'";
            int ret1 = stmt.executeUpdate(query1);
            System.out.println("Return : " + ret1);
            board.deleteBadAll();
            board.deleteGoodAll();
            board.deleteWishALL();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
    void printMyInfo(MemberVO vo) {
        System.out.println(Main.myNickName +"님의 정보");
        System.out.println("=".repeat(10));
        System.out.println("이름 : " + vo.getName());
        System.out.println("비밀번호 : " + vo.getPw() + " ");
        System.out.println("닉네임 : " + vo.getNickName() + " ");
        System.out.println("주민등록번호 : " + vo.getJumin().substring(0,6) + "-" + vo.getJumin().charAt(6) + "*".repeat(6));// 뒷 4자리 블라인드
        System.out.println("나이 : "+ DateUtils.getAmericanAge(vo.getJumin())+ " "); // 주민번호앞자리기준 나이환산
        System.out.println("성별 : "+ DateUtils.gender_output(vo.getJumin())+ " "); // 주민번호뒷자리 기준 성별반환
    }
}
