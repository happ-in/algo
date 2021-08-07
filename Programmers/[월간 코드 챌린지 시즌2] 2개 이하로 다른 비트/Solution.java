import java.util.ArrayList;

public class Solution {

	public long[] solution(long[] numbers) {
		ArrayList<Long> arr = new ArrayList<>();
		for (long number : numbers) {
			long ans = 0;
			if (binaryLen(number) != binaryLen(number + 1)) {
				ans = (number << 1) + 1;
				ans -= (1L << (Long.toBinaryString(number).length()-1));
			} else {
				int len = 0;
				for (int i = 0; i <= Long.toBinaryString(number).length(); i++) {
					if ((number & (1L << i)) != 0) continue;
					ans = number | (1L << i);
					len = i;
					break;
				}
				for (int i = len - 1; i >= 0; i--) {
					if ((number & (1L << i)) == 0) continue;
					ans -= (1L << i);
					break;
				}
			}
			arr.add(ans);
		}
		long[] answer = new long[numbers.length];
		for (int i = 0; i < numbers.length; i++) answer[i] = arr.get(i);
		return answer;
	}

	public int binaryLen(long num) {
		return Long.toBinaryString(num).length();
	}
}