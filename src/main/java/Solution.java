import java.util.Arrays;

class Solution {
    // 취약점의 2배의 크기 배열
    static int[] double_weak;
    // 중복을 방지하기 위한 조합을 위한 배열
    static boolean[] used;
    // 최종적인 답
    static int answer;
    // 취약점의 크기
    static int week_length;

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 12;
        int[] week = new int[]{1, 5, 6, 10};
        int[] dist = new int[]{1, 2, 3, 4};

        int result = solution.solution(n, week, dist);

        System.out.println("result = " + result);
    }

    // 조합이 완료된 친구들의 배열
    static boolean check(int[] dist) {
        // 1. 취약점 크기에 대해 루프(loof), week_length 이용
        for (int i = 0; i < week_length; i++) {
            // 1.1 각 취약점의 인덱스에 대하여(idx = i)
            int idx = i;
            // 1.2 조합이 된 친구들에 대해 루프(loof) forEach
            for (int distance : dist) {
                // 1.2.1 각각의 취약점의 거리 + 각 친구의 확인 거리을 저장(position)
                int position = double_weak[idx++] + distance;
                // 1.2.2 각친구의 확인 거리와, 취약점 거리을 합친(position)이 다음 취약점을 확인 가능한지(while)
                while (idx < double_weak.length && double_weak[idx] <= position) {
                    // 1.2.2.1 다음 취약점을 확인한다면 다음취약점의 index을 올린다. (idx++)
                    idx++;
                }
            }
            // 1.3 현제 dist[](조합이 된 친구의 배열)이 모든 취약점들을 확인 가능한가
            if (idx - i >= week_length) {
                // 1.3.1 결과 저장, (idx - i >= length)
                return true;
            }
        }
        return false;
    }

    // depth    : 선택된 친구의 수
    // dist : 조합한 친구 배열(중복X, 순서X)
    // org  : 거리가 큰 친구들부터 배열에 저장
    static void recur(int depth, int[] dist, int[] org) {
        // 1. 조합이 된 친구들 배열(dist)가 == org의 크기가 같다면(분기)
        if (depth == org.length) {
            // 1.2 검증(분기) 임의적으로 조합한 배열이, 취약점을 확인 할수 있는가?
            if (check(dist)) {
                // 1.2.1 결과 저장
                answer = depth;
            }
        }
        // 2. 복사된(org)만큼 루프 시작 i = 0 으로 시작한다. (순서 중요 X )
        for (int i = 0; i < org.length; i++) {
            // 2.1 중복X 이미 확인한 사람을 배제한다(분기)
            if (used[i]) continue;
            // 2.2 방문표시
            used[i] = true;
            // 2.3 조합을 표시 배열(dist)에 친구 번호 입력
            dist[depth] = org[i];
            // 2.4 재귀문 recur(depth + 1, dist, org);
            recur(depth + 1, dist, org);
            // 2.5 방문 표시 X, used[i] = false;
            used[i] = false;
        }
    }

    public int solution(int n, int[] weak, int[] dist) {
        // 취약점의 크기
        week_length = weak.length;
        // 취약점의 2배의 크기 배열
        double_weak = new int[week_length * 2];
        // 중복을 방지하기 위한 조합을 위한 배열
        // 친구 선택
        used = new boolean[dist.length];
        // 최종적인 답
        answer = -1;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < week_length; j++) {
                double_weak[j + (i * week_length)] = weak[j] + (i * n);
            }
        }

        Arrays.sort(dist);

        for (int i = 1; i <= dist.length; i++) {
            int[] org = new int[i];
            System.arraycopy(dist, dist.length - i, org, 0, i);
            recur(0, new int[i], org);
            if (answer > 0) {
                break;
            }
        }

        return answer;
    }
}