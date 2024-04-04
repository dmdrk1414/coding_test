package org.example.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back2011 {

  static StringBuilder sb = new StringBuilder();
  static String str;
  static String[] arr;
  static String[] selected; // 선택한 것들을 넣는다.
  static int[] dp;
  static int length;

  static void input() {
    FastReader scan = new FastReader();
    str = scan.nextLine();
    arr = str.split("");
    length = arr.length;
    dp = new int[length+ 1];
  }

  public static void main(String[] args) {
    input();
    int MOD = 1000000;
    dp[1] = 1;

    for (int i = 2; i < length; i++) {
      int subNumber = Integer.parseInt((arr[i - 1] + arr[i]));
      dp[i] = dp[i - 1] % MOD;
      if (subNumber >= 1 && subNumber <= 26) {
        dp[i] = dp[i - 2] + dp[i] % MOD;
      }
    }
//    System.out.println(Arrays.toString(dp));
    System.out.println(dp[length - 1]);
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
