package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class back2023 {

  static StringBuilder sb = new StringBuilder();
  static int N;
  static int[] selected;

  /*
8 8
1 7
3 7
4 7
3 4
4 6
3 5
0 4
2 7

   */
  static void input() {
    FastReader scan = new FastReader();
    N = scan.nextInt();
    selected = new int[N + 1];
  }

  private static boolean valid(int value, int idx) { // 1
    // depth 인데 마지막 한개는 빼고 만든다.
    String num = "";
    for (int i = 1; i < idx; i++) {
      num += selected[i];
    }
    num += value;
//    System.out.println(num);
    int num_ = Integer.parseInt(num);
    if (num_ == 1) {
      return false;
    }
    for (int i = 2; i < num_; i++) {
      if (num_ % i == 0) {
        return false;
      }
    }
    return true;
  }

  private static void recul(final int idx) {
    if (idx == N + 1) {
      for (int i = 1; i <= N; i++) {
        sb.append(selected[i]);
      }
      sb.append("\n");
    } else {
      for (int i = 1; i <= 9; i++) {
        if (valid(i, idx)) {
          selected[idx] = i;
          recul(idx + 1);
          selected[idx] = 0;
        }
      }
    }
  }

  static void pro() {
    recul(1);
  }

  public static void main(String[] args) {
    input();
    pro();
    System.out.println(sb);
//    selected[0] = 1;
//    System.out.println(valid(1, 1));
  }

  private static void print(List<int[]> arr) {
    for (final int[] ints : arr) {
      System.out.println(ints[0] + " " + ints[1]);
    }
  }

  private static void print(final boolean[][] arr) {
    for (final boolean[] booleans : arr) {
      System.out.println(Arrays.toString(booleans));
    }
  }

  private static void print(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static class FastReader {

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