import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if (num > 0) plus.add(num);
			else minus.add(num);
		}

		int answer = getAnswer(plus);
		answer += getAnswer(minus);
		System.out.println(answer);
	}

	private static int getAnswer(PriorityQueue<Integer> q) {
		int answer = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			if (q.isEmpty() || now * q.peek() <= now) answer += now;
			else answer += (now * q.poll());
		}
		return answer;
	}
}
