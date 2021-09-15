import java.io.*;
import java.util.*;

public class Main {

	static int N, ans = 0;
	static int[][] map, panda;

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = parse(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) map[i][j] = parse(st.nextToken());
		}

		panda = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j);
				ans = Math.max(panda[i][j], ans);
			}
		}
		System.out.println(ans);
	}

	static int dfs(int x, int y) {
		if (panda[x][y] != 0) return panda[x][y];

		panda[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] <= map[x][y]) continue;
			panda[x][y] = Math.max(panda[x][y], dfs(nx, ny) + 1);
		}
		return panda[x][y];
	}

	static int parse(String s) {
		return Integer.parseInt(s);
	}
}
