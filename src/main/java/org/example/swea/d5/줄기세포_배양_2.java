package org.example.swea.d5;

import java.util.Scanner;

class 줄기세포_배양_2 {
  static int BLANK = 0;
  static int NON_POSITION = 1;
  static int POSITION = 2;
  static int DEATH = 3;
  static Scanner sc = new Scanner(System.in);
  static int MOV = 352;
  static data_type[][][] map;
  static int N, M, K;
  static int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  private static int result;

  /*
1
2 2 10
1 1
0 2

   */
  private static void input() {
    N = sc.nextInt();
    M = sc.nextInt();
    K = sc.nextInt();
    result = 0;

    // 배열 초기화
    map = new data_type[2][MOV][MOV];
    for (int i = 0; i < N + K + 2; i++) {
      for (int j = 0; j < M + K + 2; j++) { // add N -> M
        map[0][i][j] = new data_type();
        map[1][i][j] = new data_type();
      }
    }

    // 입력
    int init_index = (K / 2) + 1;
    for (int i = 0 + init_index; i < N + init_index; i++) {
      for (int j = 0 + init_index; j < M + init_index; j++) {
        map[0][i][j].LP = sc.nextInt();
        if (map[0][i][j].LP > 0) {
          map[0][i][j].status = NON_POSITION;
          map[0][i][j].HP = 0;
        }
      }
    }
  }

  private static void pro() {
    N = N + K + 2;
    M = M + K + 2;

    int curMap = 0;
    int nextMap = 1 - curMap;

    for (int k = 0; k < K; k++) {
      for (int xx = 0; xx < N; xx++) {
        for (int yy = 0; yy < M; yy++) {
          // 1. 죽음 (현제)
          if (map[curMap][xx][yy].status == DEATH) {
            // 1.1 다음 배열에 그냥 저장
            map[nextMap][xx][yy].HP = map[curMap][xx][yy].HP;
            map[nextMap][xx][yy].status = map[curMap][xx][yy].status; // add DEAD -> 현재 상태
            map[nextMap][xx][yy].LP = map[curMap][xx][yy].LP;
            continue;
          }

          // 2. 비활성화 (현제)
          else if (map[curMap][xx][yy].status == NON_POSITION) { // add else if -> if
            // 2.1 비활성화 에너지 증가
            map[nextMap][xx][yy].HP = map[curMap][xx][yy].HP + 1;
            map[nextMap][xx][yy].LP = map[curMap][xx][yy].LP;
            // 2.2 만약 HP이 LP와 같다면
            if (map[nextMap][xx][yy].HP == map[nextMap][xx][yy].LP)
              // 2.2.1 활성화 상태
              map[nextMap][xx][yy].status = POSITION;
            else // add 추가
              map[nextMap][xx][yy].status = NON_POSITION;
          }

          // 3. 활성화 상태 (현제)
          else if (map[curMap][xx][yy].status == POSITION) { // add else if -> if
            // 3.1 처음 활성화 상태이면 (현제)
            if (map[curMap][xx][yy].HP == map[curMap][xx][yy].LP) {
              // 3.1.1 동서남북으로 이동하며
              for (int d = 0; d < 4; d++) {
                int nx = xx + dir[d][0];
                int ny = yy + dir[d][1];

                // 3.1.1.1 다음 칸이 빈칸이면
                if (map[curMap][nx][ny].status == BLANK) { // add 추가
                  if (map[nextMap][nx][ny].status == BLANK) {
                    // 3.1.1.1.1 그냥번식
                    map[nextMap][nx][ny].status = NON_POSITION; // add 이전 상태을 그대로 -> 새로운 상태
                    map[nextMap][nx][ny].LP = map[curMap][xx][yy].LP;
                    map[nextMap][nx][ny].HP = 0;
                  }

                  // 3.1.1.2 다음칸에 셀이 있다면
                  else if (map[nextMap][nx][ny].status == NON_POSITION
                    && map[nextMap][nx][ny].LP < map[curMap][xx][yy].LP) { // add || 연산을 &&으로
                    // 3.1.1.2.1 두개의 세포중 LP가 큰걸 넣는다.
                    // 둘다 새로운 새포
                    map[nextMap][nx][ny].LP = map[curMap][xx][yy].LP;
                  }
                }
              }
            }
            // 3.2 해당하는 셀의 활성화 에너지 감소
            // 이전에 있는 줄기 복사
            map[nextMap][xx][yy].HP = map[curMap][xx][yy].HP;
            map[nextMap][xx][yy].LP = map[curMap][xx][yy].LP;
            map[nextMap][xx][yy].status = map[curMap][xx][yy].status;
            // 사로운 줄기 에너지 감소
            map[nextMap][xx][yy].HP = map[curMap][xx][yy].HP - 1;

            // 3.3 해당하는 셀의 체력이 0이면
            if (map[nextMap][xx][yy].HP == 0)
              // 3.3.1 죽음상태
              map[nextMap][xx][yy].status = DEATH;
            else
              // 3.4 아니면 활성화 상태
              map[nextMap][xx][yy].status = POSITION;
          }
        }
      }
      curMap = 1 - curMap;
      nextMap = 1 - curMap;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[curMap][i][j].status == NON_POSITION // add 0 => curMap
          || map[curMap][i][j].status == POSITION) { // add 0 => curMap
          result++;
        }
      }
    }
  }

  public static void main(String args[]) throws Exception {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      System.out.printf("#%d %d\n", test_case, result);
    }
  }

  static class data_type {
    // 0 : 빈칸
    // 1 : 비활성화
    // 2 : 활성화
    // 3 : 죽음
    int status; // 상태

    // LP : 세포의 생명력
    // HP : 상태에 따라 증감이 달라진다.
    // 죽음 0
    // 비활성화 증가 (LP포인트와 같아져야한다. 활성화 체크)
    // 활성화 감소 (0으로 감소되야한다. 죽음 체크)
    int HP, LP;

    public data_type() {
      this.status = 0; // 빈칸
      this.HP = 0;
      this.LP = 0;
    }
  }
}