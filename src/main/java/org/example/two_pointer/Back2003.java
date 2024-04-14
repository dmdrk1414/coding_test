package org.example.two_pointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back2003 {

  /*
  4 2
  1 1 1 1

   */
  static StringBuilder sb = new StringBuilder();
  static FastReader scan = new FastReader();

  static int n, m;
  static int[] a;

  static void input() {
    n = scan.nextInt();
    m = scan.nextInt();
    a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scan.nextInt();
    }
  }

  static void pro() {
    int count = 0, sum = 0;
    for (int L = 0, R = 0; L < n; L++) {
      while (sum < m && R < n) {
        sum += a[R++];
      }

      if (sum == m) {
        count++;
      }

      sum -= a[L];
    }

    System.out.println(count);
  }

  public static void main(String[] args) {
    input();
    pro();
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