package org.example.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back2579 {

  static StringBuilder sb = new StringBuilder();
  static String str;
  static String[] selected; // 선택한 것들을 넣는다.
  static int[][] dp;
  static int[] step;
  static int N;
  static int MOD = 1000000;

  /*
  6
  10
  20
  15
  25
  10
  20


   */
  static void input() {
    FastReader scan = new FastReader();
    str = scan.nextLine().strip(); // 6
    N = Integer.parseInt(str);
    dp = new int[N + 1][N + 1];
    step = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      String step_input = scan.nextLine().strip(); // 6
      step[i] = Integer.parseInt(step_input);
    }
  }

  public static void main(String[] args) {
    input();

    if (N == 1) {
      System.out.println(step[1]);
    } else if (N == 2) {
      System.out.println(step[1] + step[2]);
    } else {
      dp[1][0] = step[1];
      dp[2][0] = step[2];
      dp[1][1] = step[1];
      dp[2][1] = step[1] + step[2];

      for (int i = 3; i <= N; i++) {
        dp[i][0] = Math.max(dp[i - 2][1] + step[i], dp[i - 2][0] + step[i]);
        dp[i][1] = dp[i - 1][0] + step[i];
      }

      System.out.println(Math.max(dp[N][1], dp[N][0]));
    }
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
