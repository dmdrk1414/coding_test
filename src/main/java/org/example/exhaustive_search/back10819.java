package org.example.exhaustive_search;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class back10819 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int MAX = Integer.MIN_VALUE;
    static int[] arr, selected;
    static boolean[] visited;

    /*
6
20 1 15 8 4 10

3
1 2 3

     */
    static void input() {
        N = scan.nextInt();
        arr = new int[N];
        selected = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        visited = new boolean[N];
        Arrays.sort(arr);
    }

    public static void recul(int idx) {
        if (idx == N) {
            int result = 0;
            for (int i = 1; i < N; i++) {
                result += Math.abs(selected[i - 1] - selected[i]);
            }
            MAX = Math.max(result, MAX);
        } else {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    selected[idx] = arr[i];
                    recul(idx + 1);
                    selected[idx] = 0;
                    visited[i] = false;
                }
            }
        }
    }

    public static void pro() {
        recul(0);
        System.out.println(MAX);
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