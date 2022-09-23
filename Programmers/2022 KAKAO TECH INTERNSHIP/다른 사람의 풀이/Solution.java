import java.util.*;

class Solution {

    public static int[] scores = {0, 3, 2, 1, 0, 1, 2, 3};
    public static char[][] types = {{'R', 'T'}, {'C', 'F'}, {'J','M'}, {'A', 'N'}};

    public String solution(String[] survey, int[] choices) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 4; i++) for (int j = 0; j < 2; j++) map.put(types[i][j], 0);

        for (int i = 0, len = survey.length; i < len; i++) {
            char disagree = survey[i].charAt(0); // 비동의
            char agree    = survey[i].charAt(1); // 동의

            if (choices[i] == 4) continue; // 0점
            else if (choices[i] < 4) map.put(disagree, map.get(disagree) + scores[choices[i]]);
            else map.put(agree, map.get(agree) + scores[choices[i]]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char type1 = types[i][0];
            char type2 = types[i][1];

            int score1 = map.get(type1);
            int score2 = map.get(type2);

            if (score1 == score2) sb.append(Math.min(type1, type2) + 'A');
            else if (score1 < score2) sb.append(type2);
            else sb.append(type1);
        }

        return sb.toString();
    }
}