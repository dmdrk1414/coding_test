package org.example.swea.d5;

import java.util.Scanner;

/*
10
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
7
22 47 72 42 61 93 8 31 72 54 0 64 26 71 93 87 84 83
8
30 20 43 14 58 5 91 51 55 87 40 91 14 55 28 80 75 24 74 63
9
3 9 100 100 16 52 18 19 35 67 42 29 47 68 59 38 68 81 80 37 94 92
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
10
26 100 72 2 71 100 29 48 74 51 27 0 58 0 35 2 43 47 50 49 44 100 66 96
10
46 25 16 6 48 82 80 21 49 34 60 25 93 90 26 96 12 100 44 69 28 15 57 63
10
94 83 72 42 43 36 59 44 52 57 34 49 65 79 14 20 41 9 0 39 100 94 53 3
10
32 79 0 0 69 58 100 31 67 67 58 66 83 22 44 24 68 3 76 85 63 87 7 86

1
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20

 */
class 최적경로 {
  static StringBuilder sb = new StringBuilder();
  static int N;
  static Point coporationPoint;
  static Point homePoint;
  static Point[] points;
  static Scanner sc = new Scanner(System.in);
  static boolean[] visited;
  static int MIN;

  private static void input() {
    N = sc.nextInt();
    coporationPoint = new Point(sc.nextInt(), sc.nextInt());
    homePoint = new Point(sc.nextInt(), sc.nextInt());
    points = new Point[N + 1];
    visited = new boolean[N + 1];
    MIN = Integer.MAX_VALUE;

    for (int i = 1; i <= N; i++) {
      points[i] = new Point(sc.nextInt(), sc.nextInt());
    }
  }

  private static void pro() {
    dfs(coporationPoint, 0, 0);
  }

  private static void dfs(final Point lastPoint, final int lastDistance, final int depth) {
    if (lastDistance >= MIN)
      return;
    if (depth == N) {
      int result_distance = (lastDistance + distance(homePoint, lastPoint));
      MIN = Math.min(MIN, result_distance);
    } else {
      for (int i = 1; i <= N; i++) {
        if (visited[i]) continue;
        visited[i] = true;
        int nextDistance = distance(lastPoint, points[i]);
        dfs(points[i], nextDistance + lastDistance, depth + 1);
        visited[i] = false;
      }
    }
  }

  private static int distance(final Point homePoint, final Point lastPoint) {
    return Math.abs(homePoint.x - lastPoint.x) + Math.abs(homePoint.y - lastPoint.y);
  }


  public static void main(String args[]) throws Exception {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      System.out.printf("#%d %d\n", test_case, MIN);
    }
  }

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}