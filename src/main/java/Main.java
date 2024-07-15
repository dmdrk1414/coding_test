import java.util.Scanner;
import java.util.Stack;

/**
7
1 2 1 3 1 2 1
4
1 3
2 5
3 3
5 7
팰린드롬인 경우에는 1, 아닌 경우에는 0을 출력한다.
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int[] arr;
    static int M;
    static int[][] target;
    static int[][] visited;

    private static void input() {
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        M = sc.nextInt();
        target = new int[M][2];
        visited = new int[N][N];

        for (int i = 0; i < M; i++) {
            target[i][0] = sc.nextInt() - 1;
            target[i][1] = sc.nextInt() - 1;
        }
    }

    private static void pro() {
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int flag = 1;
                int start = i; int end = j;

                while (start <= end) {
                    if (arr[start++] != arr[end--]) {
                        flag = 0;
                        break;
                    }
                }

                if (flag == 1) {
                    visited[i][j] = flag;
                }
            }
        }

        for (int[] booleans : visited) {
            for (int aBoolean : booleans) {
                System.out.print(aBoolean + " ");
            }
            System.out.println();
        }

//        for (int i = 0; i < target.length; i++) {
//            int one = target[i][0];
//            int two = target[i][1];
//
//            System.out.println(visited[one][two]);
//        }
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
