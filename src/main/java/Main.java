import java.io.*;
import java.util.*;

/*
5 4
0 0 1 0 2
2 3 2 1 0
4 3 2 9 0
1 0 2 9 0
8 8 2 1 0
1 3
3 4
8 1
4 8

 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, result;
    static StringBuilder sb;
    static Queue<Info> q;
    static int[][] map, check;
    static List<Info> movies;
    static int[][] dirs = new int[][]{{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1} };
    static int[][] checkDirs = new int[][]{{-1, -1}, {-1, 1}, {1, 1}, {1, -1} };
    static boolean[][] visited;


    private static void input() throws IOException {
        sb = new StringBuilder();
        result = 0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        movies = new ArrayList<>();
        visited = new boolean[N + 1][N + 1];

        check = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            movies.add(new Info(a, b));
        }

        q.add(new Info(N , 1));
        q.add(new Info(N , 2));
        q.add(new Info(N - 1 , 1));
        q.add(new Info(N - 1 , 2));


    }

    private static void pro() throws IOException {
//        System.out.println(q);
//        Out.print("초기 맵", map);
//        for (boolean[] booleans : visited) {
//            System.out.println(Arrays.toString(booleans));
//        }
        for(Info info : movies) {
            cloudMove(info);
//            System.out.println(q);
//            Out.print("이동 맵", map);

            copyWater();
//            Out.print("물 복사 맵", map);

            addCloud();
//            Out.print("구름 추가 맵", map);
//            for (boolean[] booleans : visited) {
//                System.out.println(Arrays.toString(booleans));
//            }
            initVisited();
//            Out.print("구금", check);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += map[i][j];
            }
        }
    }

    private static void initVisited(){
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
    }

    private static void addCloud() {
        q.clear();
        for (int[] ints : check) {
            Arrays.fill(ints, 0);
        }

        for (int xx = 1; xx <= N; xx++) {
            for (int yy = 1; yy <= N; yy++) {
                if (visited[xx][yy]) continue;
                if (map[xx][yy] < 2) continue;

                map[xx][yy] -= 2;
                check[xx][yy] = 1;
                q.add(new Info(xx , yy));
            }
        }
    }

    private static void copyWater() {

        // 구름
        for(Info info : q) {
            // 대각선에 물이 있는 바구니만큼 물증가
            int cntWater = 0;

            for (int[] checkDir : checkDirs) {
                int nx = info.x + checkDir[0], ny = info.y + checkDir[1];

                // 범위을 벚어나면
                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                // 대각선 물이 없으면
                if(map[nx][ny] < 1) continue;
                cntWater++;
            }
//            System.out.println(info);
//            System.out.println(cntWater);
//            System.out.println();
            map[info.x][info.y] += cntWater;
        }
    }

    private static void cloudMove(final Info moveInfo) {
        int dir = moveInfo.x;
        int num = moveInfo.y;

        int cloudSize = q.size();
//        System.out.println(dir + " " + num + " " + Arrays.toString(dirs[dir]));
        for (int i = 0; i < cloudSize; i++) {
            Info nowCloud = q.poll();
            int nx = nowCloud.x; int ny = nowCloud.y;

            
            // 이동
            for (int j = 0; j < num; j++) {
                nx +=  dirs[dir][0];
                if(nx > N) nx %= N;
                if(nx == 0) nx = N;

                ny +=  dirs[dir][1];
                if(ny > N) ny %= N;
                if(ny == 0) ny = N;
            }
            // 바구니 물 ++
            map[nx][ny]++;
            // 구름 체크
            visited[nx][ny] = true;
            // 이동한 구름 넣기
            q.add(new Info(nx , ny));
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Info{
        int x, y;

        public Info(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
