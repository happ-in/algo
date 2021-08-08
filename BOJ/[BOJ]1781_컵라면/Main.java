import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parse(br.readLine());
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);	// 데드라인이 오름차순
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int deadline = parse(st.nextToken());
			int ramen = parse(st.nextToken());
			q.add(new int[]{deadline, ramen});
		}
		long answer = 0;
		PriorityQueue<int[]> q2 = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);	// 라면 오름차순
		while (!q.isEmpty()) {
			int[] now = q.poll();
			q2.add(now);
			answer += now[1];
			while (q2.size() > now[0]) {
				answer -= q2.poll()[1];
			}
		}
		System.out.println(answer);
	}

	static int parse(String s) {
		return Integer.parseInt(s);
	}
}
