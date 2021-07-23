import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ2667 {
    static Queue<Point> q = new ArrayDeque<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Integer> answer= new PriorityQueue<>();
        int count = 0;
        // 모든 좌표 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 집이 없거나 이미 방문한 좌표는 pass
                if (map[i][j] == '0' || visited[i][j]) continue;
                count++;
                int house = 0;
                visited[i][j] = true;
                q.add(new Point(i, j));

                // bfs로 단지 탐색
                while (!q.isEmpty()) {
                    Point now = q.poll();
                    house++;
                    for (int k = 0; k < 4; k++) {
                        int nx = now.x + dx[k];
                        int ny = now.y + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                        if (map[nx][ny] == '0' || visited[nx][ny]) continue;
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                    }
                }
                answer.add(house);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        while (!answer.isEmpty()) sb.append(answer.poll()).append("\n");
        System.out.print(sb);
    }
}