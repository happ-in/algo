import java.util.*;

class Solution {
    
    public int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        dfs(0, dungeons.length, k, dungeons, 0, new boolean[dungeons.length]);
        return answer;
    }
    
    public void dfs(int depth, int n, int k, int[][] dungeons, int count, boolean[] visited) {
        answer = Math.max(answer, count);
        if (depth == n) return;
        
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || k < dungeons[i][0]) continue;
            visited[i] = true;
            dfs(depth + 1, n, k - dungeons[i][1], dungeons, count + 1, visited);
            visited[i] = false;
        }
    }
}