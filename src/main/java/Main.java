import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.

1
2 2 10
1 1
0 2

 */
class Main {
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
  }

  private static void bfs() {
    while (!q.isEmpty()) {
      Cell cell = q.poll();
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