package org.example.two_pointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back2473 {

  /*
3
1 1 1

3
-1 -1 -1

5

999999999 1000000000 1000000000 1000000000 1000000000

ans: 999999999 1000000000 1000000000

5

-999999999 -1000000000 -1000000000 -1000000000 -1000000000

ans: -1000000000 -1000000000 -999999999   */
  static FastReader scan = new FastReader();

  static int n;
  static long[] arr;
  static int one = 0, two = 0, three = 0;

  static void input() {
    n = scan.nextInt();
    arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scan.nextInt();
    }
  }


  static void pro() {
    long result = 3_000_000_000l;
    Arrays.sort(arr);
    long sum = 0;
    for (int i = 2; i < n; i++) {
      int L = 0, R = i - 1;

      while (L < R) {
        sum = arr[L] + arr[R] + arr[i];
        if (Math.abs(sum) < result) {
          result = Math.min(result, Math.abs(sum));

          one = (int) arr[L];
          two = (int) arr[R];
          three = (int) arr[i];
        }

        if (sum == 0) {
          break;
        }
        if (sum > 0) {
          R--;
        } else {
          L++;
        }
      }
    }
  }

  public static void main(String[] args) {
    input();
    pro();
    System.out.println(one + " " + two + " " + three);
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
