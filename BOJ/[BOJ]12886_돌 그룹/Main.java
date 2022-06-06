import java.io.*;
import java.util.*;

public class Main {

    public static int A, B, C, sum;
    public static boolean[][] visited = new boolean[1501][1501];
    public static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = parse(st.nextToken());
        B = parse(st.nextToken());
        C = parse(st.nextToken());

        q.add(new int[]{A, B, C});

        visited[A][B] = true;
        sum = A + B + C;

        if (sum % 3 != 0) {
            System.out.println(0);
            return;
        }
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (now[i] < now[j]) {
                        int a = now[i] * 2;
                        int b = now[j] - now[i];

                        if (visited[a][b]) continue;
                        visited[a][b] = true;
                        q.add(new int[]{a, b, sum - a - b});
                    }
                }
            }
        }

        System.out.println(visited[sum / 3][sum / 3] ? 1 : 0);
    }

    public static int parse(String s) {
        return Integer.parseInt(s);
    }
}