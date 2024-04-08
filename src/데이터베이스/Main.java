package 데이터베이스;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Scanner;

public class Main {
    static String id;
    static String pw;
    static String myId;
    static String myNickName;
    static String myJumin;

    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        String title = "영양제 추천 사이트";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("┌" + "- ".repeat(title.length()) + "-" + "┐");
            System.out.println("│   " + title + "   │");
            System.out.println("└" + "- ".repeat(title.length()) + "-" + "┘");
            System.out.println("[1]로그인 [2]회원가입 [3]회원정보 찾기 [4]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1: // 로그인 입력 단
                    LogInDAO logIn = new LogInDAO();
                    System.out.println("로그인을 위해 아이디와 비밀번호를 입력 하세요.");
                    System.out.print("아이디 : ");
                    id = sc.next().trim();
                    System.out.print("비밀번호 : ");
                    pw = sc.next().trim();
                    if (!logIn.logIn(id, pw)) continue;
                    myId = id;
                    myNickName = LogInDAO.nickName; //
                    break;
                case 2: // 회원가입 입력 단
                    RegisterDAO register = new RegisterDAO();
                    System.out.println("회원가입 정보를 입력 하세요.");
                    System.out.print("이름 : ");
                    String name = sc.next().trim();
                    String input_jumin1; //위치 검토필요
                    String  input_id;
                    String input_pw;
                    while (true) {
                        System.out.print("주민등록번호 : ");
                        input_jumin1 = sc.next().trim();
                        myJumin = input_jumin1;

                        if (!register.checkJumin(input_jumin1)) continue;
                        break;
                    }
                    while (true) {
                        System.out.print("아이디 : ");
                        input_id = sc.next().trim();
                        id= input_id;
                        if (!register.checkId(input_id)) continue;
                        break;
                    }
                    while (true) {
                        System.out.print("비밀번호 : ");
                        input_pw = sc.next().trim();
                        pw = input_pw;
                        if (input_pw.length() <= 3) {
                            System.out.println("비밀번호는 4자 이상이어야 합니다.");
                            continue;
                        }
                        break;
                    }
                    System.out.print("닉네임 : ");
                    String nickName = sc.next().trim();
                    register.memberInsert(input_id, input_pw, name, nickName, input_jumin1); //rsgister id, pw 변수를 main static id, pw 전역 변수를 인식하는 오류발생으로 id,pw 매개변수명 변경하였습니다.
                    break;
                case 3: // 회원정보 찾기 입력단
                    SearchIdDAO search = new SearchIdDAO();
                    while (true){
                        System.out.println("[1]아이디 찾기 [2]비밀번호찾기 [3]뒤로가기");
                        int selectmenu= sc.nextInt();
                        switch (selectmenu){
                            case 1:
                                System.out.println("가입 시 등록한 이름을 입력 하세요 : ");
                                String input_name = sc.next().trim();
                                System.out.println("가입 시 등록한 주민등록번호를 입력하세요 :");
                                String input_jumin2 = sc.next().trim();
                                if(search.searchId(input_name,input_jumin2) == null) continue;
                                search.printMemberId(search.searchId(input_name,input_jumin2));
                                break;
                            case 2:
                                System.out.println("가입 시 등록한 아이디를 입력하세요 : ");
                                String input_id2 = sc.next().trim();
                                System.out.println("가입 시 등록한 이름을 입력 하세요 : ");
                                String input_name2 = sc.next().trim();
                                System.out.println("가입 시 등록한 주민등록번호를 입력하세요 :");
                                String input_jumin3 = sc.next().trim(); // 변수명 검토 필요
                                if(search.searcPw(input_id2,input_name2,input_jumin3) == null) continue;
                                search.printMemberPW(search.searcPw(input_id2,input_name2,input_jumin3));
                                break;
                            case 3:
                                break;
                        }
                        break;
                    }
                    break;
                case 4:
                    System.out.println("메뉴를 종료 합니다");
                    return;
                default:
                    break;
            }
            
            if (myId != null) {
                mainSelect();
            }
        }
    }
    public static void mainSelect() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("┌" + "- ".repeat(7) + "-" + "┐");
            System.out.println("│    메인페이지   │");
            System.out.println("└" + "- ".repeat(7) + "-" + "┘");
            System.out.println("[1]마이페이지 [2]검색 [3]로그아웃");
            int sel1 = sc.nextInt();
            switch (sel1) {
                case 1: if(!myPageSelect()) return;
                    break;
                case 2:
                    SearchDAO search = new SearchDAO();
                    BoardDAO board = new BoardDAO();
                    TreeSet<NutrientsVO> set = null;
                    int sel2;
                    while (true) {
                        System.out.println("[1]성분 [2]효능 [3]돌아가기");
                        sel2 = sc.nextInt();
                        switch (sel2) {
                            case 1:
                                System.out.print("성분을 입력하세요 : ");
                                String ingredent = sc.next().trim();
                                set = search.search(sel2,ingredent);
                                if(set.isEmpty()) continue;
                                break;
                            case 2:
                                System.out.print("효능을 입력하세요 : ");
                                String efficacy = sc.next().trim();
                                set = search.search(sel2,efficacy);
                                if(set.isEmpty()) continue;
                            case 3:
                                break;
                            default:
                        }
                        break;
                    }
                    if(set == null) break;
                    search.printNutrients(set);
                    String str = null;
                    while (true) {
                        System.out.print("영양제 이름을 입력하세요 : ");
                        String name1 = sc.nextLine().trim();
                        if (name1.isEmpty()) {System.out.println("이름을 입력하세요.");continue;}
                        str = board.checkNut(name1);
                        if(str == null) continue;
                        str = str.replace("[","").replace("]","");
                        break;
                    }
                    NutrientsVO voN = board.boardNut(str);
                    HashSet<String> set1 = board.boardEFF(str);
                    TreeSet<BoardVO> set2= board.boardList(str);
                    board.printBoard(voN,set1,set2);
                    while (true) {
                        System.out.println("[1]댓글쓰기 [2]추천하기 [3] 비추천하기 [4]찜하기 [5]돌아가기");
                            int sel3 = sc.nextInt();
                            sc.nextLine();
                            switch (sel3) {
                                case 1:
                                    if(!board.checkMine(str)) {
                                        System.out.println("이미 작성한 댓글입니다.");
                                        continue;
                                    }
                                    while (true) {
                                        System.out.print("댓글 : ");
                                        String content = sc.nextLine().trim();
                                        if (content.isEmpty()) {
                                            System.out.println("내용을 입력하세요.");
                                            continue;
                                        }
                                        board.comment(voN, content);
                                        break;
                                    }
                                    break;
                                case 2:
                                    if(!board.checkComment(str)) continue;
                                    System.out.print("추천할 번호를 입력하세요 : ");
                                    int goodnum =sc.nextInt();
                                    if(!board.checkCommentGood(goodnum)) {System.out.println("이미 추천한 댓글입니다."); continue;}
                                    else if(!board.checkMine(goodnum)) continue;
                                    else board.commentGood(goodnum);
                                    board.updateGoodBoard(goodnum,board.checkGood(goodnum));
                                    break;
                                case 3:
                                    if(!board.checkComment(str)) continue;
                                    System.out.print("비추천할 번호를 입력하세요 : ");
                                    int badnum =sc.nextInt();
                                    if(!board.checkCommentBad(badnum)) {
                                        System.out.println("이미 비추천한 댓글입니다.");
                                        continue;
                                    }
                                    else if(!board.checkMine(badnum)) continue;
                                    else board.commentBad(badnum);
                                    board.updateBadBoard(badnum,board.checkBad(badnum));
                                    if(board.checkBad(badnum) > 2) {
                                        board.deleteBad(badnum);
                                        board.deleteGood(badnum);
                                        System.out.println("비추천 누적으로 댓글이 삭제되었습니다.");
                                        board.deleteContent(badnum);
                                    } // 신고 3번이면 삭제
                                    break;
                                case 4:
                                    if(board.checkWish(str) != 0) {
                                        System.out.println("이미 찜한 상품입니다.");
                                        continue;
                                    }
                                    else board.wishIn(str);
                                    break;
                                case 5:
                                    break;
                                default:
                            }
                        break;
                    }
                    break;
                case 3:
                    System.out.println("로그아웃 합니다");
                    return;
                default:
            }
        }
    }
    public static boolean myPageSelect(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("┌" + "- ".repeat(myNickName.length() + 7) + "-" + "┐");
            System.out.println("│    " + myNickName + "님의 페이지   │");
            System.out.println("└" + "- ".repeat(myNickName.length() + 7) + "-" + "┘");
            System.out.println("[1]내정보 [2]작성한 댓글 [3]찜목록 [4]돌아가기");
            int sel4 = sc.nextInt();
            switch (sel4) {
                case 1: // 내정보
                    MyInfoDAO myInfo = new MyInfoDAO();
                    MemberVO vo = myInfo.myInfo();
                    myInfo.printMyInfo(vo);
                    while (true) {
                        System.out.println("[1]수정 [2]회원탈퇴 [3]돌아가기");
                        int sel5 = sc.nextInt();
                        switch (sel5) {
                            case 1: // 수정
                                System.out.println("[1]이름 [2]비밀번호 [3]닉네임 [4]돌아가기");
                                int sel6 = sc.nextInt();
                                switch (sel6) {
                                    case 1:
                                        System.out.print("이름 : ");
                                        String name2 = sc.next().trim();
                                        myInfo.updateMyInfo(sel6, name2);
                                        break;
                                    case 2:
                                        while (true) {
                                            System.out.print("비밀번호 : ");
                                            String pw = sc.next().trim();
                                            if (pw.length() <= 3) {
                                                System.out.println("비밀번호는 4자 이상이어야 합니다.");
                                                continue;
                                            }
                                            myInfo.updateMyInfo(sel6, pw);
                                            break;
                                        }
                                        break;
                                    case 3:
                                        System.out.print("닉네임 : ");
                                        String nickName = sc.next().trim();
                                        myInfo.updateMyInfo(sel6, nickName);
                                        Main.myNickName = nickName;
                                        break;
                                    case 4:
                                        break;
                                }
                                break;
                            case 2: // 탈퇴
                                System.out.println("정말 회원탈퇴 하시겠습니까?");
                                System.out.println("[1]회원탈퇴 [2]돌아가기");
                                int sel7 = sc.nextInt();
                                switch (sel7) {
                                    case 1: myInfo.deleteMyInfo();
                                        Main.myId = null;
                                        Main.myNickName = null;
                                        return false;
                                    case 2:
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3: // 돌아가기
                                break;
                            default:
                                continue;
                        }
                        break;
                    }
                    break;
                case 2: // 작성한 댓글
                    BoardDAO board = new BoardDAO();
                    List<BoardVO> myList= board.searchBoard();
                    List<BoardVO> myGoodList= board.goodBoardList();
                    List<BoardVO> myBadList= board.badBoardList();
                    if(myList.isEmpty() & myGoodList.isEmpty() & myBadList.isEmpty()) {
                        System.out.println("댓글이 존재하지 않습니다.");
                        continue;
                    }
                    System.out.println("=".repeat(30));
                    System.out.println("[ 작성한 댓글 ]");
                    if(myList.isEmpty()) System.out.println("작성한 댓글이 없습니다.");
                    else board.printMyContent(myList);

                    System.out.println("=".repeat(30));
                    System.out.println("[ 추천한 댓글 ]");
                    if(myGoodList.isEmpty()) System.out.println("추천한 댓글이 없습니다.");
                    else board.printMyGoodContent(myGoodList);

                    System.out.println("=".repeat(30));
                    System.out.println("[ 비추천한 댓글 ]");
                    if(myBadList.isEmpty()) System.out.println("비추천한 댓글이 없습니다.");
                    else board.printMyBadContent(myBadList);

                    while (true) {
                        System.out.println("[1]수정 [2]댓글삭제 [3] 추천/비추천취소 [4]돌아가기");
                        int sel8 = sc.nextInt();
                        switch (sel8) {
                            case 1: // 수정
                                if(myList.isEmpty()) {
                                    System.out.println("작성한 댓글이 없습니다.");
                                    continue;
                                }
                                System.out.print("수정할 글번호를 입력하세요 : ");
                                int num = sc.nextInt();
                                if (!board.checkMyContent(num)) {
                                    System.out.println("내가 작성한 댓글이 존재하지 않습니다.");
                                    continue;
                                }
                                while (true) {
                                    System.out.print("수정할 내용 : ");
                                    String content = sc.nextLine().trim();
                                    if (content.isEmpty()) {
                                        System.out.println("내용을 입력하세요");
                                        continue;
                                    }
                                    board.updateContent(num,content);
                                    break;
                                }
                                break;
                            case 2: // 삭제
                                if(myList.isEmpty()) {
                                    System.out.println("작성한 댓글이 없습니다.");
                                    continue;
                                }
                                System.out.print("삭제 할 글번호를 입력하세요 : ");
                                int num1 = sc.nextInt();
                                if (!board.checkMyContent(num1)) {
                                    System.out.println("내가 작성한 댓글이 존재하지 않습니다.");
                                    continue;
                                }
                                board.deleteContent(num1);
                                board.deleteBad(num1);
                                board.deleteGood(num1);
                                break;
                            case 3: // 추천/비추천 취소
                                if(myGoodList.isEmpty() & myBadList.isEmpty()) {
                                    System.out.println("취소할 댓글이 없습니다.");
                                    continue;
                                }
                                System.out.print("추천/비추천을 취소할 글번호를 입력하세요 : ");
                                int num2 = sc.nextInt();
                                if (!board.checkCommentGood(num2)) {
                                    board.deleteMyGood(num2);
                                    board.updateGoodBoard(num2, board.checkGood(num2));
                                    System.out.println("추천을 취소하였습니다. ");
                                }
                                else if(!board.checkCommentBad(num2)) {
                                    board.deleteBad(num2);
                                    board.updateBadBoard(num2, board.checkBad(num2));
                                    System.out.println("비추천을 취소하였습니다. ");
                                }
                                else System.out.println("취소할 댓글이 존재하지 않습니다.");
                                break;
                            case 4: break;
                            default:
                                continue;
                        }
                        break;
                    }
                    break;
                case 3:
                    BoardDAO boardDAO = new BoardDAO();
                    SearchDAO searchDAO = new SearchDAO();
                    if(boardDAO.wishList().isEmpty()) continue;
                    searchDAO.printNutrients(boardDAO.wishList());
                    while (true) {
                        System.out.println("[1]찜취소 [2]돌아가기");
                        int sel9 = sc.nextInt();
                        switch (sel9) {
                            case 1: // 취소
                                System.out.print("취소할 영양제 이름을 입력하세요 : ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                if(boardDAO.checkWish(name) == 2) {
                                    System.out.println("검색결과가 2개 이상 존재합니다. 정확한 이름을 입력해 주세요");
                                    continue;
                                }
                                else if(boardDAO.checkWish(name) == 0){
                                    System.out.println("검색결과가 존재하지 않습니다. 정확한 이름을 입력해 주세요");
                                    continue;
                                }
                                else boardDAO.deleteWish(name);
                                break;
                            case 2: // 돌아가기
                                break;
                            default:
                                continue;
                        }
                        break;
                    }
                    break;
                case 4:
                    break;
                default:
            }
            break;
        }
        return true;
    }
}
