import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			if (list.isEmpty() || list.get(list.size() - 1) < val) list.add(val);
			else {
				int index = Collections.binarySearch(list, val);
				if (index < 0) index = (index + 1) * -1;
				list.set(index, val);
			}
		}
		System.out.println(N - list.size());
	}
}
