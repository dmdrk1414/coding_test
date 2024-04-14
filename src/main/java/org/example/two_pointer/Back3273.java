package org.example.two_pointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back3273 {

  static FastReader scan = new FastReader();

  static int n, x;
  static int[] arr;

  static void input() {
    n = scan.nextInt();
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scan.nextInt();
    }
    x = scan.nextInt();
  }

  static void pro() {
    int count = 0;
    // 투 포인터 알고리즘을 위한 정렬
    Arrays.sort(arr);

    // 투 포인터 설정
    int left = 0, right = n - 1;

    while (left < right) {
      int sum = arr[left] + arr[right];
      if (sum == x) {
        count++;
        left++;
        right--;
      } else if (sum < x) {
        // 합이 x보다 작으면 왼쪽 포인터를 오른쪽으로 이동하여 합을 키움
        left++;
      } else {
        // 합이 x보다 크면 오른쪽 포인터를 왼쪽으로 이동하여 합을 줄임
        right--;
      }
    }

    System.out.print(count);
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
