import java.io.*;
import java.util.*;

/**
 8
 5 40
 35 25
 10 20
 10 25
 30 50
 50 60
 30 25
 80 100
 30

 8
 5 40
 15 20
 10 20
 10 25
 30 50
 50 60
 30 25
 80 100
 30

 8
 5 10
 16 20
 15 21
 14 22
 13 33
 12 34
 11 45
 10 46
 30




 5
 -5 -15
 30 40
 -5 5
 50 40
 5 -5
 10

 10
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 -100000000 100000000
 200000000

 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, result, D;
    static List<Info> list;
    static PriorityQueue<Integer> pq;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        pq = new PriorityQueue<>();
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Info(Math.min(a, b), Math.max(a, b)));
        }
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
    }

    private static void pro() throws IOException {
        Collections.sort(list);

        for (Info info : list)
            System.out.println(info);

        for (int i = 0; i < N; i++) {
            Info info = list.get(i);
            if(info.r - info.l > D) continue;
            pq.add(info.l);

            while (!pq.isEmpty()) {
                if(info.r - pq.peek() <= D) break;
                pq.poll();
            }

            result = Math.max(result, pq.size());
        }

        bw.write(result + "");
    }

    public static void main(String[] args) throws IOException{
        input();
        pro();

        bw.flush();
        bw.close();
        br.close();
    }

    static class Info implements Comparable<Info> {
        int l, r;

        public Info(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Info o) {
            if (this.r != o.r) {
                return Integer.compare(this.r, o.r);
            }

            if (this.r == o.r) {
                return Integer.compare(this.l, o.l);
            }

            return 0;
        }

        @Override
        public String toString() {
            return "{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
}