import java.util.*;
import java.io.FileInputStream;

import java.util.*;
class Solution {
  static final int MAX = 1000001;
  static boolean[] visited;
  static int N, P, max;
  static Scanner sc = new Scanner(System.in);
  static int last;

  static void input(){
    N = sc.nextInt();
    P = sc.nextInt();
    visited = new boolean[MAX];
    last=0;

    for (int i = 0; i < N; i++) {
      int num = sc.nextInt();
      last = Math.max(last, num);
      visited[num] = true;
    }
    max = P+1;
  }

  static void pro(){
    search(last);
  }

  public static void main(String args[]) {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      System.out.println("#" + test_case + " " + max);
    }
  }

  public static void search(int last) {
    int L = 1; int R = 1;

    while(R < last+1) {
      if(visited[R]) {
          R++;
          max = Math.max(max, R - L);
      } else {
          if(P==0) {
            max = Math.max(max, R - L);

              if (!visited[L]) {
                P++;
              }
              L++;
          } else {
              P--;
              R++;
          }
      }
    }
  }
}