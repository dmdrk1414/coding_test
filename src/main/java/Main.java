import java.io.*;
import java.util.*;

/*
7
3
7
5
2
6
1
4

*/

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int result;
    static StringBuilder sb;

    private static void input() throws IOException {
        result = 0;
    }

    private static void pro() throws IOException {
        totalProRec();
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
