import java.util.Arrays;

class Solution {
	public String solution(int[][] scores) {
		int[] grade = new int[101];
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < scores.length; i++) {
			int min = 101;
			int max = 0;
			double sum = 0;
			int divide = scores.length;
			Arrays.fill(grade, 0);
			for (int j = 0; j < scores[i].length; j++) {
				grade[scores[j][i]]++;
				min = Math.min(scores[j][i], min);
				max = Math.max(scores[j][i], max);
				sum += scores[j][i];
			}

			if ((scores[i][i] == max && grade[max] == 1) || (scores[i][i] == min && grade[min] == 1)) {
				sum -= scores[i][i];
				divide--;
			}
			sum /= divide;

			if (sum >= 90) answer.append("A");
			else if (sum >= 80) answer.append("B");
			else if (sum >= 70) answer.append("C");
			else if (sum >= 50) answer.append("D");
			else answer.append("F");
		}

		return answer.toString();
	}
}