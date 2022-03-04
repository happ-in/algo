import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ6087 {
    final static int INF = 999999;
    static int W, H;

    static char[][] map;
    static int[][][] mirror;

    static Point[] start_end = new Point[2];
    static ArrayDeque<int[]> q = new ArrayDeque<>();

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        W = parse(st.nextToken()); // 가로
        H = parse(st.nextToken()); // 세로

        map    = new char[H][W];
        mirror = new int[4][H][W];

        int index = 0;
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') {
                    start_end[index++] = new Point(i, j);
                }
            }

            for (int j = 0; j < 4; j++) {
                Arrays.fill(mirror[j][i], INF);
            }
        }

        Point start = start_end[0];
        Point end   = start_end[1];

        for (int i = 0; i < 4; i++) {
            q.add(new int[]{start.x, start.y, i}); // C의 위치 넣기
            mirror[i][start.x][start.y] = 0;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue; // out of index
                if (map[nx][ny] == '*')        continue;              // wall
                if (Math.abs(i - now[2]) == 2) continue;              // 서로 반대 방향
                if (i == now[2] && mirror[i][nx][ny] > mirror[i][now[0]][now[1]]) // 같은 방향이고 dfs 탐색을 하면서 돌았던 다른 경우보다 최단 경로
                {
                    mirror[i][nx][ny] = mirror[i][now[0]][now[1]];
                    q.add(new int[]{nx, ny, i});
                }
                else if (i != now[2]
                      && mirror[i][nx][ny] > mirror[now[2]][now[0]][now[1]] + 1) // 90도 회전했으니 거울 하나가 추가 됨
                {
                    mirror[i][nx][ny] = mirror[now[2]][now[0]][now[1]] + 1;
                    q.add(new int[]{nx, ny, i});
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, mirror[i][end.x][end.y]);
        }
        System.out.print(ans);

    }

    public static int parse(String s) {
        return Integer.parseInt(s);
    }
}
