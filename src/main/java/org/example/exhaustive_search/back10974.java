package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class back10974 {

  static FastReader scan = new FastReader();
  static List<int[]> home;
  static List<int[]> chicken;
  static int[][] chicken_select;
  static int N, M;
  static int result = Integer.MAX_VALUE;
  static int[] dg;

  /*
5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2

   */
  static void input() {
    N = scan.nextInt();
    M = scan.nextInt();
    home = new ArrayList<>();
    chicken = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int in = scan.nextInt();
        if (in == 1) {
          home.add(new int[]{i, j});
        }
        if (in == 2) {
          chicken.add(new int[]{i, j});
        }
      }
    }
    dg = new int[home.size()];
    chicken_select = new int[M][2];
  }

  public static void recul(int idx, int start) {
    // 치킨집에서 M개의 조합
    if (idx == M) {
      process();
    } else {
      for (int i = start; i < chicken.size(); i++) {
        chicken_select[idx] = chicken.get(i);
        recul(idx + 1, i + 1);
      }
    }
  }

  public static int dig_cal(int[] arr_1, int[] arr_2) {
    return Math.abs(arr_1[0] - arr_2[0]) + Math.abs(arr_1[1] - arr_2[1]);
  }

  public static void process() {
    Arrays.fill(dg, Integer.MAX_VALUE);
    for (int i = 0; i < home.size(); i++) {
      for (int j = 0; j < chicken_select.length; j++) {
        int chicken_dig = dig_cal(home.get(i), chicken_select[j]);
        dg[i] = Math.min(dg[i], chicken_dig);
      }
    }
    int sum = 0;
    for (int i : dg) {
      sum += i;
    }
    result = Math.min(result, sum);
  }

  public static void main(String[] args) {
    input();
    recul(0, 0);
    System.out.println(result);
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