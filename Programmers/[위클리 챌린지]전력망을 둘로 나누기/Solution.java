import java.util.*;

class Solution {
    
    public static boolean[][] conn;
    
    public int solution(int n, int[][] wires) {
        conn = new boolean[n+1][n+1];
        for (int[] wire : wires) {
            conn[wire[0]][wire[1]] = true;
            conn[wire[1]][wire[0]] = true;
        }
        
        int gap = 9999999;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=0; i<wires.length; i++) {
            int[] wire = wires[i];
            boolean[][] visited = new boolean[n+1][n+1];
            
            int a = 0;
            int b = 0;
            
            // disconnect
            conn[wire[0]][wire[1]] = false;
            conn[wire[1]][wire[0]] = false;
            
            // 2 islands each size
            q.add(1);
            while (!q.isEmpty()) {
                int now = q.poll();
                a++;
                
                for (int k=1; k<=n; k++) {
                    if (!visited[k][now] && conn[now][k]) {
                        visited[k][now] = true;
                        visited[now][k] = true;
                        q.add(k);
                    }
                }
            }
            b = n - a;
            
            gap = Math.min(gap, Math.abs(a-b));
            
            // connect again
            conn[wire[0]][wire[1]] = true;
            conn[wire[1]][wire[0]] = true;
        }
        
        return gap;
    }
}
