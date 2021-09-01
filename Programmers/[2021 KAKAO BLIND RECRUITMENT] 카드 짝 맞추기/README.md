# [Programmers : 카드 짝 맞추기 🃏](https://programmers.co.kr/learn/courses/30/lessons/72415?language=java)



이상한 곳에서 초기화 잘못해서 계속 틀렸던 문제... :(

하나씩 생각해보면 딱히 어렵진 않았다.



<br/>



### 아이디어 ✍🏻

1. 카드들이 몇 개 있는지 체크
2. 어떤 카드 먼저 찾을 지 조합구하기
3. 현재 위치에서 조합 순서대로 가까운 카드 골라서 없애기



<br/>



### 소스코드 💻

1. 카드가 총 몇 개인지 체크

   ```java
   Set<Integer> set = new HashSet<>();
   int size = 0;
   
   for (int[] bd : board) 
       for (int j = 0; j < board[0].length; j++) 
           if (bd[j] != 0) 
               set.add(bd[j]);
   
   size = set.size();
   ```

   * HashSet을 사용하여  (r, c) 위치에 값이 0이 아닌 값들을 넣어줌으로써 카드의 갯수를 세어줬다.

   

2. 1 ~ szie까지의 카드 중 어떤 카드를 먼저 뒤집을 것인지 조합을 구한다.

   ```java
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
   
   ```

   

3. 현재 위치에서 조합 순서대로 가까운 카드 골라서 없애기

   ```java
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
   ```

   - 간단히 로직을 살펴보면 BFS!
   - 카드 2개가 한 쌍이므로 moveCount가 2번 실행되는 것을 확인할 수 있다.

   ```java
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
   ```

   - moveCount의 코드를보면 사방탐색을 2번한다.
   - 그 이유는 단순히 한 칸씩 상하좌우 탐색이 가능하기도 하지만, ctrl + 상하좌우의 직진 움직임도 1칸으로 이동으로 쳐지기 때문이다.
   - straightGo의 코드는 현재 방향에 카드가 있으면 그 위치를 반환하고 카드가 없다면 가장 가장자리 위치를 반환하는 코드다.

   

