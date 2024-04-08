import java.io.*;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[][] dp = new int[n + 1][10];
    int MOV = 10007;

    for (int i = 0; i <= 9; i++) {
      dp[0][i] = 1;
    }
    for (int k = 1; k <= n; k++) { // 자릿수
      for (int i = 0; i <= 9; i++) { // 큰곳의 숫자.
        for (int j = i; j <= 9; j++) {
          dp[k][i] += (dp[k - 1][j] % MOV);
        }
      }
    }
    
    int result = 0;
    for (int i = 0; i <= 9; i++) {
      result += dp[n - 1][i];
    }
    System.out.println(dp[n][0] % 10007);
  }
}