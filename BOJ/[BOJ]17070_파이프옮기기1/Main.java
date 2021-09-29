import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[][] map;

	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = parse(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) map[i][j] = parse(st.nextToken());
		}
		if (map[N][N] == 0) move(1, 2, 0);
		System.out.println(answer);
	}

	public static void move(int x, int y, int dir) {
		if (x > N || y > N || map[x][y] == 1) return;
		if (dir == 1 && (map[x-1][y] ==1 || map[x][y-1] == 1)) return;
		if (x == N && y == N) {
			answer++;
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (dir == 0 && i == 2) continue;
			if (dir == 2 && i == 0) continue;
			int nx = x + dx[i];
			int ny = y + dy[i];
			move(nx, ny, i);
		}
	}

	public static int parse(String s) {
		return Integer.parseInt(s);
	}
}
