package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back15656 {

  static FastReader scan = new FastReader();
  static int N;
  static boolean[] visited;
  static int[] selected;
  private static StringBuilder sb = new StringBuilder();

  /*
3

   */
  static void input() {
    N = scan.nextInt();
    visited = new boolean[N];
    selected = new int[N];
  }

  public static void recul(int idx) {
    if (idx == N) {
      for (int i = 0; i < N; i++) {
        sb.append(selected[i]).append(" ");
      }
      sb.append("\n");
    } else {
      for (int i = 0; i < N; i++) {
        if (!visited[i]) {
          selected[idx] = i + 1;
          visited[i] = true;
          recul(idx + 1);
          selected[idx] = 0;
          visited[i] = false;
        }
      }
    }
  }

  private static void pro() {
    recul(0);
  }

  public static void main(String[] args) {
    input();
    pro();
    System.out.println(sb);
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