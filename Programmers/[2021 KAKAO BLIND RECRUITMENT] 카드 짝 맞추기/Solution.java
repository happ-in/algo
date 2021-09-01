import java.awt.*;
import java.util.*;

class Solution {

	static Set<Integer> set = new HashSet<>();
	static int size = 0;
	static int answer = Integer.MAX_VALUE;
	static int[] orders;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public int solution(int[][] board, int r, int c) {
		for (int[] bd : board) for (int j = 0; j < board[0].length; j++) if (bd[j] != 0) set.add(bd[j]);
		size = set.size();
		orders = new int[size];
		dfs(board, r, c, 0, new boolean[size]);
		return answer;
	}

	public void dfs(int[][] board, int r, int c, int index, boolean[] visited) {
		if (index == size) {
			bfs(board, r, c);
			return;
		}

		for (int i = 0; i < size; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			orders[index] = i + 1;
			dfs(board, r, c, index + 1, visited);
			visited[i] = false;
		}
	}
	
	public void bfs(int[][] board, int r, int c) {
		int[][] copyBoard = copy(board);
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 2; j++) {
				int[] move = moveCount(copyBoard, r, c, orders[i]);
				r = move[0];
				c = move[1];
				count += move[2];
			}
		}
		answer = Math.min(answer, count);
	}

	public int[] moveCount(int[][] copyBoard, int r, int c, int order) {
		int count = 0;
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[4][4];
		q.add(new int[]{r, c, 0});
		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (copyBoard[now[0]][now[1]] == order) {
				r = now[0];
				c = now[1];
				count += now[2] + 1;
				copyBoard[r][c] = 0;
				break;
			}
			for (int j = 0; j < 4; j++) {
				Point next = straightGo(copyBoard, now[0], now[1], j);
				if (visited[next.x][next.y]) continue;
				visited[next.x][next.y] = true;
				q.add(new int[]{next.x, next.y, now[2] + 1});
			}
			for (int j = 0; j < 4; j++) {
				int nx = now[0] + dx[j];
				int ny = now[1] + dy[j];
				if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				q.add(new int[]{nx, ny, now[2] + 1});
			}
		}
		return new int[]{r, c, count};
	}

	public int[][] copy(int[][] board) {
		int[][] copyBoard = new int[4][4];
		for (int i = 0; i < 4; i++) System.arraycopy(board[i], 0, copyBoard[i], 0, 4);
		return copyBoard;
	}
	
	public Point straightGo(int[][] board, int x, int y, int dir) {
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
			x = nx;
			y = ny;
			if (board[x][y] != 0) break;
		}
		return new Point(x, y);
	}
}