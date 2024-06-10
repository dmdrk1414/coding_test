package org.example.swea.d5;

import java.util.Scanner;

/*
1
2 2 10
1 1
0 2

 */
class 줄기세포_배양 {
  static final int MAXL = 352;
  static int BLANK = 0;
  static int NONE_POSITION = 1;
  static int POSITION = 2;
  static int DEAD = 3;

  static Scanner sc = new Scanner(System.in);
  static int N, M, K;
  static data_type[][][] map;
  static int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  static int result;

  private static void input() {
    N = sc.nextInt();
    M = sc.nextInt();
    K = sc.nextInt();
    result = 0;

    // map 초기화
    map = new data_type[2][MAXL][MAXL];
    // 왜 + 2을 하는가? => 고민
    // 1 <= N <= 50, 1 <= k <= 300 이니 + 2을 한다.
    // 경계값 포함여부
    for (int i = 0; i < N + K + 2; i++) {
      for (int j = 0; j < M + K + 2; j++) {
        map[0][i][j] = new data_type();
        map[1][i][j] = new data_type();
        map[0][i][j].status = BLANK; // 빈공간
        map[1][i][j].status = BLANK; // 빈공간
      }
    }

    // 입력 받기
    // 초기 줄기 세포의 Life Point
    int init_add_index = (K / 2) + 1;
    for (int i = 0 + init_add_index; i < N + init_add_index; i++) {
      for (int j = 0 + init_add_index; j < M + init_add_index; j++) {
        map[0][i][j].LP = sc.nextInt();
        if (map[0][i][j].LP > 0) {
          map[0][i][j].status = 1;
          map[0][i][j].HP = 0;
        }
      }
    }
  }

  private static void pro() {
    // 확장을 해야한다
    // 경계값을 포함해야한다.
    N = N + K + 2;
    M = M + K + 2;

    int curMap = 0; // 먼지 모르겠다.
    int nextMap = 1 - curMap;

    // 시간초 마다 for
    for (int k = 0; k < K; k++) {
      // y축
      for (int xx = 0; xx < N; xx++) {
        // x축
        for (int yy = 0; yy < M; yy++) {

          // 죽은 세포일 경우
          if (map[curMap][xx][yy].status == DEAD) {
            // 아무 동작도 하지 않음
            // 다음 배열의 상태 = 현재배열의 상태
            map[nextMap][xx][yy].status = map[curMap][xx][yy].status;
            map[nextMap][xx][yy].LP = map[curMap][xx][yy].LP;
            continue;
          }

          // 비활성 상태일 경우
          else if (map[curMap][xx][yy].status == NONE_POSITION) {
            // 비활성 시간 증가
            // 비활성 일때 nextMap의 LP가 0이면 넣어야 한다.
            map[nextMap][xx][yy].HP = map[curMap][xx][yy].HP + 1;
            map[nextMap][xx][yy].LP = map[curMap][xx][yy].LP;

            // 비활성 시간이 LP 만큼 지났을 경우
            if (map[nextMap][xx][yy].HP == map[nextMap][xx][yy].LP)
              // 활성 상태로 변경
              map[nextMap][xx][yy].status = POSITION;
            else
              map[nextMap][xx][yy].status = NONE_POSITION;
          }

          // 활성 상태일 경우
          else if (map[curMap][xx][yy].status == POSITION) {
            // 활성 상태가 된 후, 첫 시간일 경우
            if (map[curMap][xx][yy].HP == map[curMap][xx][yy].LP) {

              // 현재 위치에서 상, 하, 좌, 우 탐색
              for (int d = 0; d < 4; d++) {
                int nx = xx + dir[d][0];
                int ny = yy + dir[d][1];

                // 줄기 세포가 번식하는 방향이 비어있을 경우
                // 다음 셀에 세포가 비어있는 경우
                if (map[curMap][nx][ny].status == BLANK) {
                  // 하나의 줄기 세포가 번식하려고 하는 경우
                  if (map[nextMap][nx][ny].status == BLANK) {
                    // 해당 줄기 세포가 셀을 차지
                    map[nextMap][nx][ny].status = NONE_POSITION;
                    map[nextMap][nx][ny].LP = map[curMap][xx][yy].LP;
                    map[nextMap][nx][ny].HP = 0;
                  }

                  // 두개 이상의 줄기 세포가 셀에 동시에 번식하려고 하는 경우
                  // 생명력(LP)가 높은 줄기 세포가 셀을 차지
                  else if (map[nextMap][nx][ny].status == NONE_POSITION
                    && map[nextMap][nx][ny].LP < map[curMap][xx][yy].LP)
                    map[nextMap][nx][ny].LP = map[curMap][xx][yy].LP;
                }
              }
            }

            // 활성 시간 증가
            // 활성 시간이 갈수록 -1 을 한다.
            map[nextMap][xx][yy].HP = map[curMap][xx][yy].HP - 1;

            // 활성 상태 시간이 LP 시간 만큼 지났을 경우
            if (map[nextMap][xx][yy].HP == 0)
              // 줄기 세포가 죽음
              map[nextMap][xx][yy].status = DEAD;
            else
              map[nextMap][xx][yy].status = POSITION;
          }
        }
      }
      curMap = 1 - curMap;
      nextMap = 1 - curMap;
    }

    // 살아 있는 줄기 세포 탐색
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[curMap][i][j].status == NONE_POSITION
          || map[curMap][i][j].status == POSITION)
          result++;
      }
    }
  }

  public static void main(String args[]) throws Exception {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      // 결과
      System.out.printf("#%d %d\n", test_case, result);
    }
  }

  static void print(data_type[][][] graph, int i) {
    for (int j = 0; j < N + (K) + 2; j++) {
      for (int k = 0; k < M + (K) + 2; k++) {
        System.out.print(graph[i][j][k].LP + " ");
      }
      System.out.println();
    }
  }

  static class data_type {
    // 0: 빈 공간,
    // 1: 비활성 상태,
    // 2: 활성 상태,
    // 3: 죽은 상태
    int status;
    // LP : Life Point
    // HP: 죽은 상태: 0, 비활성 상태일 경우 증가, 활성 상태일 경우 감소
    int LP, HP;
  }
}