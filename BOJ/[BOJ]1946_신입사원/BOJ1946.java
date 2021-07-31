import java.io.*;
import java.util.*;

public class BOJ1946 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = parse(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 0; test_case < TC; test_case++) {
			int N = parse(br.readLine());
			int[] 등수 = new int[N+1];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int 서류 = parse(st.nextToken());
				int 면접 = parse(st.nextToken());
				등수[서류] = 면접;
			}
			int count = 1;
			int 면접기준 = 등수[1];
			for (int i = 2; i <= N; i++) {
				if (면접기준 > 등수[i]) {
					count++;
					면접기준 = 등수[i];
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}

	static int parse(String s) {
		return Integer.parseInt(s);
	}
}
