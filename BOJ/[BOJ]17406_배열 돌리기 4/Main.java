import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A;
    static ArrayList<int[]> arr = new ArrayList<>();
    static int[] comb;
    static Queue<Integer> q = new ArrayDeque<>();
    static int ans = Integer.MAX_VALUE;

    static int[][] copyA;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken());
        M = parse(st.nextToken());
        K = parse(st.nextToken());
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) A[i][j] = parse(st.nextToken());
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = parse(st.nextToken()) - 1;
            int c = parse(st.nextToken()) - 1;
            int s = parse(st.nextToken());
            arr.add(new int[]{r, c, s});
        }

        copyA = new int[N][M];
        comb = new int[K];
        combination(0, 1<<K);
        System.out.println(ans);
    }

    static void combination(int index, int visited) {
        if (index == K) {
            rotation();
            return;
        }
        for (int i = 0; i < K; i++) {
            if ((visited & (1 << i)) != 0) continue;
            comb[index] = i;
            combination(index+1, visited | (1 << i));
        }
    }

    static void copy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyA[i][j] = A[i][j];
            }
        }
    }

    static void rotation() {
        copy();
        for (int index : comb) {
            int[] rcs = arr.get(index);
            int r = rcs[0];
            int c = rcs[1];
            int s = rcs[2];
            for (int i = 0; i < s; i++) {
                for (int j = c-s+i; j < c+s-i; j++) q.add(copyA[r-s+i][j]);
                for (int j = r-s+i; j < r+s-i; j++) q.add(copyA[j][c+s-i]);
                for (int j = c+s-i; j > c-s+i; j--) q.add(copyA[r+s-i][j]);
                for (int j = r+s-i; j > r-s+i; j--) q.add(copyA[j][c-s+i]);

                for (int j = c-s+i+1; j <= c+s-i; j++) copyA[r-s+i][j] = q.poll();
                for (int j = r-s+i+1; j <= r+s-i; j++) copyA[j][c+s-i] = q.poll();
                for (int j = c+s-i-1; j >= c-s+i; j--) copyA[r+s-i][j] = q.poll();
                for (int j = r+s-i-1; j >= r-s+i; j--) copyA[j][c-s+i] = q.poll();
            }
        }

        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, Arrays.stream(copyA[i]).sum());
        }
    }

    static int parse(String s) {return Integer.parseInt(s);}
}
