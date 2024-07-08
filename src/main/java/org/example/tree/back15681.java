package org.example.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class back15681 {
  /*
9 5 3
1 3
4 3
5 4
5 6
6 7
2 3
9 6
6 8
5
4
8

   */
  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();
  static int N, R, Q;
  static int[] node_count;
  static List<Integer>[] adj;

  static void input() throws IOException {
    N = scan.nextInt();
    R = scan.nextInt();
    Q = scan.nextInt();

    node_count = new int[N + 1];
    Arrays.fill(node_count, 1);
    adj = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
    for (int i = 1; i <= N - 1; i++) {
      int a = scan.nextInt(), b = scan.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }

//    for (int i = 1; i <= N; i++) {
//      System.out.println(i + " " + adj[i]);
//    }
  }

  private static void DFS(final int x, final int par, final int depth) {
    for (Integer value : adj[x]) {
      if (value == par) continue;
      DFS(value, x, depth + 1);
      node_count[x] += node_count[value];
    }
  }

  static void pro() {
    DFS(R, R, 0);
//    System.out.println("============================================");
//    System.out.print(" ");
//    for (int i = 0; i <= N; i++) {
//      System.out.print(i + ", ");
//    }
//    System.out.println();
//    System.out.println(Arrays.toString(node_count));

    for (int i = 0; i < Q; i++) {
      sb.append(node_count[scan.nextInt()]).append("\n");
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws IOException {
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