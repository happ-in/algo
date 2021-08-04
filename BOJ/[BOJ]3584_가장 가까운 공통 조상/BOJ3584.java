import java.util.*;
import java.io.*;

public class BOJ3584 {
	static int[] parent;
	static boolean[] visited;

	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = parse(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			int n = Integer.parseInt(br.readLine());
			parent = new int[n+1];
			visited = new boolean[n+1];
			for (int i = 1; i <= n; i++) parent[i] = i;
			for (int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int A = parse(st.nextToken());
				int B = parse(st.nextToken());
				parent[B] = A;
			}
			st = new StringTokenizer(br.readLine(), " ");
			int A = parse(st.nextToken());
			int B = parse(st.nextToken());
			sb.append(findSameParent(A, B)).append("\n");
		}
		System.out.print(sb);
	}

	public static int findSameParent(int r1, int r2) {
		int child = r1;
		while (child != parent[child]) {
			visited[child] = true;
			child = parent[child];
		}
		child = r2;
		while (!visited[child]) {
			visited[child] = true;
			child = parent[child];
		}
		return child;
	}

	public static int parse(String s) {
		return Integer.parseInt(s);
	}
}