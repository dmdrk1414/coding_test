package org.example.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back2193 {

  static StringBuilder sb = new StringBuilder();
  static String str;
  static String[] selected; // 선택한 것들을 넣는다.
  static Long[][] dp;
  static int N;
  static int MOD = 1000000;

  /*
6
6
10
13
9
8
1


   */
  static void input() {
    FastReader scan = new FastReader();
    str = scan.nextLine().strip(); // 6
    N = Integer.parseInt(str);
    dp = new Long[N + 1][2];
  }

  public static void main(String[] args) {
    input();
    dp[1][0] = 0L;
    dp[1][1] = 1L;

    for (int i = 2; i <= N; i++) {
      dp[i][1] = dp[i - 1][0];
      dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
    }
//    for (int i = 1; i <= N; i++) {
//      System.out.print(dp[i][0] + " ");
//    }
//    System.out.println();
//    for (int i = 1; i <= N; i++) {
//      System.out.print(dp[i][1] + " ");
//    }
//    System.out.println();
//    System.out.println();
    System.out.println(dp[N][0] + dp[N][1]);
  }


  static class FastReader {

    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    public FastReader(String s) throws FileNotFoundException {
      br = new BufferedReader(new FileReader(new File(s)));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
