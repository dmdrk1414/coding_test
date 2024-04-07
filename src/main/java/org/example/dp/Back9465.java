package org.example.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back9465 {

  static StringBuilder sb = new StringBuilder();
  static String str;
  static String[] selected; // 선택한 것들을 넣는다.
  static int[][] dp;
  static int[][] items;
  static int N;
  static int T;
  static FastReader scan;
  static int MOD = 1000000;

  /*
2
5
50 10 100 20 40
30 50 70 10 60
7
10 30 10 50 100 20 40
20 40 30 50 60 20 80


   */
  static void input() {
    str = scan.nextLine().strip(); // 6
    N = Integer.parseInt(str);
    dp = new int[3][N + 1];
    items = new int[3][N + 1];
    String[] arr = scan.nextLine().split(" ");
    for (int i = 1; i <= N; i++) {
      items[1][i] = Integer.parseInt(arr[i - 1]);
    }
    arr = scan.nextLine().split(" ");
    for (int i = 1; i <= N; i++) {
      items[2][i] = Integer.parseInt(arr[i - 1]);
    }
  }

  static void input_2() {
    scan = new FastReader();
    str = scan.nextLine().strip(); // 6
    T = Integer.parseInt(str);
  }

  public static void main(String[] args) {
    input_2();
    for (int j = 0; j < T; j++) {
      input();
      dp[1][1] = items[1][1];
      dp[2][1] = items[2][1];

      for (int i = 2; i <= N; i++) {
        dp[1][i] = Math.max(
            dp[2][i - 1] + items[1][i],
            dp[2][i - 2] + items[1][i]
        );
        dp[2][i] = Math.max(
            dp[1][i - 1] + items[2][i],
            dp[1][i - 2] + items[2][i]
        );
      }
//      System.out.println();
//      for (int i = 1; i <= N; i++) {
//        System.out.print(dp[1][i] + " ");
//      }
//      System.out.println();
//      for (int i = 1; i <= N; i++) {
//        System.out.print(dp[2][i] + " ");
//      }
//      System.out.println();
//      System.out.println();
      // 마지막 값 넣어보기
      System.out.println(Math.max(dp[1][N], dp[2][N]));
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
