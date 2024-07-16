import java.util.*;
import java.io.*;

/**
 7 7
 #######
 #...RB#
 #.#####
 #.....#
 #####.#
 #O....#
 #######

 6 7
 #######
 #B....#
 #R....#
 #.....#
 #.....#
 #######

 */
public class Main {
  static int N, M, K;
  static Scanner sc = new Scanner(System.in);
  static long[] tree, arr;

  private static void input() {
    N = sc.nextInt();
    M = sc.nextInt();
    K = sc.nextInt();
    arr = new long[N + 1];
    tree = new long[N * 4];


    for (int i = 1; i <= N; i++) {
      arr[i] = sc.nextLong();
    }

    initTree(1, 1, N);
    System.out.println("Arrays.toString(tree) = " + Arrays.toString(tree));

    for (int i = 0; i < M + K; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      long c = sc.nextLong();

      if (a == 1) {
//        a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고
//        update(b, c, 1, 1, N);
      } else if (a == 2) {
//        a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.
//        sumRec(b, (int) c, 1, 1, N);
        
      }
    }
  }

  private static long initTree(final int node, final int left, final int right) {
      if(left == right) {
        return tree[node] = arr[left];
      }

      int mid = left + (right - left) / 2;
      long leftVal = initTree(node * 2, left, mid);
      long rightVal = initTree(node * 2 + 1, mid + 1, right);

      return tree[node] = leftVal + rightVal;
  }

  private static void pro() {

  }

  public static void main(String[] args) {
    input();
    pro();
  }
}
