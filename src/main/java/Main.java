import java.io.*;
import java.util.*;

/**
 6
 10
 20
 15
 25
 10
 20

 */

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N;
  static int ONE = 0, TWO = 1;
  static int[] arr;
  static int[][] dp;

  private static void input() throws IOException {
    N = Integer.parseInt(br.readLine().trim());
    arr = new int[N + 1];
    dp = new int[N + 1][2];

    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(br.readLine().trim());
    }
    System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
  }

  private static void pro() {
    dp[1][ONE] = arr[1];
    dp[1][TWO] = 0;

    if (N >=2){
      dp[2][ONE] = arr[1] + arr[2];
      dp[2][TWO] = arr[2];
    }

    for (int i = 3; i <= N ; i++) {
      dp[i][ONE] = dp[i - 1][TWO] + arr[i];
      dp[i][TWO] = Math.max(dp[i - 2][ONE], dp[i - 2][TWO]) + arr[i];
    }

  }

  public static void main(String[] args) throws IOException {
//    input();
//    pro();

    List<Integer> arr = new LinkedList<>();

    arr.add(1);
    arr.add(2);
    arr.add(3);

    System.out.println("arr.indexOf(2) = " + arr.indexOf(2));
    System.out.println("arr = " + arr);
  }
}
