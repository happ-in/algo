class Solution {
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int answer;

    public int solution(int n, String[] data) {
        answer = 0;
        combination(data, 1<<8, "");
        return answer;
    }

    public void combination(String[] data, int visited, String s) {
        if (s.length() == 8) {
            boolean flag = true;
            for (String datum : data) {
                if (!check(datum, s)) {
                    flag = false;
                    break;
                }
            }
            if (flag) answer++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if ((visited & 1<<i) != 0) continue;
            combination(data, (visited | 1<<i), s + friends[i]);
        }
    }

    public boolean check(String data, String s) {
        char[] arr = data.toCharArray();
        int friend1 = s.indexOf(arr[0]);
        int friend2 = s.indexOf(arr[2]);
        int gap = Math.abs(friend1 - friend2) - 1;
        int num = arr[4] - '0';
        switch (arr[3]) {
            case '=':
                return gap == num;
            case '>':
                return gap > num;
            default:
                return gap < num;
        }
    }
}