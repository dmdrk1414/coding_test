package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class back15666 {

  static FastReader scan = new FastReader();
  static int N, M;
  static int[] selected, arr;
  static List<Integer> arr_notDuple = new ArrayList<>();
  static boolean[] visited = new boolean[10000];

  private static StringBuilder sb = new StringBuilder();

  /*
4 2
9 7 9 1

1 7 9 9

1 1
1 7
1 9
7 7
7 9
9 9
   */
  static void input() {
    N = scan.nextInt();
    M = scan.nextInt();
    arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = scan.nextInt();
    }
    selected = new int[M];
    for (int i = 0; i < arr.length; i++) {
      if (!arr_notDuple.contains(arr[i])) {
        arr_notDuple.add(arr[i]);
      }
    }

    Collections.sort(arr_notDuple);
  }

  // 중복 가능, 중복 순열 금지, 오른 차순
  public static void recul(int idx, int start) {
    if (idx == M) {
      for (int i = 0; i < M; i++) {
        sb.append(selected[i]).append(" ");
      }
      sb.append("\n");
    } else {
      for (int i = start; i < arr_notDuple.size(); i++) {
        selected[idx] = arr_notDuple.get(i);
        recul(idx + 1, i);
        selected[idx] = 0;
      }
    }
  }

  private static void pro() {
    recul(0, 0);
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