import java.util.*;

class Solution {
    
    public static String[] alphabets = {"A" , "E", "I", "O", "U"};
    public static int answer = 0;
    
    public int solution(String word) {
        dfs("", word);
        return answer;
    }
    
    public boolean dfs(String s, String word) {
        if (word.equals(s)) return true;
        if (s.length() == 5) return false;
        
        for (int i = 0; i < 5; i++) {
            answer++;
            if (dfs(s + alphabets[i], word)) return true;
        }
        
        return false;
    }
}
