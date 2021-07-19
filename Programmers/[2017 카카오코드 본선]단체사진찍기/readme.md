# [Programmers: 단체 사진 찍기 📷](https://programmers.co.kr/learn/courses/30/lessons/1835)



### sudo ✍🏻

이 문제는 그냥 단순하게 풀었다.



1. 모든 경우의 수를 구한다.
2. data의 조건이 모두 만족하는 값을 count 한다.



### algorithm 💻



1. 모든 경우의 수를 구한다.

```java
public void combination(String[] data, int visited, String s) {
        if (s.length() == 8) {
            // 종료 조건
            return;
        }

        for (int i = 0; i < 8; i++) {
            if ((visited & 1<<i) != 0) continue;
            combination(data, (visited | 1<<i), s + friends[i]);
        }
}
```



2. data의 조건이 모두 만족하면 count

```java
public void combination(String[] data, int visited, String s) {
    if (s.length() == 8) {
            boolean flag = true;
            for (String datum : data) {
                if (!check(datum, s)) {
                    flag = false;
                    break;
                }
            }
            if (flag) answer++;
            return;
        }
    
    // combination 코드
}

public boolean check(String data, String s) {
        char[] arr = data.toCharArray();
        int friend1 = s.indexOf(arr[0]);
        int friend2 = s.indexOf(arr[2]);
        int gap = Math.abs(friend1 - friend2) - 1;
        int num = arr[4] - '0';
        switch (arr[3]) {
            case '=':
                return gap == num;
            case '>':
                return gap > num;
            default:
                return gap < num;
        }
}
```

