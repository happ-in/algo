import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ17141 {

    static int[][] map;
    static int[] virus;
    static ArrayList<Point> virusPos = new ArrayList<>(); // 바이러스 놓을 수 있는 자리
    static Queue<Point> q = new ArrayDeque<>();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M, count;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = parse(st.nextToken());
        M = parse(st.nextToken());
        map = new int[N][N];
        virus = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = parse(st.nextToken());
                if (map[i][j] == 2) virusPos.add(new Point(i, j));
                if (map[i][j] == 0) count++;
            }
        }

        count += virusPos.size() - M;   // 벽과 바이러스가 없는 곳

        combination(0, 0);
        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    // 바이러스 놓을 수 있는 조합
    static void combination(int start, int index) {
        if (index == M) {
            spread();
            return;
        }
        for (int i = start, size = virusPos.size(); i < size; i++) {
            virus[index] = i;
            combination(i+1, index+1);
        }
    }

    // 바이러스 퍼지기
    static void spread() {
        int[][] map = copy();
        for (int v : virus) {
            Point pos = virusPos.get(v);
            map[pos.x][pos.y] = 2;
            q.add(pos);
        }

        int check = count;
        int time = 0;
        while (!q.isEmpty()) {
            time++;

            // 1초마다 퍼지기
            for (int i = 0, size = q.size(); i < size; i++) {
                Point now = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0) continue;
                    map[nx][ny] = 2;
                    q.add(new Point(nx, ny));
                    check--;
                }
            }
        }

        // 모든 공간에 바이러스가 있다면 ans 갱신
        if (check == 0) ans = Math.min(ans, time);
    }

    static int[][] copy() {
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (map[i][j] == 1)
                    copyMap[i][j] = 1;
         return copyMap;
    }

    static int parse(String s) {
        return Integer.parseInt(s);
    }
}
