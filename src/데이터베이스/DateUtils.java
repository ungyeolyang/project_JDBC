package 데이터베이스;

import java.time.LocalDate;

public class DateUtils {

    public static int getAmericanAge(String jumin_input) {
        // 오늘 날짜
        LocalDate today = LocalDate.now();
        int todayYear = today.getYear();
        int todayMonth = today.getMonthValue();
        int todayDay = today.getDayOfMonth();

        // 주민등록번호를 통해 입력 받은 날짜
        int year = Integer.parseInt(jumin_input.substring(0,2));
        int month = Integer.parseInt(jumin_input.substring(2,4));
        int day = Integer.parseInt(jumin_input.substring(4,6));

        // 주민등록번호 뒷자리로 몇년대인지
        String gender = jumin_input.substring(6,7);
        if(gender.equals("1") || gender.equals("2")) {
            year += 1900;
        } else if(gender.equals("3") || gender.equals("4")) {
            year += 2000;
        } else if(gender.equals("0") || gender.equals("9")) {
            year += 1800;
        }

        // 올해 - 태어난년도
        int americanAge = todayYear - year;

        // 생일이 안지났으면 - 1
        if(month > todayMonth) {
            americanAge--;
        } else if(month == todayMonth) {
            if(day > todayDay) {
                americanAge--;
            }
        }

        return americanAge;
    }

    public static String gender_output (String juminBack) {
        // 주민등록번호 뒷자리로 성별출력
        String gender = juminBack.substring(6, 7);
        String gender_output;
        if (gender.equals("1") || gender.equals("3")) {
            return gender_output = "남자";
        } else if (gender.equals("2") || gender.equals("4")) {
            return gender_output = "여자";
        }
        return gender_output = "알 수 없음";

    }
}