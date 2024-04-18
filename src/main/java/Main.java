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
5
1 2 3 4 5

5
1 2 3 1 2

5
1 1 1 1 1

*/
  static FastReader scan = new FastReader();

  static int n;
  static int[] arr;

  static void input() {
    n = scan.nextInt();
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scan.nextInt();
    }
  }


  static void pro() {
    boolean[] check = new boolean[n + 1];
    // 시작
    long result = (long) n * (n + 1) / 2;
    int L = 0, R = 1;
    check[arr[L]] = true;
    while (R < n) {
      if (!check[arr[R]]) {
        check[arr[R]] = true;
        R++;
      } else {
        result -= n - R;
        check[arr[L]] = false;
        L++;
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
