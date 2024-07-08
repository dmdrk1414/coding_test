package org.example.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class back2206 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] inputs = br.readLine().split(" ");

    int n = Integer.parseInt(inputs[0]);
    int m = Integer.parseInt(inputs[1]);

    char[][] map = new char[n][m];
    for (int i = 0; i < n; i++) {
      String input = br.readLine();
      for (int j = 0; j < m; j++) {
        map[i][j] = input.charAt(j);
      }
    }
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][][] visited = new boolean[n][m][2];

    Queue<Loc> q = new LinkedList<>();
    q.add(new Loc(0, 0, 1, false));

    while (!q.isEmpty()) {
      Loc now = q.poll();

      if (now.x == n - 1 && now.y == m - 1) {
        System.out.println(now.cnt);
        return;
      }

      for (int d = 0; d < 4; d++) {
        int nx = now.x + dir[d][0];
        int ny = now.y + dir[d][1];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
          continue;
        }

        int next_cnt = now.cnt + 1;

        if (map[nx][ny] == '0') { // 벽이 아니면
          if (!now.destroyed && !visited[nx][ny][0]) { // 부신 벽이 여태까지 없었으면
            q.add(new Loc(nx, ny, next_cnt, false));
            visited[nx][ny][0] = true;
          } else if (now.destroyed && !visited[nx][ny][1]) { // 벽을 한번 부신 적이 있으면
            q.add(new Loc(nx, ny, next_cnt, true));
            visited[nx][ny][1] = true;
          }

        } else if (map[nx][ny] == '1') { // 벽이면

          if (!now.destroyed) { // 한번도 벽을 부순적이 없다면 부순다~
            q.add(new Loc(nx, ny, next_cnt, true));
            visited[nx][ny][1] = true;
          }
          // 한번 부순 적이 있다면 또 부수고 갈 수 없기 때문에 pass
        }
      }

    }

    System.out.println(-1);
  }

  static class Loc {

    int x;
    int y;
    int cnt;
    boolean destroyed;

    public Loc(int x, int y, int cnt, boolean d) {
      this.x = x;
      this.y = y;
      this.cnt = cnt;
      this.destroyed = d;
    }
  }
}