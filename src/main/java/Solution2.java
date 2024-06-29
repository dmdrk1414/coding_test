import java.util.*;
import java.io.FileInputStream;

import java.util.*;
class Solution {
  static Scanner sc = new Scanner(System.in);
  static int N;
  static String str, result;
  static List<Sub> list;

  /*
10
4
monster

   */

  static void input(){
    N = sc.nextInt();
    str = sc.next();
    result = "";

    list = new ArrayList<>();

    for (int i = 0; i < str.length(); i++) {
      list.add(new Sub(str.substring(i, str.length())));
    }

    Collections.sort(list);

//    for (Sub s : list) {
//      System.out.println("s = " + s.sub);
//    }
  }

  static void pro(){
  }

  public static void main(String args[]) {
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      input();
      pro();

      System.out.printf("#%d %s\n",test_case, list.get(N - 1).sub);
    }
  }

  static class Sub implements Comparable<Sub>{
    String sub;

    public Sub(final String sub) {
      this.sub = sub;
    }

    @Override
    public int compareTo(final Sub o) {
      return sub.compareTo(o.sub);
    }
  }
}