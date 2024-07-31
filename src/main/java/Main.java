import java.io.*;
import java.util.*;

/**
 4
 0 10 15 20
 5 0 9 10
 6 13 0 12
 8 8 9 0

 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int[][] dp;
    static final int INF = 11000000;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        dp = new int[n][(1 << n) ];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void pro() {
        // dp배열 초기화
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        System.out.println(recur(0, 1));
    }

    private static int recur(final int city, final int visitBitmask) {
        if (visitBitmask == (1 << n) - 1) {
            return map[city][0];
        }

        if(dp[city][visitBitmask] != INF) {	// dp값이 존재하는경우
            return dp[city][visitBitmask];
        }

        for (int i = 0; i < n; i++) {
            if((visitBitmask & (1 << i) ) == 0 && map[city][i] != 0) {
                dp[city][visitBitmask] = Math.min(dp[city][visitBitmask], recur(i, visitBitmask | (1 << i)) + map[city][i]);
            }
        }

        return dp[city][visitBitmask];
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}