import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.xml.transform.Result;

public class Main {

  /*
1
2
6
2

1
2
6
1

*/
  static FastReader scan = new FastReader();

  static int first, n;
  static long left, right;
  static Queue<Integer> que = new LinkedList<>();
  static long one, two, three;

  static void input() {
    first = scan.nextInt();
    left = scan.nextLong();
    right = scan.nextLong();
    n = scan.nextInt();
    que.add(first);
  }


  static void pro() {
    for (int i = 0; i < n; i++) {
      long temp = que.size();
      for (int j = 0; j < temp; j++) {
        int value = que.poll();

        if (value == 1) {
          que.add(1);
          que.add(3);
          que.add(2);
        } else if (value == 2) {
          que.add(2);
          que.add(1);
          que.add(1);
        } else if (value == 3) {
          que.add(2);
          que.add(3);
          que.add(2);
        }
      }
    }
    System.out.println(que);

    System.out.println("[2, 1, 1, 1, 3, 2, 1, 3, 2, 1, 3, 2, 2,");
    for (int i = 0; i < left; i++) {
      que.poll();
    }
    for (long i = left; i <= right; i++) {
      int value = que.poll();
      if (value == 1) {
        one++;
      } else if (value == 2) {
        two++;
      } else if (value == 3) {
        three++;
      }
    }
    System.out.println(one + " " + two + " " + three);
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
