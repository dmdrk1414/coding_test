package org.example.two_pointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back1253 {

  /*
  10
  1 2 3 4 5 6 7 8 9 10

  8
  5 8 12 15 17 19 20 25


  4
  0 0 0 0

  1
  1

  2
  1 1

  3
  1 1 1

  3
  1 1 2


   */
  static FastReader scan = new FastReader();

  static int n;
  static int[] arr;

  static void input() {
    n = scan.nextInt();
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scan.nextInt();
    }
  }

  static void pro() {
    int count = 0;
    Arrays.sort(arr);

    for (int i = 0; i < n; i++) {
      if (func(i)) {
        count++;
      }
    }
    System.out.println(count);
  }

  private static boolean func(final int targetId) {
    int L = 0, R = n - 1;
    int target = arr[targetId];

    while (L < R) {
      if (L == targetId) {
        L++;
      } else if (R == targetId) {
        R--;
      } else {
        // 합과 target을 비교
        int sum = arr[L] + arr[R];
        if (sum > target) {
          R--;
        } else if (sum == target) {
          return true;
        } else {
          L++;
        }
      }
    }
    return false;
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
