/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

/*
10
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
7
22 47 72 42 61 93 8 31 72 54 0 64 26 71 93 87 84 83
8
30 20 43 14 58 5 91 51 55 87 40 91 14 55 28 80 75 24 74 63
9
3 9 100 100 16 52 18 19 35 67 42 29 47 68 59 38 68 81 80 37 94 92
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
10
26 100 72 2 71 100 29 48 74 51 27 0 58 0 35 2 43 47 50 49 44 100 66 96
10
46 25 16 6 48 82 80 21 49 34 60 25 93 90 26 96 12 100 44 69 28 15 57 63
10
94 83 72 42 43 36 59 44 52 57 34 49 65 79 14 20 41 9 0 39 100 94 53 3
10
32 79 0 0 69 58 100 31 67 67 58 66 83 22 44 24 68 3 76 85 63 87 7 86

1
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20

 */
class Solution {
  static StringBuilder sb = new StringBuilder();
  static int N;
  static Point coporation;
  static Point homePoint;
  static Point[] points;
  static Scanner sc = new Scanner(System.in);
  static boolean[] visited;
  static int min_distance;

  private static void input() {
    N = sc.nextInt();
    coporation = new Point(sc.nextInt(), sc.nextInt());
    homePoint = new Point(sc.nextInt(), sc.nextInt());
    points = new Point[N + 1];
    visited = new boolean[N + 1];
    min_distance = Integer.MAX_VALUE;

    for (int i = 1; i <= N; i++) {
      points[i] = new Point(sc.nextInt(), sc.nextInt());
    }
  }

  private static void pro() {
    dfs(1, 0, coporation);
  }

  private static void dfs(int depth, int distance, Point lastPoint) {
    if (distance >= min_distance) {
      return;
    }

    if (depth == N + 1) {
      // 모든 고객을 확인하고, 집으로 돌아가는 길
      int final_distance = distance + distance(lastPoint, homePoint);
      if (final_distance < min_distance) {
        min_distance = final_distance;
        return;
      }
    } else {
      for (int i = 1; i <= N; i++) {
        if (visited[i]) continue;
        visited[i] = true;
        int next_distance = distance + distance(lastPoint, points[i]);
        dfs(depth + 1, next_distance, points[i]);
        visited[i] = false;
      }
    }
  }

  private static int distance(final Point point1, final Point point2) {
    return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
  }

  public static void main(String args[]) throws Exception {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      System.out.printf("#%d %d\n", test_case, min_distance);
    }
  }

  static class Edge implements Comparable<Edge> {
    int start, end;
    int weight;

    public Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      if (this.weight != o.weight) {
        return this.weight - o.weight;
      }
      return 0;
    }
  }

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}