package org.example.two_pointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back11728 {
  /*
10 3
1 2 2 2 1 2 1 2 2 1

5 3
2 2 2 2 2

1 3
1

1 1
1

   */

  static FastReader scan = new FastReader();

  static int n, m, k;
  static int[] a_arr;
  static int[] b_arr;
  static StringBuilder sb = new StringBuilder();

  static void input() {
    n = scan.nextInt();
    m = scan.nextInt();
    a_arr = new int[n];
    for (int i = 0; i < n; i++) {
      a_arr[i] = scan.nextInt();
    }

    b_arr = new int[m];
    for (int i = 0; i < m; i++) {
      b_arr[i] = scan.nextInt();
    }
  }

  static void pro() {
    int result_p = 0, A = 0, B = 0;

    while (A < n && B < m) {
      if (a_arr[A] <= b_arr[B]) {
        sb.append(a_arr[A++]).append(" ");
      } else {
        sb.append(b_arr[B++]).append(" ");
      }
    }

    while (A < n) {
      sb.append(a_arr[A++]).append(" ");
    }
    while (B < m) {
      sb.append(b_arr[B++]).append(" ");
    }

    System.out.print(sb);
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