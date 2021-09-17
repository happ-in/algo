import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		int[] ans = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) ans[i] = Integer.parseInt(st.nextToken());

		int count = 0;
		for (int i = 0; i < N; i++) {
			int gap = Math.abs(num[i] - ans[i]);
			count += gap;
			if (num[i] == ans[i]) continue;
			if (num[i] < ans[i]) {
				for (int k = 0; k < gap; k++) {
					for (int j = i; j < N; j++) {
						if (num[j] >= ans[j]) break;
						num[j]++;
					}
				}
			} else {
				for (int k = 0; k < gap; k++) {
					for (int j = i; j < N; j++) {
						if (num[j] <= ans[j]) break;
						num[j]--;
					}
				}
			}
		}

		System.out.println(count);
	}
}
