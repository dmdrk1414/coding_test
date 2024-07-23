package org.example.bfsdfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Back7562 {

  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();

  static int N, M;
  static int[] temp;
  static int[][] target;
  static boolean[][] visited;
  static int[][] board;
  static int[][] dir = new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1},
      {-1, 2}};

  /*
5 3
2 4
3 2
3 5
4 5

   */
  static void input() {
    N = scan.nextInt();
    M = scan.nextInt();
    visited = new boolean[N + 1][N + 1];
    temp = new int[2];
    temp[0] = scan.nextInt();
    temp[1] = scan.nextInt();
    target = new int[M][2];
    for (int i = 0; i < M; i++) {
      target[i] = new int[2];
      target[i][0] = scan.nextInt();
      target[i][1] = scan.nextInt();
    }

  }

  static void bfs(int _x, int _y) {
    Queue<Integer> q = new LinkedList<>();
    q.add(_x);
    q.add(_y);
    visited[_x][_y] = true;
    board = new int[N][N];

    while (!q.isEmpty()) {
      int x = q.poll();
      int y = q.poll();

      for (int i = 0; i < dir.length; i++) {
        int nx = x + dir[i][0];
        int ny = y + dir[i][1];

        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
          continue;
        }
        if (visited[nx][ny]) {
          continue;
        }
        q.add(nx);
        q.add(ny);
        visited[nx][ny] = true;
        board[nx][ny] = board[x][y] + 1;
      }
    }

    for (final int[] ints : board) {
      System.out.println(Arrays.toString(ints));
    }
    for (int i = 0; i < M; i++) {
      System.out.println(target[i][0] + " " + target[i][1]);
    }
    for (int i = 0; i < M; i++) {
      sb.append(board[target[i][0] - 1][target[i][1] - 1]).append("\n");
    }
    // 1 2 1
  }

  static void pro() {
    bfs(temp[0], temp[1]);
  }

  public static void main(String[] args) {
    input();
    pro();
    System.out.println(sb);
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