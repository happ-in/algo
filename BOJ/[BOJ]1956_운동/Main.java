import java.io.*;
import java.util.*;

public class Main {

    public static int ANS = Integer.MAX_VALUE;
    public static int INF = Integer.MAX_VALUE;

    public static int V, E;
    public static int[][] roads;
    public static int[][] visited;

    public static StringTokenizer st;
    public static BufferedReader br;

    public static void main(String[] args) throws Exception {
        //== 변수 초기화 START ==//
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");

        V = parse(st.nextToken());
        E = parse(st.nextToken());

        roads   = new int[V + 1][V + 1];
        visited = new int[V + 1][V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());
            int c = parse(st.nextToken());
            roads[a][b] = c;
        }
        //== 변수 초기화 END ==//

        for (int start = 1; start <= V; start++) {
            for (int end = 1; end <= V; end++) {
                for (int mid = 1; mid <= V; mid++) {
                    if (start == mid || mid == end) continue;
                    if (roads[start][mid] == 0 || roads[mid][end] == 0) continue; // not connected
                    int cost = roads[start][mid] + roads[mid][end];
                    roads[start][end] = roads[start][end] == 0 ? cost : Math.min(roads[start][end], cost);
                    if (start == end) ANS = Math.min(ANS, roads[start][end]);
                }
            }
        }

        System.out.println(ANS == INF ? -1 : ANS);
    }

    public static int parse(String s) {
        return Integer.parseInt(s);
    }
}
