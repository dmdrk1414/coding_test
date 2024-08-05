import java.io.*;
import java.util.*;

/**
 8
 1 2
 1 3
 1 4
 2 5
 2 6
 4 7
 4 8

 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[][] dp;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }
    }

    private static void pro() throws IOException {
        // tree이기 때문에 root는 1이다.
        recur(1);

        System.out.println(Math.min(dp[1][1], dp[1][0]));
    }

    static void recur(int parent) {
        visited[parent] = true; // 부모 방문 표시
        dp[parent][0] = 0; // 부모가 얼리가 아니다.
        dp[parent][1] = 1; // 부모가 얼리일때

        for (Integer child : adj[parent]) {
            if (!visited[child]) {
                recur(child);

                // 부모가 얼리가 아니면 자식은 얼리다.
                dp[parent][0] += dp[child][1];
                // 부모가 얼리가 맞으면 자식은 얼리 OR 아니다
                // 그래서 최소의 값을 넣어라.
                dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.flush();
        bw.close();
        br.close();
    }
}