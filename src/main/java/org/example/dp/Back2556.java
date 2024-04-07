package org.example.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back2556 {

  static StringBuilder sb = new StringBuilder();
  static String str;
  static String[] selected; // 선택한 것들을 넣는다.
  static int[] dp;
  static int[] wine;
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
    dp = new int[N + 1];
    wine = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      String step_input = scan.nextLine().strip(); // 6
      wine[i] = Integer.parseInt(step_input);
    }
  }

  public static void main(String[] args) {
    input();
    dp[1] = wine[1];
    if (N > 1) {
      dp[2] = wine[1] + wine[2];
    }

    for (int i = 3; i <= N; i++) {
      dp[i] = Math.max(
          dp[i - 1],
          Math.max(
              dp[i - 2] + wine[i],
              dp[i - 3] + wine[i - 1] + wine[i]
          )
      );
    }
    System.out.println(dp[N]);
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
