import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] map;
    public static int[][] dir = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static int answer = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        /* ================
            변수 초기화
         ================== */
        N = parse(st.nextToken());
        M = parse(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) map[i][j] = parse(st.nextToken());
        }

        /* ===========
         *  DFS 탐색
         * ============= */

        dfs(0, 0, new boolean[N][M], 0);
        System.out.println(answer);
    }

    public static void dfs(int x, int y, boolean[][] visited, int val) {
        answer = Math.max(val, answer);

        if (x == N || y == M) {
            return;
        }

        int next_x = x;
        int next_y = y + 1;

        if (next_y == M) {
            next_x = x + 1;
            next_y = 0;
        }
        dfs(next_x, next_y, visited, val);

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (visited[nx][y] || visited[x][ny] || visited[x][y]) continue;

            visited[x][y] = true;
            visited[nx][y] = true;
            visited[x][ny] = true;
            dfs(next_x, next_y, visited, val + (map[x][y] * 2) + map[nx][y] + map[x][ny]);

            visited[x][y] = false;
            visited[nx][y] = false;
            visited[x][ny] = false;
        }
    }

    public static int parse(String s) {
        return Integer.parseInt(s);
    }
}