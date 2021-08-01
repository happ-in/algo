import java.io.*;
import java.util.*;

public class BOJ2841 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = parse(st.nextToken());	// 멜로디에 포함된 음의 수
		int P = parse(st.nextToken());	// 한 줄에 있는 프렛 수

		List<Stack<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= N; i++) list.add(new Stack<>());

		int move = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int 줄 = parse(st.nextToken());
			int 프렛 = parse(st.nextToken());
			Stack<Integer> stack = list.get(줄);
			if (!stack.isEmpty()) {
				while (!stack.isEmpty() && stack.peek() > 프렛) {
					stack.pop();
					move++;
				}
			}
			if (!stack.isEmpty() && stack.peek() == 프렛) continue;
			stack.add(프렛);
			move++;
		}
		System.out.println(move);
	}

	static int parse(String s) {
		return Integer.parseInt(s);
	}
}
