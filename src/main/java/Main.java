import java.io.*;
import java.util.*;

// 물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체
public class Main {
  /*
5 4
.D.*
....
..X.
S.*.
....

   */

  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();
  static int N, M;

  static String[] graph;
  static int[][] board;
  static boolean[][] visited;
  static int[] animal, water;
  static int count;
  static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  static void input() {
    N = scan.nextInt(); // y
    M = scan.nextInt(); // x
    graph = new String[N];
    visited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      graph[i] = scan.nextLine();
    }
    board = new int[N][M];
  }

  static void bfs() {
    Queue<int[]> ani_que = new LinkedList<>();
    Queue<int[]> water_que = new LinkedList<>();
    ani_que.add(animal);
    water_que.add(water);
    visited[animal[0]][animal[1]] = true;
    visited[water[0]][water[1]] = true;

//    while (!ani_que.isEmpty()) {
//      int[] ani_value = ani_que.poll();
//      int x = ani_value[0], y = ani_value[1];
//
//      for (int[] dir : dir) {
//        int nx = x + dir[0], ny = y + dir[1];
//        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
//          continue;
//        }
//        if (visited[nx][ny]) {
//          continue;
//        }
//        if (graph[nx].charAt(ny) == 'X' || graph[nx].charAt(ny) == '*') {
//          continue;
//        }
//        if (graph[nx].charAt(ny) == '.') {
//          animal.
//        }
//      }
//    }
  }

  static void water_bfs() {
    Queue<int[]> water_que = new LinkedList<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (graph[i].charAt(j) == '*') {
          water_que.add(new int[]{i, j});
        }
      }
    }

    while (!water_que.isEmpty()) {
      int[] water_value = water_que.poll();
      int x = water_value[0], y = water_value[1];
      for (int[] dir : dir) {
        int nx = x + dir[0], ny = y + dir[1];
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
          continue;
        }
        if (visited[nx][ny]) {
          continue;
        }
        if (graph[nx].charAt(ny) == 'X' || graph[nx].charAt(ny) == 'D') {
          continue;
        }

        if (graph[nx].charAt(ny) == '.') {
          board[nx][ny] += (board[x][y] + 1);
          visited[nx][ny] = true;
          water_que.add(new int[]{nx, ny});
        }
      }
    }
    for (final int[] ints : board) {
      System.out.println(Arrays.toString(ints));
    }
  }

  static void pro() {
    count = 0;
    water_bfs();
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