import java.util.Scanner;
import java.util.Stack;

/**
 2 12

1 100

 1
 1 : 1, 1

 3
 2 : 10, 1
 3 : 11, 2, 4
 8
 4 : 100, 1, 5
 5 : 101, 2, 7
 6 : 110, 2, 9
 7 : 111, 3, 12

 20
 8 : 1000, 1, 13
 9 : 1001, 2, 15
 10 : 1010, 2, 17
 11 : 1011, 3, 20
 12 : 1100, 2, 22
 13 : 1101, 3, 25
 14 : 1110, 3, 28
 15 : 1111, 4, 32
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N, M;

    private static void input() {
        N = sc.nextInt();
        M = sc.nextInt();

//        N = 1;
//        M = 100;
    }

    private static void pro() {
        int total  = 0;
        for (int i = N; i <= M; i++) {
            int cnt = 0;

            String test = Integer.toBinaryString(i);
            for (char c : test.toCharArray()) {
                if(c == '1') cnt++;
            }

            total += cnt;
            System.out.printf("%3d : %s, %d, %d\n", i, test, cnt, total);
        }
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
