import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
/*
4 5 1
1 2
1 3
1 4
2 4
3 4


*/

  static int N, M, V;
  static List<List<Integer>> graph;
  static boolean[] visited_dfs;
  static boolean[] visited_bfs;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);
    V = Integer.parseInt(input[2]);
    graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }
    visited_dfs = new boolean[N + 1];
    visited_bfs = new boolean[N + 1];

    int first, second = 0;
    for (int i = 0; i < M; i++) {
      input = br.readLine().split(" ");
      first = Integer.parseInt(input[0]);
      second = Integer.parseInt(input[1]);
      graph.get(first).add(second);
      graph.get(second).add(first);
    }
    for (int i = 1; i < N + 1; i++) {
      Collections.sort(graph.get(i));
    }

    dfs(V);
    System.out.println();
    bfs(V);
  }

  public static void dfs(int start) {
    visited_dfs[start] = true;
    System.out.print(start + " ");

    for (final Integer value : graph.get(start)) {
      if (!visited_dfs[value]) {
        dfs(value);
      }
    }
  }

  public static void bfs(int start) {
    Queue<Integer> que = new LinkedList<>();
    que.add(start);
    visited_bfs[start] = true;

    while (!que.isEmpty()) {
      int value = que.poll();

      System.out.print(value + " ");
      for (final Integer val : graph.get(value)) {
        if (!visited_bfs[val]) {
          visited_bfs[val] = true;
          que.add(val);
        }
      }
    }
  }
}