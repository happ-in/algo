import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = parse(st.nextToken());
		int C = parse(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = parse(br.readLine());
		Arrays.sort(arr);

		int left = 1;
		int right = arr[N-1] - arr[0];
		int dist = 0;
		int ans = 0;

		while (left <= right) {
			int mid = (left + right)/2;
			int now = arr[0];
			int cnt = 1;
			for (int i = 0; i < N; i++) {
				dist = arr[i] - now;
				if (dist >= mid) {
					cnt++;
					now = arr[i];
				}
			}

			if (cnt >= C) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(ans);
	}

	static int parse(String s) {
		return Integer.parseInt(s);
	}
}