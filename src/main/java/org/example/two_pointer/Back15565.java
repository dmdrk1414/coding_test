package org.example.two_pointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back15565 {
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

  static int n, k;
  static int[] a;

  static void input() {
    n = scan.nextInt();
    k = scan.nextInt();
    a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scan.nextInt();
    }
  }

  static void pro() {
    int MOV = 9999_999;
    int result = MOV;

    int count = 0;
    for (int L = 0, R = 0; L < n; L++) {

      while (R < n && count < k && a[L] == 1) {
        if (a[R++] == 1) {
          count++;
        }
      }

      if (a[L] == 1) {
        result = Math.min(result, R - L);
        count--;
      }
    }

    if (result == MOV) {
      System.out.println(-1);
    } else {
      System.out.println(result);
    }
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