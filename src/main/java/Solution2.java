import java.util.*;
import java.io.FileInputStream;

/**
 1
 12 10
 1B3B3B81F75E

 1
 16 2
 F53586D76286B2D8

 */
class Solution {
  static Scanner sc = new Scanner(System.in);
  static int  N, K, MAX;
  static int result;
  static String input;
  static Set<num> set;
  static int sub_limit;

  static String[] map;

  static void input() {
    result = 0;
    N = sc.nextInt();
    K = sc.nextInt();
    input = sc.next();
    String t = new String(input);
    set = new TreeSet<>();
    sub_limit = N / 4;
    input += input;

    for(int start_idx = 0; start_idx< sub_limit; start_idx++) {
      for(int i = 0; i< 4; i++) {
        int start = N + (start_idx * -1) + (i * sub_limit);
        set.add(new num(input.substring(start, start + sub_limit)));
      }
    }

    map = t.split("");
//    for (int i = 0; i < N; i++) {
//      map[i] = Integer.parseInt(String.valueOf(t.charAt(i)), 16);
//    }

    List<String> list = new ArrayList<>();
    System.out.println("Arrays.toString(map) = " + Arrays.toString(map));

    // -1을 곱하라.
    for (int i = 0; i < 4; i++) {
      // 시작 인덱스
      for (int start_idx = 0; start_idx < 4; start_idx++) {

        int start = start_idx - (i * -1);
      }
    }
  }

  static int valid(char c){
    return (c - 'A') + 10;
  }

  static void pro() {
    int cnt =0;
    for (num num : set) {
      if(++cnt == K){
        result = num.num;
        break;
      }
    }
  }

  static class num implements Comparable<num>{
    String str;
    int num;

    public num(final String str) {
      this.str = str;
      num = Integer.parseInt(str.toLowerCase(), 16);
    }

    @Override
    public int compareTo(final Solution.num o) {
      if(this.num != o.num)
        return Integer.compare(o.num, this.num);

      return 0;
    }

    @Override
    public String toString() {
      return "num{" +
        "str='" + str + '\'' +
        ", num=" + num +
        '}';
    }
  }

  public static void main(String args[]) throws Exception {
    int T;
    T=sc.nextInt();

    for(int test_case = 1; test_case <= T; test_case++)
    {
      input();
      pro();

      System.out.printf("#%d %s\n", test_case, result);
    }
  }
}
