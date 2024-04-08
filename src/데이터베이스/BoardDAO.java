package 데이터베이스;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class BoardDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public boolean checkMine(String str) {
        String query = "SELECT USER_ID FROM BOARD WHERE NUTRIENTS_NAME = '" + str + "'";
        String mine = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                mine = rs.getString("USER_ID");
            }
            if (mine == null) return true;
            else if (mine.equals(Main.myId)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    public boolean checkComment(String str) {
        String query = "SELECT USER_ID FROM BOARD WHERE NUTRIENTS_NAME = '" + str + "'";
        String mine = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                mine = rs.getString("USER_ID");
            }
            if (mine == null) {
                System.out.println("댓글이 존재하지 않습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    public boolean checkCommentGood(int num) {
        List<String> list = new ArrayList<>();
        String str = null;
        String query = "SELECT * FROM GOODBOARD WHERE USER_ID = '" + Main.myId + "' AND COMMENT_NO = '" + num + "'";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            if (rs.next()) { // 추천한 댓글이 존재
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    public boolean checkComment(int num, String str) {
        String query = "SELECT USER_ID FROM BOARD WHERE NUTRIENTS_NAME = '" + str + "' AND COMMENT_NO = '" + num + "'";
        String mine = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                mine = rs.getString("USER_ID");
            }
            if (mine == null) {
                System.out.println("존재하지 않는 댓글입니다.");
                return false;
            } else if (mine.equals(Main.myId)) {
                System.out.println("본인의 댓글입니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    public void commentGood(int num) {
        String query = "INSERT INTO GOODBOARD VALUES(" + num + ",'" + Main.myId + "')";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
        System.out.println("추천이 완료되었습니다.");
    }

    public boolean checkCommentBad(int num) {
        String query = "SELECT * FROM BADBOARD WHERE USER_ID = '" + Main.myId + "' AND COMMENT_NO = '" + num + "'";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            if (rs.next()) { // 비추천한 댓글 존재
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    public int checkGood(int num) {
        int good = 0;
        String query = "SELECT COUNT(COMMENT_NO) AS COUNT FROM GOODBOARD " +
                " WHERE COMMENT_NO =" + num;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                good = rs.getInt("COUNT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return good;
    }

    public void updateGoodBoard(int cnum, int num) {
        String query = "UPDATE BOARD SET GOOD =" + num + " WHERE COMMENT_NO = " + cnum;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }

    public void commentBad(int num) {
        String query = "INSERT INTO BADBOARD VALUES(" + num + ",'" + Main.myId + "')";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
        System.out.println("비추천이 완료되었습니다.");
    }

    public int checkBad(int num) {
        int bad = 0;
        String query = "SELECT COUNT(COMMENT_NO) AS COUNT FROM BADBOARD " +
                " WHERE COMMENT_NO =" + num;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                bad = rs.getInt("COUNT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return bad;
    }

    public void updateBadBoard(int cnum, int num) {
        String query = "UPDATE BOARD SET BAD =" + num + " WHERE COMMENT_NO = " + cnum;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }

    public void deleteBad(int num) {
        String query = "DELETE FROM BADBOARD WHERE COMMENT_NO =" + num;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }

    public void deleteGood(int num) {
        String query = "DELETE FROM GOODBOARD WHERE COMMENT_NO =" + num;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }
    public void deleteMyGood(int num) {
        String query = "DELETE FROM GOODBOARD WHERE COMMENT_NO =" + num + "AND USER_ID = '"+ Main.myId+"'";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }
    public void deleteMyBad(int num) {
        String query = "DELETE FROM BABOARD WHERE COMMENT_NO =" + num + "AND USER_ID = '"+ Main.myId+"'";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }

    public void deleteBadAll() {
        String query = "DELETE FROM BADBOARD WHERE USER_ID = '" + Main.myId + "'";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }

    public void deleteGoodAll() {
        String query = "DELETE FROM GOODBOARD WHERE USER_ID = '" + Main.myId + "'";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }

    public String checkNut(String name) {
        List<String> list = new ArrayList<>();
        String str = null;
        String query = "SELECT NUTRIENTS_NAME FROM NUTRIENTS WHERE NUTRIENTS_NAME LIKE '%" + name + "%'";
        int count = 0;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("NUTRIENTS_NAME"));
                count++;
            }
            if (list.isEmpty()) {
                System.out.println("검색결과가 존재하지 않습니다.");
                return str;
            } else if (list.size() > 1) {
                System.out.println("검색결과가 2개 이상 존재합니다. 정확한 이름을 입력해 주세요");
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return str = list.toString();
    }

    public NutrientsVO boardNut(String str) {
        NutrientsVO vo = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT * FROM NUTRIENTS WHERE NUTRIENTS_NAME =" + "'" + str + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                vo = new NutrientsVO();

                vo.setNutrientsName(rs.getString("NUTRIENTS_NAME"));
                vo.setIngredientA(rs.getString("INGREDIENT_A"));
                vo.setIngredientB(rs.getString("INGREDIENT_B"));
                vo.setCompany(rs.getString("COMPANY"));
                vo.setHowToTake(rs.getString("HOW_TO_TAKE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return vo;
    }

    public HashSet<String> boardEFF(String str) {
        HashSet<String> set = new HashSet<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query1 = "SELECT EFFICACY_A ,EFFICACY_B ,EFFICACY_C " +
                    "FROM NUTRIENTS n JOIN INGREDIENT i ON i.INGREDIENT_NAME = n.INGREDIENT_A OR i.INGREDIENT_NAME = n.INGREDIENT_B " +
                    "WHERE NUTRIENTS_NAME LIKE '%" + str + "'";
            rs = stmt.executeQuery(query1);
            while (rs.next()) {
                set.add(rs.getString("EFFICACY_A"));
                set.add(rs.getString("EFFICACY_B"));
                set.add(rs.getString("EFFICACY_C"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return set;
    }

    public TreeSet<BoardVO> boardList(String str) {
        TreeSet<BoardVO> set = new TreeSet<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT COMMENT_NO,USER_NICK,RPAD(SUBSTR(USER_ID,1,3),LENGTH (USER_ID),'*') AS \"USER_ID\",CONTENT,GOOD,BAD " +
                    "FROM BOARD WHERE NUTRIENTS_NAME = '" + str + "'";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                BoardVO voB = new BoardVO();
                String num = rs.getString("COMMENT_NO");
                String nick = rs.getString("USER_NICK");
                String id = rs.getString("USER_ID");
                String content = rs.getString("CONTENT");
                int good = rs.getInt("GOOD");
                int bad = rs.getInt("BAD");

                voB.setCommentNo(num);
                voB.setUserNick(nick);
                voB.setUserId(id);
                voB.setContent(content);
                voB.setGood(good);
                voB.setBad(bad);

                set.add(voB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return set;
    }
    public List<BoardVO> goodBoardList() {
        List<BoardVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT NUTRIENTS_NAME,COMMENT_NO,USER_NICK,RPAD(SUBSTR(USER_ID,1,3),LENGTH (USER_ID),'*') AS \"USER_ID\",CONTENT,GOOD,BAD " +
                    "FROM BOARD WHERE COMMENT_NO IN(SELECT COMMENT_NO FROM GOODBOARD WHERE USER_ID = '" + Main.myId + "')";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                BoardVO voB = new BoardVO();
                String name = rs. getString("NUTRIENTS_NAME");
                String num = rs.getString("COMMENT_NO");
                String nick = rs.getString("USER_NICK");
                String id = rs.getString("USER_ID");
                String content = rs.getString("CONTENT");
                int good = rs.getInt("GOOD");
                int bad = rs.getInt("BAD");

                voB.setNutrientsName(name);
                voB.setCommentNo(num);
                voB.setUserNick(nick);
                voB.setUserId(id);
                voB.setContent(content);
                voB.setGood(good);
                voB.setBad(bad);

                list.add(voB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return list;
    }
    public List<BoardVO> badBoardList() {
        List<BoardVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT NUTRIENTS_NAME,COMMENT_NO,USER_NICK,RPAD(SUBSTR(USER_ID,1,3),LENGTH (USER_ID),'*') AS \"USER_ID\",CONTENT,GOOD,BAD " +
                    "FROM BOARD WHERE COMMENT_NO IN(SELECT COMMENT_NO FROM BADBOARD WHERE USER_ID = '" + Main.myId + "')";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                BoardVO voB = new BoardVO();
                String name = rs. getString("NUTRIENTS_NAME");
                String num = rs.getString("COMMENT_NO");
                String nick = rs.getString("USER_NICK");
                String id = rs.getString("USER_ID");
                String content = rs.getString("CONTENT");
                int good = rs.getInt("GOOD");
                int bad = rs.getInt("BAD");

                voB.setNutrientsName(name);
                voB.setCommentNo(num);
                voB.setUserNick(nick);
                voB.setUserId(id);
                voB.setContent(content);
                voB.setGood(good);
                voB.setBad(bad);

                list.add(voB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return list;
    }

    public boolean searchBoard(String str) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query1 = "SELECT * FROM BOARD WHERE USER_ID = '" + Main.myId + "' AND NUTRIENTS_NAME = '" + str + "'";
            rs = stmt.executeQuery(query1);
            if (rs.next()) return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    public List<BoardVO> searchBoard() {
        List<BoardVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query1 = "SELECT * FROM BOARD WHERE USER_ID = '" + Main.myId + "'";
            rs = stmt.executeQuery(query1);

            while (rs.next()) {
                String commentNo = rs.getString("COMMENT_NO");
                String nutrientsName = rs.getString("NUTRIENTS_NAME");
                String content = rs.getString("CONTENT");
                int good = rs.getInt("GOOD");
                int bad = rs.getInt("BAD");

                BoardVO vo = new BoardVO();
                vo.setCommentNo(commentNo);
                vo.setNutrientsName(nutrientsName);
                vo.setContent(content);
                vo.setGood(good);
                vo.setBad(bad);

                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return list;
    }

    public void comment(NutrientsVO vo, String content) {
        String query = null;
        query = "INSERT INTO BOARD VALUES (SEQ_COMMENT.NEXTVAL, '"
                + vo.getNutrientsName() + "', '" + Main.myId + "' , '" +
                Main.myNickName + "' , '" + content + "', 0 , 0)";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public boolean checkMyContent(int num) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT * FROM BOARD WHERE USER_ID = '" + Main.myId + "' AND COMMENT_NO = '" + num + "'";
            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }

    void updateContent(int num, String content) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "UPDATE BOARD SET CONTENT = " + "'" + content + "' "
                    + "WHERE COMMENT_NO = '" + num + "'";

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    void deleteContent(int num) {
        Scanner sc = new Scanner(System.in);
        String query1;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            query1 = "DELETE FROM BOARD WHERE COMMENT_NO = '" + num + "'";

            int ret = stmt.executeUpdate(query1);
            System.out.println("Return : " + ret);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    void printBoard(NutrientsVO vo, HashSet<String> set, TreeSet<BoardVO> set1) {
        System.out.println(vo.getNutrientsName());
        System.out.println("=".repeat(10));
        if (vo.getIngredientB() != null) System.out.println("성분 : " + vo.getIngredientA() + ", " + vo.getIngredientB());
        else System.out.println("성분 : " + vo.getIngredientA());
        System.out.println("효능 : " + set.toString().replace("[", "").replace("]", ""));
        System.out.println("제조사 : " + vo.getCompany());
        System.out.println("복용법 : " + vo.getHowToTake() + " ");
        if (!set.isEmpty()) {
            for (BoardVO e : set1) {
                System.out.println("=".repeat(30));
                System.out.println(e.getCommentNo() + " / " + e.getUserNick() + "(" + e.getUserId() + ") : " + e.getContent() + " / 좋아요 : " + e.getGood() + " / 싫어요 : " + e.getBad());
            }
        }
    }

    void printMyContent(List<BoardVO> list) {
        for (BoardVO e : list) {
            System.out.println("=".repeat(30));
            System.out.println(e.getCommentNo() + " / " + e.getNutrientsName() + " : " + e.getContent() + " / 좋아요 : " + e.getGood() + " / 싫어요 : " + e.getBad());
        }
    }

    void printMyGoodContent (List < BoardVO > list) {
            for (BoardVO e : list) {
                System.out.println("=".repeat(30));
                System.out.println(e.getCommentNo() + " / " + e.getNutrientsName() + " / " + e.getUserNick() + "(" + e.getUserId() + ") : " + e.getContent() + " / 좋아요 : " + e.getGood() + " / 싫어요 : " + e.getBad());
            }
    }
    void printMyBadContent (List < BoardVO > list) {
            for (BoardVO e : list) {
                System.out.println("=".repeat(30));
                System.out.println(e.getCommentNo() + " / " + e.getNutrientsName() + " / " + e.getUserNick() + "(" + e.getUserId() + ") : " + e.getContent() + " / 좋아요 : " + e.getGood() + " / 싫어요 : " + e.getBad());
            }
    }

}