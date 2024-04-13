package org.example.프로그래머스;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class 연속된_수열의_합 {

  static int N, M, V;
  static List<List<Integer>> graph;
  static boolean[] visited_dfs;
  static boolean[] visited_bfs;

  public static void main(String[] args) throws IOException {
    int[] sequence = new int[]{1, 1, 1, 2, 3, 4, 5};
    int k = 5;

    System.out.println(Arrays.toString(solution(sequence, k)));
  }

  public static int[] solution(int[] sequence, int k) {

    int N = sequence.length;
    int left = 0, right = N;
    int sum = 0;
    for (int L = 0, R = 0; L < N; L++) {
      while (R < N && sum < k) {
        sum += sequence[R++];
      }

      // sum 이 k 보다 큰 경우
      if (sum == k) {
        int range = R - L - 1;
        System.out.println("" + L + " " + R + " " + (R - L) + " " + range);
        if ((right - left) > range) {
          left = L;
          right = R - 1;
        }
      }

      // L의 sequence을 빼서 슬라이딩을 한다.
      sum -= sequence[L];
    }

    int[] answer = {left, right};

    return answer;
  }
}
