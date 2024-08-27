package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back2580 {

  static StringBuilder sb = new StringBuilder();
  static int N;
  static int[][] board;

  /*

0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0

0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

   */
  static void input() {
    FastReader scan = new FastReader();
    N = 9;
    board = new int[N][N];
    for (int i = 0; i < N; i++) {
      String[] input = scan.nextLine().split(" ");
      for (int j = 0; j < input.length; j++) {
        int in = Integer.parseInt(input[j]);
        board[i][j] = in;
      }
    }
  }

  private static void recur(final int x, final int y) {
    if (y == 9) {
      recur(x + 1, 0);
      return;
    }
    if (x == 9) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          System.out.print(board[i][j] + " ");
        }
        System.out.println();
      }
      System.exit(0);
    }

    if (board[x][y] == 0) {
      for (int i = 1; i <= 9; i++) {
        if (valid(x, y, i)) {
          board[x][y] = i;
          recur(x, y + 1);
        }
      }
      board[x][y] = 0;
      return;
    }

    recur(x, y + 1);
  }

  private static boolean valid(int x, int y, int value) {
    // 열 검사
    for (int i = 0; i < 9; i++) {
      if (board[x][i] == value) {
        return false;
      }
    }

    // 행 검사
    for (int i = 0; i < 9; i++) {
      if (board[i][y] == value) {
        return false;
      }
    }

    // 정방 검사
    int _x = (x / 3) * 3;
    int _y = (y / 3) * 3;
    for (int i = _x; i < _x + 3; i++) {
      for (int j = _y; j < _y + 3; j++) {
        if (board[i][j] == value) {
          return false;
        }
      }
    }

    return true;
  }

  static void pro() {
    recur(0, 0);
    print(board);
  }

  static void print(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
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