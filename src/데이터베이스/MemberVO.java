package 데이터베이스;

public class MemberVO {

    private String id;
    private String pw;
    private String name;
    private String nickName;
    private Integer jumin;

    public MemberVO(String id, String pw, String name, String nickName, Integer jumin) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickName = nickName;
        this.jumin = jumin;
    }

    public MemberVO() { ///

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getJumin() {
        return jumin;
    }

    public void setJumin(Integer jumin) {
        this.jumin = jumin;
    }
}
