package 데이터베이스;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

public class SearchIdDAO {
    Connection conn =null;
    Statement stmt = null;
    ResultSet rs = null;
    public String searchId(String input_name, String input_jumin){
        String query = null;
        String output_id = null;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            query = "select USER_ID FROM MEMBER WHERE USER_NAME = '" +input_name+ "' and USER_JUMIN = '" + input_jumin + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                 output_id = rs.getString("USER_ID");
            }

            if (output_id == null) {
                System.out.println("입력정보가 잘못 되었습니다.");
                return output_id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return output_id;
    }

    public String searcPw(String input_id, String input_name, String input_jumin){
        String query = null;
        String output_id = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            query = "select USER_PW FROM MEMBER WHERE USER_ID = '" +input_id+ "' and USER_NAME = '" +input_name+ "' and USER_JUMIN = '" + input_jumin + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                 output_id = rs.getString("USER_PW");
            }
            if (output_id == null) {
                System.out.println("입력정보가 잘 못 되었습니다.");
                return output_id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return output_id;
    }
    public void printMemberId(String id) {
        System.out.println("=".repeat(6)+ "검색결과" + "=".repeat(6));
        System.out.println("아이디는 "+ id+ "입니다.");
    }
    public void printMemberPW(String pw) {
        System.out.println("=".repeat(6)+ "검색결과" + "=".repeat(6));
        System.out.println("비밀번는 "+ pw+ "입니다.");
    }
}
