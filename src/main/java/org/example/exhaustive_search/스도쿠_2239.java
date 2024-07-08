package org.example.exhaustive_search;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 스도쿠_2239 {
    /*
  103000509
  002109400
  000704000
  300502006
  060000050
  700803004
  000401000
  009205800
  804000107

     */
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int[][] board;
    static List<int[]> checks;

    static void input() {
        checks = new ArrayList<>();

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            board[i] = new int[9];
        }

        for (int i = 0; i < 9; i++) {
            String[] input = scan.nextLine().split("");

            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] == 0) {
                    checks.add(new int[]{i, j});
                }
            }
        }

    }

    private static boolean recur(final int x, final int y) {
        if (y == 9) {
            return recur(x + 1, 0);
        }

        if (x == 9) {
            print();
            return true;
        }

        if (board[x][y] != 0) {
            return recur(x, y + 1);
        }

        // 스토쿠에 넣는 숫자
        for (int i = 1; i <= 9; i++) {
            if (isValid(x, y, i)) {
                board[x][y] = i;
                if (recur(x, y + 1)) {
                    return true;
                }
                board[x][y] = 0;
            }
        }

        return false;
    }

    static void pro() {
//        print();
        recur(0, 0);
//        print();
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    // 0 <= x, y <= 8 까지의 범위
    public static boolean isValid(int x, int y, int target) {
        for (int i = 0; i < 9; i++) {
//      System.out.println(board[i][y] + " " + target);
            if (board[x][i] == target || board[i][y] == target) {
                return false;
            }
        }
        // 정사각형
        int start_squ_x = (x / 3) * 3;
        int start_squ_y = (y / 3) * 3;

        for (int i = start_squ_x; i < start_squ_x + 3; i++) {
            for (int j = start_squ_y; j < start_squ_y + 3; j++) {
                if (board[i][j] == target)
                    return false;
            }
        }

        return true;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
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