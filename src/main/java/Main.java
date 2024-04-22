import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

  /*
5 4
.D.*
....
..X.
S.*.
....

   */
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int[][] water_board;
  static int[][] ani_board;
  static boolean[][] visited;
  static String[] graph;
  static int N, M;
  static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  static void input() throws IOException {
    String[] input_arr = br.readLine().split(" ");
    N = Integer.parseInt(input_arr[0]);
    M = Integer.parseInt(input_arr[1]);
    water_board = new int[N][M];
    ani_board = new int[N][M];
    visited = new boolean[N][M];
    graph = new String[N];
    for (int i = 0; i < N; i++) {
      graph[i] = br.readLine().trim();
    }
  }

  static void water_bfs() {
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        water_board[i][j] = -1;
        visited[i][j] = false;
        if (graph[i].charAt(j) == '*') {
          q.add(i);
          q.add(j);
          water_board[i][j] = 0;
          visited[i][j] = true;
        }
      }
    }

    while (!q.isEmpty()) {
      int x = q.poll();
      int y = q.poll();

      for (int i = 0; i < dir.length; i++) {
        int nx = x + dir[i][0];
        int ny = y + dir[i][1];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
          continue;
        }
        if (graph[nx].charAt(ny) != '.') {
          continue;
        }
        if (visited[nx][ny]) {
          continue;
        }
        q.add(nx);
        q.add(ny);
        visited[nx][ny] = true;
        water_board[nx][ny] = water_board[x][y] + 1;
      }
    }
  }

  private static void ani_bfs() {
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        ani_board[i][j] = -1;
        visited[i][j] = false;
        if (graph[i].charAt(j) == 'S') {
          q.add(i);
          q.add(j);
          ani_board[i][j] = 0;
          visited[i][j] = true;
        }
      }
    }

    while (!q.isEmpty()) {
      int x = q.poll();
      int y = q.poll();

      for (int i = 0; i < dir.length; i++) {
        int nx = x + dir[i][0];
        int ny = y + dir[i][1];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
          continue;
        }
        if (graph[nx].charAt(ny) != '.' && graph[nx].charAt(ny) != 'D') {
          continue;
        }
        if (water_board[nx][ny] != -1 && ani_board[x][y] + 1 >= water_board[nx][ny]) {
          continue;
        }
        if (visited[nx][ny]) {
          continue;
        }

        q.add(nx);
        q.add(ny);
        visited[nx][ny] = true;
        ani_board[nx][ny] = ani_board[x][y] + 1;
      }
    }
  }

  static void pro() {
    water_bfs();
    ani_bfs();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (graph[i].charAt(j) == 'D') {
          if (ani_board[i][j] == -1) {
            System.out.println("KAKTUS");
          } else {
            System.out.println(ani_board[i][j]);
          }
        }
      }
    }
  }


  static void print(int[][] arr) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws IOException {
    input();
    pro();
  }
}