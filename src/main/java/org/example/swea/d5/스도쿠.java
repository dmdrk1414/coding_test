package org.example.swea.d5;

import java.util.Scanner;

/*
103000509
002109400
000704000
300502006
060000050
700803004
000401000
009205800
804000107

*/
class 스도쿠 {
  static StringBuilder sb = new StringBuilder();
  static Scanner sc = new Scanner(System.in);
  static int[][] graph;

  private static void input() {
    graph = new int[9][9];

    for (int i = 0; i < 9; i++) {
      String[] in = sc.nextLine().split("");
      for (int j = 0; j < 9; j++) {
        graph[i][j] = Integer.parseInt(in[j]);
      }
    }
  }

  static boolean valid(int x, int y, int num) {
    for (int i = 0; i < 9; i++) {
      if (graph[x][i] == num) return false;
      if (graph[i][y] == num) return false;
    }

    int check_x = (x / 3) * 3;
    int check_y = (y / 3) * 3;
    for (int i = check_x; i < check_x + 3; i++) {
      for (int j = check_y; j < check_y + 3; j++) {
        if (graph[i][j] == num) return false;
      }
    }

    return true;
  }

  static boolean recur() {
    Check check = findZeroPlace();

    // 빈칸이 없다면 끝이다.
    if (check == null) return true;

    int x = check.x;
    int y = check.y;

    // 스코쿠 체우기 1~9
    for (int i = 1; i <= 9; i++) {
      if (valid(x, y, i)) {
        graph[x][y] = i;
        // 만약 true이면 끝난 것이다.
        if (recur()) return true;
        graph[x][y] = 0;
      }
    }

    // 아직 다 못체웠다.
    return false;
  }

  static Check findZeroPlace() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (graph[i][j] == 0) return new Check(i, j);
      }
    }

    return null;
  }

  static void print(int[][] graph) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        System.out.print(graph[i][j]);
      }
      System.out.println();
    }
  }

  private static void pro() {
    recur();
    print(graph);
  }

  public static void main(String args[]) throws Exception {
    input();
    pro();
  }

  static class Check {
    int x, y;

    public Check(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}