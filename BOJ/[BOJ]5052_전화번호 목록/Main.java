import java.io.*;
import java.util.*;

public class Main {

    public static int t, n;
    public static ArrayList<String> q = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = parse(br.readLine());

        for (int i = 0; i < t; i++) {
            q.clear();
            n = parse(br.readLine());

            boolean flag = true;
            for (int j = 0; j < n; j++) q.add(br.readLine());
            Collections.sort(q, (o1, o2) -> o1.length() - o2.length()); // 길이가 작은대로

            Trie trie = new Trie();
            for (int j = 0; j < n; j++) {
                flag = trie.insert(q.get(j));
                if (flag) {
                    sb.append("NO").append("\n");
                    break;
                }
            }
            if (!flag) sb.append("YES").append("\n");
        }

        System.out.print(sb.toString());
    }

    public static int parse(String s) {
        return Integer.parseInt(s);
    }

    public static class Node {
        boolean flag;
        Map<Character, Node> child = new HashMap<>();
    }

    public static class Trie {
        Node root = new Node();

        public boolean insert (String str) {
            Node node = this.root;

            for (int i = 0, len = str.length(); i < len; i++) {
                if (node.flag) return true;                        // 현재 위치까지인 단어가 존재한다는 것은 일관성이 없는 목록
                if (!node.child.containsKey(str.charAt(i))) {      // 현재 글자 다음 글자가 없다면 child로 가지 뻗기
                    node.child.put(str.charAt(i), new Node());
                }
                node = node.child.get(str.charAt(i));
            }

            node.flag = true;
            return false;
        }
    }
}