import java.io.*;
import java.util.*;

public class BOJ1504 {

	static int N, E, v1, v2;
	static int[] dist;
	static boolean[] visited;
	static int[][] map;

	static final int INF = 20000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = parse(st.nextToken());
		E = parse(st.nextToken());
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) Arrays.fill(map[i], INF);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = parse(st.nextToken());
			int b = parse(st.nextToken());
			int c = parse(st.nextToken());
			map[a][b] = map[b][a] = c;
		}
		st = new StringTokenizer(br.readLine(), " ");
		v1 = parse(st.nextToken());
		v2 = parse(st.nextToken());

		dist = new int[N+1];
		visited = new boolean[N+1];

		int v1_v2_distance = dijkstra(v1, v2);
		int answer1 = dijkstra(1, v1) + v1_v2_distance + dijkstra(v2, N);
		int answer2 = dijkstra(1, v2) + v1_v2_distance + dijkstra(v1, N);
		int answer = Math.min(answer1, answer2);

		System.out.println(answer >= INF ? -1 :answer);
	}

	public static int dijkstra(int start, int end) {
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dist[start] = 0;

		int index = start;
		int min = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= N; j++) {
				if (visited[j] || dist[j] == INF || min < dist[j]) continue;
				min = dist[j];
				index = j;
			}

			visited[index] = true;
			for (int j = 1; j <= N; j++) {
				if (visited[j] || map[index][j] == 0) continue;
				dist[j] = Math.min(dist[j], dist[index] + map[index][j]);
			}
			min = INF;
			if (index == end) return dist[end];
		}

		return dist[end];
	}

	public static int parse(String s) {
		return Integer.parseInt(s);
	}
}
