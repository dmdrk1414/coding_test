package org.example.swea;

import java.util.*;
import java.io.FileInputStream;
/**
 1
 6 2
 3 3 3 2 1 1
 3 3 3 2 2 1
 3 3 3 3 3 2
 2 2 3 2 2 2
 2 2 3 2 2 2
 2 2 2 2 2 2

 */
class 활주로_건설 {
  static	Scanner sc = new Scanner(System.in);
  static int result;
  static int N, X;
  static int[][] graph;
  static map_info[] visited;
  static int BLANK = 0;
  static int DOWN = 1;
  static int UP = 2;
  static int[] check_graph;

  static void input() {
    result = 0;
    N = sc.nextInt();
    X = sc.nextInt();
    check_graph = new int[N];
//		visited = new map_info[N];
//		for(int i = 0; i < N ; i++) { visited[i] = new map_info(); }

    graph = new int[N][N];
    for(int i = 0; i < N ; i++) {
      for(int j = 0; j < N ; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

    // x축 검사
//		check_graph = copy_row_graph(1, 0, true);
//		boolean is = install(check_graph);
//		print("------ 결과 확인");
//		System.out.println("설치가능 확인 " + is);
//		print("한줄 그래프", check_graph);
//		print("경사로 설치 이후 체크 그래프", visited);
  }
  static void print(String a) {
    System.out.println(a );
  }
  static void print(String a, int visi) {
    System.out.println(a + " " + visi);
  }

  static void print(String a, int[] visi) {
    System.out.println(a);
    for(int i = 0; i < N ; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for(int i = 0; i < N ; i++) {
      System.out.print(visi[i] + " ");
    }
    System.out.println();
    System.out.println();
  }


  // true => x을 주고
  // false => y을 주고
  static int[] copy_row_graph(int x, int y, boolean check) {
    int[] copy = new int[N];
    // xx축에 대한 검증
    if(check == true) {
      for(int i = 0; i < N ; i++) {
        copy[i] = graph[x][i];
      }
    }

    // yy축에 대한 검증
    else if(check == false) {
      for(int i = 0; i < N ; i++) {
        copy[i] = graph[i][y];
      }
    }

    return copy;
  }

  static boolean install(int[] row_graph) {
    // 설치 확인 초기
    visited = new map_info[N];
    for(int j = 0; j < N ; j++) { visited[j] = new map_info(); }

    // xx축에 대한 검증 y-> N
    for(int i = 1; i < N ; i++) {
      // 이전 블록과 현제 블록의 차이가 2이상이면 끝
      if(Math.abs(row_graph[i] - row_graph[i - 1]) > 1) return false;
      // 이전 블록과 현제 블록의 차이가 1이면
      if(Math.abs(row_graph[i] - row_graph[i - 1]) == 1) {
        // 현제 블록이 낮다면
        if(row_graph[i] < row_graph[i - 1]) {
//					print("");
//					print("----- 여기는 현제 블록이 낮다면의 시작 i인덱스", i);

          // 경사로 길이만큼 평지가 같은지 확인후
          for(int j = 0; j < X; j++) {
//					print("-----여기는 X만큼 길이 탐색");
            // 그래프 끝탐색
            if(i + j >= N) return false;
            // 현재 블록과 다음블록 확확인을 하여 차이가 다르면
            if(row_graph[i] != row_graph[i + j]) return false;
            // 그 그래프래 경사로가 있다면
            if(visited[i + j].isActivate) return false;
          }
//					print("i 값확인", i );
          // 경사로 체크
          for(int j = 0; j < X; j++) {
            visited[i + j].isActivate = true;
            visited[i + j].status = DOWN;
          }

//					print("X만큼 길이 탐색을 한 이후 visited", visited);
          // 현재 검사 index + 경사로 길이
          i = i + X - 1;
          // N == 6
          // i =5
        }
        // 이전 블록이 낮다면 올라가는
        else if(row_graph[i] > row_graph[i - 1]) {

          // 경사로 길이만큼 평지가 같은지 확인
          for(int j = 0; j < X; j++) {
            // 그래프 끝 탐색
            if(i - 1 - j < 0) return false;
            // 이이전 블록과 이전블록의 높이가 다르면
            if(row_graph[i - 1] != row_graph[i - 1 - j]) return false;
            // 그 그래프에 경사로가 있다면
            if(visited[i - 1 - j].isActivate) return false;
          }
          // 경사로 체크
          for(int j = 0; j < X; j++) {
            visited[i - 1 - j].isActivate = true;
            visited[i - 1 - j].status = UP;
          }
        }
      }
    }

    return true;
  }

  static void print(String a, map_info[] visi) {
    System.out.println(a);
    for(int i = 0; i < N ; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for(int i = 0; i < N ; i++) {
      System.out.print(visi[i].status + " ");
    }
    System.out.println();
    System.out.println();
  }

  static void print(String a, int[][] gra) {
    System.out.println(a);
    for(int i = 0; i < gra.length ; i++) {
      System.out.print(i + ": ");
      for(int j = 0; j < gra[0].length; j++) {
        System.out.print(gra[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println();
  }

  static void pro() {
//		print("초기 경사로 설치 체크 그래프", visited);
//		print("초기 그래프", graph);
//
//		// true => x을 주고
//		// false => y을 주고
//		check_graph = copy_row_graph(0, 2, false);
//		print("초기 체크 한줄 그래프", check_graph);
//
//
//		boolean ch = install(check_graph);
//		System.out.println("설치가능 확인 " + ch);
//		print("경사로 설치 이후 체크 그래프", visited);

    boolean is = false;
    for(int i = 0; i < N ; i++) {
      // x축 검사
      check_graph = copy_row_graph(i, 0, true);
      is = install(check_graph);
      if(is) {
        result++;
      }
//			print("------", i);
//			System.out.println("설치가능 확인 " + is);
//			print("한줄 그래프", check_graph);
//			print("경사로 설치 이후 체크 그래프", visited);

      is = false;

//			 y축 검사
      check_graph = copy_row_graph(0, i, false);
      is = install(check_graph);
      if(is) {
        result++;
      }
//			System.out.println("설치가능 확인 " + is);
    }
  }

  public static void main(String args[]) throws Exception
  {
    int T;
    T=sc.nextInt();
    for(int test_case = 1; test_case <= T; test_case++)
    {
      input();
      pro();

      System.out.printf("#%d %d\n", test_case, result);
    }
  }

  static class map_info{
    // 0: 평지
    // 1: 내려가는
    // 2: 올라가는
    int status;
    boolean isActivate;

    public map_info() {
      this.status = 0;
      this.isActivate = false;
    }
  }
}