package 데이터베이스;

import java.security.PrivateKey;

public class BoardVO implements Comparable<BoardVO>{
    private String commentNo;
    private String nutrientsName;
    private String userId;
    private String content;
    private String userNick;
    private int good;
    private int bad;

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public String getCommentNo() {
        return commentNo;
    }

    public String getUserNick() {
        return userNick;
    }

    public String getNutrientsName() {
        return nutrientsName;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setNutrientsName(String nutrientsName) {
        this.nutrientsName = nutrientsName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public void setCommentNo(String commentNo) {
        this.commentNo = commentNo;
    }

    @Override
    public int compareTo(BoardVO o) {
        if (this.good < o.good) return -1;
        else return 1;
    }
}
