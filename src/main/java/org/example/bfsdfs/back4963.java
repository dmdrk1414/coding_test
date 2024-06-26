package org.example.bfsdfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back4963 {

  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();

  static int N, M;
  static int[][] a;
  static boolean[][] visit;
  static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

  static void input() {
    M = scan.nextInt(); // w
    N = scan.nextInt(); // h
    a = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        a[i][j] = scan.nextInt();
      }
    }
    visit = new boolean[N][M];
  }

  // x, y 를 갈 수 있다는 걸 알고 방문한 상태
  static void dfs(int x, int y) {
    visit[x][y] = true;
    for (int k = 0; k < 8; k++) {
      int nx = x + dir[k][0];
      int ny = y + dir[k][1];
      if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
        continue;  // 지도를 벗어나는 곳으로 가는가?
      }
      if (a[nx][ny] == 0) {
        continue;  // 갈 수 있는 칸인지 확인해야 한다.
      }
      if (visit[nx][ny]) {
        continue;  // 이미 방문한 적이 있는 곳인가?
      }
      dfs(nx, ny);
    }
  }

  static void pro() {
    int ans = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!visit[i][j] && a[i][j] == 1) {
          ans++;
          dfs(i, j);
        }
      }
    }

    System.out.println(ans);
  }

  public static void main(String[] args) {
    while (true) {
      input();
      if (N == 0 && M == 0) {
        break;
      }
      pro();
    }
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