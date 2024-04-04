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
    public TreeSet<MemberVO> searcId(int num, String input_name, String input_jumin){
        String query = null;
        TreeSet<MemberVO> memberSet = new TreeSet<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            switch (num){
                case 1:
                    query = "select USER_ID FROM MEMBER WHERE USER_NAME = '" +input_name+ "' and USER_JUMIN = '" + input_jumin + "'";
                    break;
            }
            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                System.out.println("입력정보가 잘못 되었습니다.");
                return memberSet;
            }

            while (rs.next()) {
                String output_id = rs.getString("USER_ID");

                MemberVO vo = new MemberVO();
                vo.setId(output_id);

                memberSet.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return memberSet;
    }
    public TreeSet<MemberVO> searcPw(int num, String input_id, String input_name, String input_jumin){
        String query = null;
        TreeSet<MemberVO> memberSet = new TreeSet<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            switch (num){
                case 2:
                    query = "select USER_ID FROM MEMBER WHERE USER_ID = '" +input_id+ "' and USER_NAME = '" +input_name+ "' and USER_JUMIN = '" + input_jumin + "'";
                    break;
            }
            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                System.out.println("입력정보가 잘 못 되었습니다.");
                return memberSet;
            }

            while (rs.next()) {
                String output_id = rs.getString("USER_ID");

                MemberVO vo = new MemberVO();
                vo.setId(output_id);

                memberSet.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return memberSet;
    }
    void printMemberId(TreeSet<MemberVO> memberVO) {
        System.out.println("======검색결과======");
        System.out.println("아이디는 "+ memberVO.first().getId()+ "입니다.");
    }
}
