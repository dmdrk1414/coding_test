package org.example.swea;

import java.util.*;

/**
 * 1
 * 3 10 10
 * 0 0 0 0 0 0 0 0 0 0
 * 1 0 1 0 1 0 0 0 0 0
 * 1 0 3 0 1 1 0 0 0 1
 * 1 1 1 0 1 2 0 0 0 9
 * 1 1 4 0 1 1 0 0 1 1
 * 1 1 4 1 1 1 2 1 1 1
 * 1 1 5 1 1 1 1 2 1 1
 * 1 1 6 1 1 1 1 1 2 1
 * 1 1 1 1 1 1 1 1 1 5
 * 1 1 7 1 1 1 1 1 1 1
 * <p>
 * 1
 * 3 10 10
 * 1 0 0 0 0 0 0 0 0 0
 * 0 0 1 0 1 0 0 0 0 0
 * 0 0 3 0 1 1 0 0 0 1
 * 0 1 1 0 1 2 0 0 0 9
 * 0 1 4 0 1 1 0 0 1 1
 * 0 1 4 1 1 1 2 1 1 1
 * 0 1 5 1 1 1 1 2 1 1
 * 0 1 6 1 1 1 1 1 2 1
 * 0 1 1 1 1 1 1 1 1 5
 * 1 1 7 1 1 1 1 1 1 1
 * <p>
 * 1
 * 3 10 10
 * 1 0 0 0 0 0 0 0 0 0
 * 1 0 1 0 1 0 0 0 0 0
 * 0 0 3 0 1 1 0 0 0 1
 * 0 1 1 0 1 2 0 0 0 9
 * 1 1 4 0 1 1 0 0 1 1
 * 1 1 4 1 1 1 2 1 1 1
 * 0 1 5 1 1 1 1 2 1 1
 * 1 1 6 1 1 1 1 1 2 1
 * 1 1 1 1 1 1 1 1 1 5
 * 1 1 7 1 1 1 1 1 1 1
 * <p>
 * <p>
 * 1
 * 1 10 10
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 1 2 1 0 0 0 0 0 0 0
 * 1 1 7 0 0 0 0 0 0 0
 * <p>
 * <p>
 * 1
 * 3 10 10
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 1 2 1 0 0 1 1 1 1 1
 * 1 1 7 1 1 1 1 1 1 0
 * 1 1 1 1 1 1 1 1 1 0
 * <p>
 * <p>
 * 1
 * 3 10 10
 * 0 0 0 0 0 0 0 0 0 0
 * 1 0 1 0 1 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 1 1 1 0 1 2 0 0 0 9
 * 0 0 0 0 0 0 0 0 0 0
 * 1 1 4 1 1 1 2 1 1 1
 * 1 1 5 1 1 1 1 2 1 1
 * 0 0 0 0 0 0 0 0 0 0
 * 1 1 1 1 1 1 1 1 1 5
 * 0 0 0 0 0 0 0 0 0 0
 * <p>
 * 1
 * 3 6 7
 * 1 1 0 0 0 0
 * 1 1 0 0 1 0
 * 1 1 0 0 4 0
 * 4 1 0 0 1 0
 * 1 5 1 0 1 6
 * 1 2 8 1 1 6
 * 1 1 1 9 2 1
 */
class 벽돌_깨기 {
  static Scanner sc = new Scanner(System.in);
  static int result;
  static int[][] graph;
  static int N, W, H;
  static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  

  static void input() {
    result = 10000;
    N = sc.nextInt();
    W = sc.nextInt();
    H = sc.nextInt();

    graph = new int[H][W];
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

//		print("graph", graph);
//
//		re_graph(graph);
//
//		print("graph", graph);
  }

  // 벽돌 정리
  static void re_graph(int[][] gra) {
    for (int i = 0; i < gra.length; i++) {
      for (int j = 0; j < gra[0].length; j++) {

        if (gra[i][j] == 0) {
          for (int b = i; b > 0; b--) {
            gra[b][j] = gra[b - 1][j];
            gra[b - 1][j] = 0;
          }
        }
      }
    }
  }

  static int cnt_block(int[][] gra) {
    int cnt = 0;
    for (int i = 0; i < gra.length; i++) {
      for (int j = 0; j < gra[0].length; j++) {
        if (gra[i][j] != 0)
          cnt++;
      }
    }

    return cnt;
  }

  static int[][] clone_graph(int[][] gra) {
    int[][] clone = new int[gra.length][gra[0].length];

    for (int i = 0; i < gra.length; i++) {
      for (int j = 0; j < gra[0].length; j++) {
        clone[i][j] = gra[i][j];
      }
    }

    return clone;
  }

  /**
   * 찾는게 없으면 null
   */
  static int[] top_block(int[][] arr, int y) {
    for (int x = 0; x < H; x++) {
      if (arr[x][y] != 0)
        return new int[]{x, y};
    }

    return null;
  }

  static void recul(int[][] graph, int idx, int pre) {
    // N개의 구슬을 다골랐을때
    if (idx == N) {
      int cnt = cnt_block(graph);
      result = Math.min(result, cnt);
    } else {
      // 구슬에 대한 선택
      for (int i = 0; i < W; i++) {
        int[][] temp_graph = clone_graph(graph);
        int[] top_block = top_block(temp_graph, i);

        // 맨위 블록이 없는 경우;
        if (top_block == null) continue;

        // 맨위 블록의 번호가 1인경우
//				if(temp_graph[top_block[0]][top_block[1]] == 1) {
//					temp_graph[top_block[0]][top_block[1]] = 0;
//					re_graph(temp_graph);
//					recul(temp_graph, idx + 1);
//					continue;
//				}
        bfs(temp_graph, top_block);

        re_graph(temp_graph);
//				print("idx", idx);
//				print("이전 구슬", pre);
//				print("구슬 위치", i);
//				print("temp_graph", temp_graph);
        recul(temp_graph, idx + 1, i);
      }
    }
  }

  static void bfs(int[][] temp_graph, int[] top_block) {
//		print("---------------시작 ", 2);
//		print("top_block: ", top_block);
    Queue<block_info> q = new LinkedList<>();
    int x = top_block[0];
    int y = top_block[1];
    q.add(new block_info(x, y, temp_graph[x][y]));

    while (!q.isEmpty()) {
      block_info block = q.poll();
      int xx = block.x;
      int yy = block.y;
      temp_graph[xx][yy] = 0;

      for (int i = 1; i < block.num; i++) {
        for (int[] dir : dirs) {
          int nx = xx + (dir[0] * i);
          int ny = yy + (dir[1] * i);
          if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

          if (temp_graph[nx][ny] > 1) {
            q.add(new block_info(nx, ny, temp_graph[nx][ny]));
          }

          temp_graph[nx][ny] = 0;
//					print("nx: ", nx);
//					print("ny: ", ny);
//					print("bfs graph: " , temp_graph);
        }
      }
    }
  }

  static void pro() {
    recul(graph, 0, 0);
  }

  public static void main(String args[]) throws Exception {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();
      if (result == 10000)
        result = 0;
      System.out.printf("#%d %d\n", test_case, result);
    }
  }

  static void print(String sdf, int[][] x) {
    System.out.println(sdf);
    int a = 0;
    for (int[] e : x) {
      System.out.print(a++ + ": ");
      for (int i = 0; i < e.length; i++) {
        System.out.print(e[i] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  static void print(String sdf, int[] x) {
    System.out.println(sdf + " " + x[0] + " " + x[1]);
  }

  static void print(String sdf, int x) {
    System.out.println(sdf + " " + x);
  }

  static class block_info {
    int x, y, num;

    public block_info(int x, int y, int num) {
      this.x = x;
      this.y = y;
      this.num = num;
    }
  }
}