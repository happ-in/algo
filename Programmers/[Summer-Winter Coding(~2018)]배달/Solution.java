import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

	static long INF = Long.MAX_VALUE;

	public int solution(int N, int[][] road, int K) {
		List<int[]>[] graph = new List[N+1];
		for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
		for (int[] r : road) {
			graph[r[0]].add(new int[]{r[1], r[2]});
			graph[r[1]].add(new int[]{r[0], r[2]});
		}

		int answer = 0;
		long[] dist = new long[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dist, INF);

		int root = 1;
		dist[root] = 0;
		long min = INF;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (visited[j]) continue;
				if (min >= dist[j]) {
					min = dist[j];
					root = j;
				}
			}
			visited[root] = true;
			min = INF;
			for (int j = 0; j < graph[root].size(); j++) {
				int[] now = graph[root].get(j);
				if (visited[now[0]]) continue;
				dist[now[0]] = Math.min(dist[now[0]], dist[root] + now[1]);
			}
			if (dist[root] <= K) answer++;
		}
		return answer;
	}
}