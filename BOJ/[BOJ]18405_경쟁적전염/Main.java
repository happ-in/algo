import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		Queue<int[]> q2 = new ArrayDeque<>();

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) q.add(new int[]{i, j, map[i][j]});
			}
		}
		while (!q.isEmpty()) q2.add(q.poll());

		st = new StringTokenizer(br.readLine(), " ");
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		while (!q2.isEmpty() && --S > 0) {
			for (int i = 0; i < q2.size(); i++) {
				int[] now = q2.poll();
				for (int j = 0; j < 4; j++) {
					int nx = now[0] + dx[j];
					int ny = now[1] + dy[j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0) continue;
					map[nx][ny]  = now[2];
					q2.add(new int[]{nx, ny, now[2]});
				}
			}
		}

		System.out.println(map[X-1][Y-1]);
	}
}
