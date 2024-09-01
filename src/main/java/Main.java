import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();  // 돌고래의 수
        int K = sc.nextInt();  // 시간의 수

        int[][] M = new int[N + 1][K + 1];
        boolean[] camera = new boolean[N + 1];
        boolean[] photographed = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                M[i][j] = sc.nextInt();  // 돌고래 i가 시간 j에 묘기
            }
        }
        int cnt = 0;

        for (int t = 1; t <= K; t++) {
            for (int i = 1; i <= N; i++) {
                if (M[i][t] == 1 && !photographed[i]) {  // 돌고래 i가 t시간 묘기를 && 촬영 XX
                    if (!camera[i]) {  // 아직 카메라가 설치되지 않은 서식지라면
                        camera[i] = true;  // 카메라 설치
                        i--;
                    } else {
                        photographed[i] = true;  // 돌고래 촬영
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
        sc.close();
    }
}
