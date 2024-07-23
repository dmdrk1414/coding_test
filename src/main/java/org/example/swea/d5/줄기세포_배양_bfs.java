package org.example.swea.d5;

import java.util.*;
import java.io.FileInputStream;

/**
 * 1
 * 9 10 37
 * 0 0 0 0 0 0 0 0 3 0
 * 0 0 0 0 0 0 0 0 5 3
 * 0 0 2 0 0 0 0 4 0 0
 * 3 0 0 0 0 0 4 0 0 0
 * 0 0 0 0 0 3 5 0 0 2
 * 0 0 0 0 0 0 0 0 0 5
 * 0 0 0 0 0 0 0 0 2 3
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 2 2 0 0 0 0 0 0
 */
class 줄기세포_배양_bfs {
  static Scanner sc = new Scanner(System.in);
  static int R, C, K;
  static boolean[][] visited; // 방문여부의 그래프 체크
  static int result;
  static PriorityQueue<Cell> liveCells;
  static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  // 입력
  static void input() {
    R = sc.nextInt();
    C = sc.nextInt();
    K = sc.nextInt();
    result = 0;

    // 지도 정보는 단순히 방문 여부만 관리
    visited = new boolean[R + K + 3][C + K + 3];
    // 확장시간 오른차순, 생명력 내림차순
    liveCells = new PriorityQueue<>();


    // 입력
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        int life = sc.nextInt();
        if (life > 0) {
          Cell cell = new Cell(K / 2 + i, K / 2 + j, life, life, 0);
          liveCells.offer(cell);
          visited[cell.r][cell.c] = true;
        }
      }
    }
  }

  static void print(Cell cell) {
    System.out.println("----------------");
    System.out.println(cell);
  }

  // 실행
  static void pro() {
    while (!liveCells.isEmpty()) {
      // 맨 처음 세포 가져오기
      Cell head = liveCells.poll();

      // 1. 가져온 셀의 시간이 K와 같다면?
      if (head.time == K) {
        // 마지막인 녀석도 다시 넣어라
        liveCells.offer(head);
        break;
      }


      // 2. 가져온 셀이 비활성화 상태이면
      if (!head.isActivated) {
        if (--head.wait == 0) {
          head.isActivated = true;
        }
        head.time++;
        liveCells.offer(head);
      }

      // 3. 활성화 된 상태이면
      else {
        // 초기 활성된 세포일 경우
        if (head.wait == 0) {
          for (int d = 0; d < 4; d++) {
            int nr = head.r + dir[d][0];
            int nc = head.c + dir[d][1];

            // 다음 칸이 비어있다면
            // 라이프에 의해 확장 여부는 생각 하지말자
            if (visited[nr][nc] == false) {
              // 라이프에 의해 확장 여부
              // 뒤에 있는 녀석들은 라이프가 무조건 작다(정렬)
              visited[nr][nc] = true;
              Cell nextCell = new Cell(nr, nc, head.life, head.life, head.time + 1);
              liveCells.offer(nextCell);
            }
          }
        }

        // 4. 죽음 관리
        if (++head.wait != head.life) {
          head.time++;
          liveCells.offer(head);
        }
        // 죽음관리 life 줄이기
//				if(--head.life > 0) {
//					head.time++;
//					liveCells.offer(head);
//				}
        
      }
    }
  }

  public static void main(String args[]) throws Exception {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      System.out.printf("#%d %d\n", test_case, liveCells.size());
    }
  }

  // 초기 비활성활할시 wait == life
  // 활성화 상태일시 wait == 0 이면 죽음
  static class Cell implements Comparable<Cell> {


    int r, c;
    int life, wait; // 생명력, 활성화 까지 기다리는 시간
    int time; // 현재가 전체 시간에서 몇번째인가?
    boolean isActivated; // 활성화 여부

    public Cell(int r, int c, int life, int wait, int time) {
      this.r = r;
      this.c = c;
      this.life = life;
      this.wait = wait;
      this.time = time;
      this.isActivated = (wait == 0);
    }

    @Override
    public int compareTo(Cell c) {
      // 현제 시간의 턴,  오름차순
      if (this.time != c.time) {
        return Integer.compare(this.time, c.time);
      }

      // 현제 생명력, 내림차순
      if (this.life != c.life) {
        return Integer.compare(c.life, this.life);
      }

      return 0;
    }

    @Override

    public String toString() {
      return this.r + " " + this.c + " " + this.life + " " + this.wait + " " + this.time + "";
    }
  }
}