import java.io.*;
import java.util.*;

/**
 5
 4
 1 3 1 2
 3
 1 3 2

 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, N, M;
    static long[] a_sum, b_sum;
    static int[] a_arr, b_arr;
    static long result;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        result = 0;
        a_arr = new int[N];
        a_sum = new long[N * (N + 1) / 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a_arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        b_arr = new int[M];
        b_sum = new long[M * (M + 1) / 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b_arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void pro() throws IOException {
        make_sum(a_sum, N, a_arr);
        make_sum(b_sum, M, b_arr);

        for (int i = 0; i < a_sum.length;) {
            long a_target = a_sum[i];
            long b_target = T - a_target;

            long a_term = upper_bound(a_sum, a_target) - lower_bound(a_sum, a_target);
            long b_term = upper_bound(b_sum, b_target) - lower_bound(b_sum, b_target);

            result += a_term * b_term;
            i += a_term;
        }

        // 0도 가능
        bw.write(result + "");
    }

    private static long upper_bound(long[] arr, long target) {
        int L = 0, R = arr.length - 1;

        while(L < R) {
            int mid = L + (R - L) / 2;

            if (target >= arr[mid]) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L;
    }

    private static long lower_bound(long[] arr, long target) {
        int L = 0, R = arr.length - 1;

        while(L < R) {
            int mid = L + (R - L) / 2;

            if (target <= arr[mid]) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return L;
    }

    private static void make_sum( long[] sum,  int n,  int[] arr) {
        int idx = 0;

        for (int i = 0; i < n; i++) {
            int temp = 0;

            for (int j = i; j < n; j++) {
                temp += arr[j];
                sum[idx++] = temp;
            }
        }

        Arrays.sort(sum);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.flush();
        bw.close();
        br.close();
    }
}