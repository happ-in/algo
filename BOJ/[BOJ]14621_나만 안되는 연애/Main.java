import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[] sex;
    static PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);  // 거리가 짧은 순서

    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = parse(st.nextToken());
        M = parse(st.nextToken());
        sex = br.readLine().replaceAll(" ", "").toCharArray();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = parse(st.nextToken());
            int to = parse(st.nextToken());
            int cost = parse(st.nextToken());
            q.add(new Node(from, to, cost));
        }
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) parent[i] = i;

        int count = 0;
        int ans = 0;
        while (!q.isEmpty()) {
            if (count == N-1) break;        // 간선이 N-1라는 것은 모든 루트가 이어졌다는 의미
            Node now = q.poll();
            if (union(now.to, now.from)) {  // 같은 성별이거나 이미 이어져있는 경우는 pass
                ans += now.cost;
                count++;
            }
        }
        System.out.println(count == N-1 ? ans : -1);
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return find(parent[x]);
    }

    static boolean union(int x, int y) {
        if (sex[x-1] == sex[y-1]) return false;
        x = find(x);
        y = find(y);
        if (x == y) return false;
        else if (x < y) parent[x] = y;
        else parent[x] = y;
        return true;
    }

    static int parse(String s) { return Integer.parseInt(s); }

    private static class Node {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
