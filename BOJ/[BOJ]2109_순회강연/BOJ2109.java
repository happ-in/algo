import java.io.*;
import java.util.*;

public class BOJ2109 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parse(br.readLine());

		// pay 높고 day 많이 남은 순서대로
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			q.add(new int[] {parse(st.nextToken()), parse(st.nextToken())});
		}

		int pay = 0;
		boolean[] days = new boolean[10001];
		// Greedy : day 1 ~ 현재까지 중 강연이 가능하다면 돈을 받고 강연한다.
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = now[1]; i > 0; i--) {
				if (!days[i]) {
					pay += now[0];
					days[i] = true;
					break;
				}
			}
		}
		System.out.println(pay);
	}

	public static int parse(String s) {
		return Integer.parseInt(s);
	}
}
