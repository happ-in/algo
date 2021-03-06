# [백준 11066번: 파일 합치기 📂](https://www.acmicpc.net/problem/11066)

## sudo ✍  
제일 작은 수들 끼리 골라서 합치면 되는 거 아니야? 했더니 답이 다르게 나왔다.  
이 문제의 키포인트는 **연속된 파일들끼리 합치기**였다.  

우선, 직접 그림을 그리며 어떻게 나온 건지 확인을 했다.   
![image](https://user-images.githubusercontent.com/36289638/109290970-b0531200-786b-11eb-8d4a-4042aaf9cb19.png)


<br/> 

이걸 코드로 어떻게 해야할까 고민을 하다가 세그먼트 트리가 떠올라서 조금 응용해봤다.  
세그먼트 트리에 대한 설명이 필요하다면 [여기](https://bin-park.tistory.com/24)를 클릭하세요!  
음 글로 작성한 것보다 사실 시간초과 코드를 보는게 좀 더 직관적으로 이해가 쉬운 것 같다.

<br/> 

### 123번째 쯤 도전 [[시간초과 코드보기]](https://colorscripter.com/s/FKKWNfZ)  
<img src="https://user-images.githubusercontent.com/36289638/109291264-1c357a80-786c-11eb-9d68-737ec1f39a0d.png" width="300px">  

1. 만약 A~C까지의 최소 합을 구하려면 A~A, B~C 또는 A~B, C~C로 나뉜다.
    * 중간 구간을 거쳐오는 값을 갱신하며 전체의 최소합을 구한다.
2. 이를 응용해서 재귀로 memorization을 하며 트리를 만들어 나간다.  
3. 시간초과 실패 떙 😉  

<br/>

흠.. 내용은 위의 내용을 기반으로 반복문을 돌리면 될 것 같다고 생각했다. 그치만 말처럼 쉽지 않은 작업이었다. 왜냐면 내 머리가 굳었기 때문이지...😟  

<br/>

### 311987번째 성공 💙  
1. 위의 내용과 일치
2. 이를 응용해 반복문으로 dp를 구현한다.
3. 성공!!!🥳🎉🎊🎈  

<br/>

## algorithm 💻  
생각보다 구현 코드는 짧다.


1. 만약 A ~ C 까지의 최소 합을 구하려면 A ~ A, B ~ C 또는 A ~ B, C ~ C 로 나뉜다.
    * 중간 구간을 거쳐오는 값을 갱신하며 전체의 최소합을 구한다.
2. 이를 응용해서 재귀로 memorization을 하며 트리를 만들어 나간다

    ```java
    for (int y = 2; y < N; y++) {
        for (int x = y - 1; x > 0; x--) {
            dp[x][y] = INF;
            for (int k = x; k < y; k++) {
                dp[x][y] = Math.min(dp[x][y], dp[x][k] + dp[k+1][y] + sum[y] - sum[x-1]);
            }
        }
    }
    ```  

    여기서 sum 배열은 입력값의 구간합을 구한 배열이다. 두 값이 합쳐지면 두 구간의 합도 더해줘야하므로 sum[y] - sum[x-1]이라는 값이 더해졌다.  
