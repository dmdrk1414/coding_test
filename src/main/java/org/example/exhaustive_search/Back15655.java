package org.example.exhaustive_search;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back15655 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] arr, selected;

    /*
4 2
9 8 7 1
     */
    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N + 1];
        selected = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
    }

    public static void recul(int idx, int start) {
        if (idx == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            for (int i = start; i <= N; i++) {
                selected[idx] = arr[i];
                recul(idx + 1, i + 1);
                selected[idx] = 0;
            }
        }
    }

    public static void pro() {
        recul(1, 1);
        System.out.println(sb);
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