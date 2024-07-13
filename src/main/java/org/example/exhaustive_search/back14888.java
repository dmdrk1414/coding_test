package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back14888 {

  static StringBuilder sb = new StringBuilder();
  static int N;
  static int[] nums, operator;
  static int MIN = Integer.MAX_VALUE, MAX = Integer.MIN_VALUE;

  /*
  3
  3 4 5
  1 0 1 0
   */
  static void input() {
    FastReader scan = new FastReader();
    N = scan.nextInt();
    nums = new int[N + 1];
    operator = new int[5];
    for (int i = 1; i <= N; i++) {
      nums[i] = scan.nextInt();
    }
    for (int i = 1; i <= 4; i++) {
      operator[i] = scan.nextInt();
    }
  }

  // Recurrence Function (재귀 함수)
  // 만약 M 개를 전부 고름   => 조건에 맞는 탐색을 한 가지 성공한 것!
  // 아직 M 개를 고르지 않음 => k 번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
  static void rec_func(int k, int value) {
    if (k == N) {
      MAX = Math.max(MAX, value);
      MIN = Math.min(MIN, value);
      return;
    } else {
      for (int cand = 1; cand <= 4; cand++) {
        if (operator[cand] >= 1) {
          operator[cand]--;
          rec_func(k + 1, calculator(nums[1], cand, nums[2]));
          operator[cand]++;
        }
      }
    }
  }

  private static int calculator(final int value, final int oper, final int num) {
    int result = value;
    if (oper == 1) {
      result = result + num;
    }
    if (oper == 2) {
      result = result - num;
    }
    if (oper == 3) {
      result = result * num;
    }
    if (oper == 4) {
      result = result / num;
    }
    return result;
  }

  public static void main(String[] args) {
    input();
    rec_func(1, nums[1]);
    System.out.println(MAX);
    System.out.println(MIN);
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