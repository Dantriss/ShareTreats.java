import java.util.Scanner;

public class ShareTreats {
    // 상품코드는 10개가 준비되면 고객에서 10개 까지만 제공
    // 상품코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어짐
    // 상품코드를 이용해 상품 교환을 1회 이루어지면 다시 해당 상품코드로는 상품 교환을 할 수 없음
    // 상품교환전 check를 통해서 상품이 교환되었는지 확인 할 수 있음
    // 상품교환할 때 상점코드를 알아야 함
    // 상점 코드는 A~Za~Z 대소영문자 6글자임
    // ex) claim[aaaaaa][123456789]
    // 고객이 입력하는 문자열은 0~9, A~Za~z, blink 까지 문자를 입력할 수 있음 최대 30글자

    private static String[][] storeAndStuffCode = { // 상점코드, 상품코드
                                    {"aaaaaa","123456789","234567890","345678901"},
                                    {"bbbbbb","111111111","222222222","333333333"},
    };

    private static final boolean usedCheck(String num){ //사용된쿠폰인지 확인하는 메서드
        String numFromCusotomer = checkCouponNum(num);

        for (int i = 0; i < storeAndStuffCode.length; i++) {
            for (int j = 0; j < storeAndStuffCode[i].length; j++) {

                if (storeAndStuffCode[i][j] != null&&storeAndStuffCode[i][j].equals(numFromCusotomer)){
                    return true;
                }
            }
        }


        return false;
    }

    private static String checkCouponNum(String num){ //check[쿠폰번호] 추출
        String result = "";
        int indexStart = num.indexOf("[")+1;
        int indexEnd = num.indexOf("]");
        result = num.substring(indexStart,indexEnd);


        return result;

    }

    private static void removeCouponNum(String numFromCoustomer){ //쿠폰번호 사용하면 null로 바꾸는 메서드
        int startIndex = numFromCoustomer.indexOf("[",numFromCoustomer.indexOf("[")+1);
        int endIndex = numFromCoustomer.indexOf("]",numFromCoustomer.indexOf("]")+1);
        String num = numFromCoustomer.substring(startIndex+1,endIndex);


        int startStoreIndex = numFromCoustomer.indexOf("[");
        int endStoreIndex = numFromCoustomer.indexOf("]");
        String storeNum = numFromCoustomer.substring(startStoreIndex+1,endStoreIndex);

        boolean flag = false;

        for (int i = 0; i < storeAndStuffCode.length; i++) {
            for (int j = 0; j < storeAndStuffCode[i].length; j++) {
                if (storeAndStuffCode[i][j]!=null&&storeAndStuffCode[i][0].equals(storeNum)&&storeAndStuffCode[i][j].equals(num)){
                    storeAndStuffCode[i][j] = null;
                    System.out.println("쿠폰을 사용하여 상품을 주문했습니다. 이용해주셔서 감사합니다.");
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
            if(!flag){
                System.out.println("상점코드 또는 쿠폰번호를 잘못 입력하셨습니다. 다시한번 확인해주세요.");
            }



    }

    public static String gfitconCheck() { //사용자가 입력한 명령어대로 작동하는 메서드
        Scanner code = new Scanner(System.in);

        String lowerCase = "";

        while (true) {
            lowerCase = code.nextLine().toLowerCase().replace(" ","");


            if (lowerCase.equals("help")) {

                System.out.println("\n사용법 안내입니다.\n" +
                                    "사용가능한 쿠폰인지 확인하기 : check[상품번호9자리(0~9)]\n" +
                                    "쿠폰 사용하기 : claim[상점번호6자리(알파벳대소문자)][상품번호9자리(0~9)]\n" +
                                    "프로그램 종료하기 : exit");

            } else if (lowerCase.matches("check\\[\\w{9}\\]")) {
                boolean check = usedCheck(lowerCase);


                if (check){
                    System.out.println("사용 가능한 쿠폰번호 입니다. 사용법은 help로 확인해주세요.");

                }else if (!check){
                    System.out.println("사용하실 수 없는 쿠폰번호 입니다.");
                }


            } else if (lowerCase.matches("claim\\[\\w{6}\\]\\[\\w{9}\\]")) {

                removeCouponNum(lowerCase);

            } else if (lowerCase.equals("exit")) {

                System.out.println("프로그램을 종료합니다.");
                break;

            } else if (lowerCase.length()>30) {
                System.out.println("30글자 이하로만 입력이 가능합니다.");
            } else {

                System.out.println("잘못된 명령어 또는 쿠폰번호를 입력하셨습니다. 다시 한번 확인해주세요.");

            }

        }
        code.close();


            return lowerCase;
    }



    public static void main(String[] args) {

        System.out.println("안녕하세요 고객님 ShareTreats 상품교환 서비스 입니다.\n\n"+
                "CHECK[상품코드] : 상품 교환여부 확인\n" +
                            "HELP : 사용법 안내\n" +
                            "CLAIM[상점코드][상품코드] : 상품교환 \n" +
                            "EXIT : 종료\n" +
                            "중에서 입력하실 수 있습니다.\n"
                            );
        gfitconCheck();




    }
}