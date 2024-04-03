package 데이터베이스;

public class test {
    public static void main(String[] args) {
        String jumin = "9506201234567"; // 010101

        // 성별 반환 코드
        String gender;
        gender = jumin.substring(6,7);
        String result;
        switch (gender) {
            case "1":
            case "3":
                result = "남자";
                break;
            case "2":
            case "4":
                result = "여자";
                break;
            default:
                result = "알 수 없음";
                break;
        }
        // 나이 반환 코드

        System.out.println();;

    }

}

