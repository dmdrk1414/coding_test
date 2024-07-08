package org.example.union;

import java.io.*;
import java.util.StringTokenizer;

public class back17352 {
  /*
4
1 2
1 3

8
1 2
3 4
3 5
7 8
6 7
5 2


   */
  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();
  static int N;
  static int[] parents;
  static int[][] inputs;

  static void input() {
    N = scan.nextInt();
    parents = new int[N + 1];
    inputs = new int[N + 1][2];

    for (int i = 0; i <= N; i++) {
      parents[i] = i;
    }

    for (int i = 1; i <= N - 2; i++) {
      int a = scan.nextInt();
      int b = scan.nextInt();

      inputs[i] = new int[]{a, b};
    }
  }

  static void pro() {
    for (int i = 1; i <= N - 2; i++) {
      int[] input = inputs[i];
      int a = input[0];
      int b = input[1];

      union(a, b);
//      System.out.println(a + " -> " + b);
//      print();
//      System.out.println();
    }

    for (int j = 1; j <= N - 1; j++) {
      if (find(j) != find(j + 1)) {
        sb.append(parents[j]).append(" ").append(parents[j + 1]);
        break;
      }
    }

//    print();
    System.out.println(sb);
  }

  private static void union(final int a, final int b) {
    int a_root = find(a);
    int b_root = find(b);

    if (a_root != b_root) {
      if (a_root < b_root) {
        parents[b_root] = a_root;
      } else {
        parents[a_root] = b_root;
      }
    }
  }

  // root을 찾는다.
  private static int find(final int root) {
    if (parents[root] == root) {
      return parents[root];
    }

    return parents[root] = find(parents[root]);
  }

  public static void main(String[] args) {
    input();
    pro();
  }

  private static void print() {
    for (int i = 0; i <= N; i++) {
      System.out.print(i + " | ");
    }
    System.out.println();
    for (int i = 0; i <= N; i++) {
      System.out.print(parents[i] + " | ");
    }
    System.out.println();
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