package org.example.bfsdfs;

import java.io.*;
import java.util.*;

public class back5567 {

  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();

  static int N, M;
  static ArrayList<Integer>[] adj;
  static int[] dist;

  static void input() {
    N = scan.nextInt();
    M = scan.nextInt();
    adj = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 1; i <= M; i++) {
      int x = scan.nextInt(), y = scan.nextInt();
      adj[x].add(y);
      adj[y].add(x);
    }
  }

  // start 라는 정점의 결혼식에 올 수 있는 사람 수 찾기
  static int bfs(int start) {
    int ret = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    for (int i = 0; i <= N; i++) {
      dist[i] = -1;
    }
    dist[start] = 0;

    while (!q.isEmpty()) {
      int x = q.poll();
      if (1 <= dist[x] && dist[x] <= 2) {
        ret++;
      }
      if (dist[x] == 2) {
        continue;
      }

      for (final Integer y : adj[x]) {
        if (dist[y] != -1) {
          continue;
        }
        q.add(y);
        dist[y] = dist[x] + 1;
      }
    }

    return ret;
  }

  static void pro() {
    dist = new int[N + 1];
    System.out.println(bfs(1));
  }

  public static void main(String[] args) {
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