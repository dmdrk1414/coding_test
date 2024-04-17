import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.xml.transform.Result;

public class Main {

  /*
1 0
0

1 0
1

2 0
0 0

2 0
1 1

5 6
1 2 3 4 11

*/
  static FastReader scan = new FastReader();

  static int n, m;
  static int[] arr;

  static void input() {
    n = scan.nextInt();
    m = scan.nextInt();
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scan.nextInt();
    }
  }


  static void pro() {
    Arrays.sort(arr);
    int result = 2_000_000_000;
    int L = 0, R = n - 1;
    int sub = 0;
    if (n == 1) {
      System.out.println(arr[0]);
      return;
    }

    while (L < R) {
      sub = arr[R] - arr[L];
      if (sub >= m && sub < result) {
        result = Math.min(result, sub);
      }

      if (sub == m) {
        break;
      }
      if (sub > m) {
//        R--;
        L++;
      } else {
//        L++;
        R--;
      }
    }

    System.out.println(result);
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
