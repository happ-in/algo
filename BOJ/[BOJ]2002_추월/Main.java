import java.io.*;
import java.util.*;

public class Main {

    public static int N, ans, front;
    public static Queue<String> youngsik = new ArrayDeque<>();
    public static Queue<String> daegeun = new ArrayDeque<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) daegeun.add(br.readLine().trim());
        for (int i = 0; i < N; i++) youngsik.add(br.readLine().trim());

        while (!youngsik.isEmpty()) {
            String ys = youngsik.poll();
            front = 0;
            for (int i = 0, size = daegeun.size(); i < size; i++) {
                String dg = daegeun.poll();
                if (dg.equals(ys) && front == 0) break;
                else if (dg.equals(ys)) ans++;
                else {
                    front++;
                    daegeun.add(dg);
                }
            }
        }
        System.out.println(ans);
    }
}
