package org.example.exhaustive_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Back1759 {

  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();
  static int N, M;
  static String[] arr, selected;
  static boolean[] visited;

  /*
4 6
a t c i s w

acis
acit
aciw
acst
acsw
actw
aist
aisw
aitw
astw
cist
cisw
citw
istw
   */
  static void input() {
    M = scan.nextInt();
    N = scan.nextInt();
    arr = scan.nextLine().split(" ");
    selected = new String[M];
    Arrays.sort(arr);
  }

  /*
새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다. C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.

한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되

한개의 모음



   */
  public static void recul(int idx, int start) {
    if (idx == M) {
      if (valid()) {
        for (int i = 0; i < M; i++) {
          sb.append(selected[i]);
        }
        sb.append("\n");
      }
    } else {
      for (int i = start; i < N; i++) {
        selected[idx] = arr[i];
        recul(idx + 1, i + 1);
        selected[idx] = "";
      }
    }
  }

  private static void pro() {
    recul(0, 0);
  }

  private static boolean valid() {
//  한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
    List<String> moum = List.of("a", "e", "i", "o", "u");
    int moum_cnt = 0;
    int jaum_cnt = 0;
    for (final String s : selected) {
      if (moum.contains(s)) {
        moum_cnt++;
      } else {
        jaum_cnt++;
      }
    }

    if (moum_cnt >= 1 && jaum_cnt >= 2) {
      return true;
    }
    return false;
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