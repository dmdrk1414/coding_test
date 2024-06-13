import java.io.IOException;
import java.util.*;

class Solution {
  /*

1
10
0 1 0 3 0 0 0 0 7 0
0 0 0 0 -1 0 5 0 0 0
0 4 0 0 0 3 0 0 2 2
1 0 0 0 1 0 0 3 0 0
0 0 3 0 0 0 0 0 6 0
3 0 0 0 2 0 0 1 0 0
0 0 0 0 0 1 0 0 4 0
0 5 0 4 1 0 7 0 0 5
0 0 0 0 0 1 0 0 0 0
2 0 6 0 0 4 0 0 0 4


   */
  static int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
  static Scanner sc = new Scanner(System.in);
  static int N;
  static int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  static int[][] graph;
  static int ball_dir;
  static int start_x, start_y;
  static int fin_ball = -9;
  static int[][] wamHolls;
  static int MAX;
  static int ans;
  static List<int[]> starts;

  private static void input() throws IOException {
//    N = 5;
    MAX = Integer.MIN_VALUE;
    N = sc.nextInt();
    graph = new int[N][N];
    wamHolls = new int[N + 1][4];
    starts = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      Arrays.fill(wamHolls[i], -1);
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int num = sc.nextInt();
        graph[i][j] = num;
        if (num == 0) {
          starts.add(new int[]{i, j});
        }
        if (num >= 6 && num <= 10) {
          if (wamHolls[num][0] == -1) {
            wamHolls[num][0] = i;
            wamHolls[num][1] = j;
          } else {
            wamHolls[num][2] = i;
            wamHolls[num][3] = j;
          }
        }
      }
    }
//    print(wamHolls);
//    graph[0][3] = 3;
//    graph[4][3] = -1;
    start_x = 7;
    start_y = 5;
    ball_dir = EAST;
//    ball_dir = WEST;
//    ball_dir = SOUTH;
//    ball_dir = NORTH;
  }

  private static void bfs(final int x, final int y) {
    Queue<Integer> q = new LinkedList<>();
    q.add(x);
    q.add(y);
    ans = 0;

    while (!q.isEmpty()) {
      int xx = q.poll();
      int yy = q.poll();

//      System.out.println("현제 핀볼의 위치 \nxx: " + xx + ", yy: " + yy + " 현제 방향: " + ball_dir);
      if (graph[xx][yy] == 0) {
        graph[xx][yy] = fin_ball;
        print(graph);
        graph[xx][yy] = 0;
      }
//      System.out.println();

      // 현제 볼이 진행하는 방향
      int nx = xx + dir[ball_dir][0];
      int ny = yy + dir[ball_dir][1];
      // 벽을 만났다.
      if (nx < 0 || nx >= N) {
        meet_wall();
//        System.out.println("벽을 만났습니다. " + ball_dir + "으로 방향 전환했습니다.");
        q.add(xx);
        q.add(ny);
        ans++;
        continue;
      }
      if (ny < 0 || ny >= N) {
        meet_wall();
//        System.out.println("벽을 만났습니다. " + ball_dir + "으로 방향 전환했습니다.");
        q.add(nx);
        q.add(yy);
        ans++;
        continue;
      }

      // 핀볼 그리기
      // 블록을 만난다.
      int temp_stand = graph[nx][ny];
      if (temp_stand >= 1 && temp_stand <= 5) {
        valid_meet_block(temp_stand);
        ans++;
//        System.out.println("블록을 만났습니다. " + ball_dir + "으로 방향 전환했습니다.");
//        System.out.println();
      }

      // 종료 조건
      if (temp_stand == -1 || (nx == x && ny == y)) {
        break;
      }

      // 웜홀 만나기
      if (temp_stand >= 6 && temp_stand <= 10) {
//        System.out.println("웜홀을 만났습니다.");
        if (nx == wamHolls[temp_stand][0] && ny == wamHolls[temp_stand][1]) {
          nx = wamHolls[temp_stand][2];
          ny = wamHolls[temp_stand][3];
        } else {
          nx = wamHolls[temp_stand][0];
          ny = wamHolls[temp_stand][1];
        }
      }

      q.add(nx);
      q.add(ny);
    }
    MAX = Math.max(MAX, ans);
    System.out.println(ans);
//    System.out.println("핀볼 종료");
  }

  private static void pro() {
    for (int[] start : starts) {
      System.out.println(Arrays.toString(start));
      bfs(start[0], start[1]);
    }
  }

  static void meet_wall() {
    if (ball_dir == EAST) ball_dir = WEST;
    else if (ball_dir == WEST) ball_dir = EAST;
    else if (ball_dir == SOUTH) ball_dir = NORTH;
    else if (ball_dir == NORTH) ball_dir = SOUTH;
  }

  static void valid_meet_block(int type) {
    if (type == 1) one_meet_block();
    if (type == 2) two_meet_block();
    if (type == 3) three_meet_block();
    if (type == 4) four_meet_block();
    if (type == 5) five_meet_block();
  }

  static void one_meet_block() {
    if (ball_dir == EAST) ball_dir = WEST;
    else if (ball_dir == NORTH) ball_dir = SOUTH;
    else if (ball_dir == WEST) ball_dir = NORTH;
    else if (ball_dir == SOUTH) ball_dir = EAST;
  }

  static void two_meet_block() {
    if (ball_dir == EAST) ball_dir = WEST;
    else if (ball_dir == SOUTH) ball_dir = NORTH;
    else if (ball_dir == NORTH) ball_dir = EAST;
    else if (ball_dir == WEST) ball_dir = SOUTH;
  }

  static void three_meet_block() {
    if (ball_dir == SOUTH) ball_dir = NORTH;
    else if (ball_dir == WEST) ball_dir = EAST;
    else if (ball_dir == EAST) ball_dir = SOUTH;
    else if (ball_dir == NORTH) ball_dir = WEST;
  }

  static void four_meet_block() {
    if (ball_dir == WEST) ball_dir = EAST;
    else if (ball_dir == NORTH) ball_dir = SOUTH;
    else if (ball_dir == EAST) ball_dir = NORTH;
    else if (ball_dir == SOUTH) ball_dir = WEST;
  }

  static void five_meet_block() {
    if (ball_dir == EAST) ball_dir = WEST;
    else if (ball_dir == WEST) ball_dir = EAST;
    else if (ball_dir == SOUTH) ball_dir = NORTH;
    else if (ball_dir == NORTH) ball_dir = SOUTH;
  }

  public static void main(String args[]) throws Exception {
    int T = 1;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();
    }
  }

  static void print(int x, int y) {
    System.out.println("============");
    System.out.println(x + " " + y);
  }

  static void print(int[][] arr) {
    for (int[] ints : arr) {
      System.out.println(Arrays.toString(ints));
    }
    System.out.println();
  }

  static class WamHoll {
    int x, y;
    int num;
    WamHoll linkWamHoll;

    public WamHoll(int x, int y, int num) {
      this.x = x;
      this.y = y;
      this.num = num;
      this.linkWamHoll = null;
    }

    public void link(WamHoll wamHoll) {
      this.linkWamHoll = wamHoll;
    }
  }
}