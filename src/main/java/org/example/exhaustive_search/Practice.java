package org.example.exhaustive_search;

import java.io.*;
import java.util.*;

public class Practice {

  static int N, M;
  static StringBuilder sb = new StringBuilder();
  static int[] selected;

  public static void recu_func(int k) {
    // 1.1 k + 1 이 M과 같은경우
    if (k == M + 1) {
      for (int i = 1; i <= M; i++) {
        // 1.2selected의 배열의 값을 sb에 넣는다.
        sb.append(selected[i]).append(" ");
      }
      sb.append("\n");
    } else {
      for (int i = 1; i <= N; i++) {
        // 2.1 selected에 k번째에 값을 넣는다.
        selected[k] = i;
        // 2.2 재귀 함수 호출
        recu_func(k + 1);
        // 2.3 selected에 k번째의 값을 '0'으로 만들기
        selected[k] = 0;
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    N = Integer.parseInt(input.split(" ")[0]);
    M = Integer.parseInt(input.split(" ")[1]);

    selected = new int[M + 1];
    recu_func(1);
    System.out.println(sb);
  }
}
