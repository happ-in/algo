import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        String input = "";
        while ((input = br.readLine()) != null) {
            search(input);
        }
    }

    public static void search(String input) throws Exception {
        int x = parse(input) * 10000000;
        int n = parse(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = parse(br.readLine());
        Arrays.sort(arr);

        int left = 0;
        int right = n-1;
        while (left <  right) {
            long sum = arr[left] + arr[right];
            if (sum == x) {
                System.out.printf("yes %d %d %n", arr[left], arr[right]);
                return;
            } else if (sum < x) left++;
            else right--;
        }
        System.out.println("danger");
    }

    static int parse(String s) {
        return Integer.parseInt(s);
    }
}