import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean[][] visited;
	static int[][] map, check;

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = parse(st.nextToken());
		M = parse(st.nextToken());

		visited = new boolean[N][M];
		check = new int[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) map[i][j] = parse(st.nextToken());
		}

		check[N-1][M-1] = 1;
		dfs(0, 0);
		System.out.println(check[0][0]);
	}

	static int dfs(int x, int y) {
		if (x == N-1 && y == M-1 || visited[x][y]) return check[x][y];
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[x][y] <= map[nx][ny]) continue;
			check[x][y] += dfs(nx, ny);
		}
		return check[x][y];
	}

	static int parse(String s) { return Integer.parseInt(s); }
}
