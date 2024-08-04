package org.example.spanningtree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class back1774 {
  static FastReader scan = new FastReader();
  static int N, M;
  static double ans;
  static int[] parents;
  static int[][] alreadys;
  static double[][] weight;
  static List<Edge> edges = new ArrayList<>();
  static Point[] points;

  /*
5 7
M W W W M
1 2 12
1 3 10
4 2 5
5 2 5
2 5 10
3 4 3
5 4 7

   */
  static void input() {
    N = scan.nextInt();
    M = scan.nextInt();

    parents = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      parents[i] = i;
    }

    weight = new double[N + 1][N + 1];

    points = new Point[N + 1];
    for (int i = 1; i <= N; i++) {
      int x = scan.nextInt();
      int y = scan.nextInt();

      points[i] = new Point(i, x, y);
    }

    alreadys = new int[M + 1][2];
    for (int i = 1; i <= M; i++) {
      alreadys[i] = new int[]{scan.nextInt(), scan.nextInt()};
    }
//
//    for (int i = 1; i <= N; i++) {
//      System.out.println(points[i].num + " " + points[i].x + " " + points[i].y);
//    }
//
//    for (int[] already : alreadys) {
//      System.out.println(already[0] + " " + already[1]);
//    }
  }

  static int find(int root) {
    if (parents[root] == root) return parents[root];

    return parents[root] = find(parents[root]);
  }

  static void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA != rootB) {
      if (rootA < rootB)
        parents[rootB] = rootA;
      else
        parents[rootA] = rootB;
    }
  }

  static void pro() {
    // 계산을 한후
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i == j) continue;
        weight[i][j] = distance(points[i], points[j]);
      }
    }

//    for (int i = 1; i <= N; i++) {
//      for (int j = 1; j <= N; j++) {
//        System.out.print(weight[i][j] + " ");
//      }
//      System.out.println();
//    }

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i == j) continue;
        Point point_1 = points[i], point_2 = points[j];
        edges.add(new Edge(point_1.num, point_2.num, weight[point_1.num][point_2.num]));
      }
    }


    // edges을 만들자.
//    for (Edge edge : edges) {
//      System.out.println(edge.start + " " + edge.end + " " + edge.weight);
//    }
    Collections.sort(edges);
//    System.out.println();
//    for (Edge edge : edges) {
//      System.out.println(edge.start + " " + edge.end + " " + edge.weight);
//    }

    MST();
    System.out.printf("%.2f", ans);
  }

  private static double distance(final Point point1, final Point point2) {
    return Math.sqrt(
      Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2)
    );
  }

  private static void MST() {
    // 이미 연결
    for (int[] already : alreadys) {
      int start = already[0], end = already[1];

      union(start, end);
    }

    for (Edge edge : edges) {
      int start = edge.start, end = edge.end;
      int root_start = find(start), root_end = find(end);
      if (root_start != root_end) {
        union(root_start, root_end);
        ans += edge.weight;
      }
//      System.out.println(Arrays.toString(parents));
//      System.out.println(start + " " + end);
//      System.out.println(root_start + " " + root_end);
    }
  }

  public static void main(String[] args) {
    input();
    pro();
  }

  static void print(int[][] graph) {
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }
  }

  static class Point {
    int num, x, y;

    public Point(int num, int x, int y) {
      this.num = num;
      this.x = x;
      this.y = y;
    }
  }

  static class Edge implements Comparable<Edge> {
    int start, end;
    double weight;

    public Edge(int start, int end, double weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    @Override
    public int compareTo(final Edge o) {
      if (this.weight > o.weight) {
        return 1;
      }

      return -1;
    }
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
