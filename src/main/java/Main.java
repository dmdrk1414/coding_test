import java.util.Scanner;
import java.util.Stack;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Stack<Integer> later, repeat;
    static String input;
    static int result;

    private static void input() {
        later = new Stack<>();
        repeat = new Stack<>();
        input = scanner.nextLine();
    }

    private static void pro() {
        if (input.length() == 1) {
            System.out.println(1);
            return;
        }

        int lenCnt = 0; // 만약 현제 숫자이면 문자열 길이 넣기
        int k_num = 0; // 닫힘 괄호을 만날때 곱해야하는 숫자
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 숫자이면
            if (Character.isDigit(c)) {
                lenCnt++;
                k_num = c - '0';
            }
            // 숫자가 아니면
            else if(c == '('){
                later.add(--lenCnt);
                repeat.add(k_num); // )을 만날때 반복하는 숫자 등록
                lenCnt = 0;
            } else if (c == ')') {
                if(lenCnt >0)
                    later.add(lenCnt);
                lenCnt = 0;
                int len = later.pop();
                int repeatCnt = repeat.pop();
                int cnt = len * repeatCnt; // )을 만나고 반복하는 숫자 * 이전의 숫자을 곱한
                int nextTempLater = 0;

                nextTempLater = later.pop() + cnt;
                later.add(nextTempLater);
                result = nextTempLater;

            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
