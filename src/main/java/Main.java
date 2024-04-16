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

public class Main {

  /*
2
abbcaccba

1
abbcaccba

1
abbbcaccba

5
aaaaa

2
aa

3
aaa

2
aaa

2
azz

1
aaaaa

   */
  static FastReader scan = new FastReader();

  static int n, kind;
  static String in;
  static int[] map;

  static void input() {
    n = scan.nextInt();
    in = scan.nextLine();
  }


  static void pro() {
    int sub = 0;
    int len = in.length();
    map = new int[26];
    for (int R = 0, L = 0; R < len; R++) {
      // add
      map[in.charAt(R) - 'a'] += 1;
      if (map[in.charAt(R) - 'a'] == 1) {
        kind++;
      }

      while (kind > n) {
        map[in.charAt(L) - 'a'] -= 1;
        if (map[in.charAt(L) - 'a'] == 0) {
          kind--;
        }
        L++;
      }

      sub = Math.max(sub, R - L + 1);
    }
    System.out.println(sub);
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
