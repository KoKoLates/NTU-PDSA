# Long Jump
`Long Jump` 、 `Binary Search Tree`

It’s a special long jump competition. The winner isn’t the one who jumps the farest. The winners need to jump into the range we give. We have some contestants who already signed up, but still have some players who want to join the competition. Besides, the winner range will change everytime when we want to find the winners. The answer we need is the total distances that all the winners jump.

(A competition jump on the edge of the range we give is still a winner)

## Template
```java
class LongJump {
    
    
    public LongJump(int[] playerList){
        
    }
    // Add new player in the competition with different distance
    public void addPlayer(int distance) {
        
    }   
    // return the winners total distances in range[from, to]
    public int winnerDistances(int from, int to) {
        
    }   
    public static void main(String[] args) {
        
        LongJump solution = new LongJump(new int[]{2,5,6});
        System.out.println(solution.winnerDistances(3,10));
        solution.addPlayer(10);
        solution.addPlayer(8);
        System.out.println(solution.winnerDistances(3,10));
    }  
}
```
The Expected Output
```
11
29
```
## Test Case
**Time Limit: 450ms**

* Each sample has N competitors, M players who want to join.
* We will ask the total distances of all the winners jumps L times.
* We guarantee the distances of each competitors and players jump are unique and farther than `0` .
* We guarantee the range number `from` is smaller or equal to to and both of them are bigger than `0` .
* We will check your answer by `answer.equals(output)`.

**Cases:**

* case1: 20 points: N <= 10, M <= 10, L <= 10
* case2: 20 points: N <= 450, M <= 1000, L <= 2000 `Special Case(4 samples)`
* case3: 20 points: N <= 5000, M <= 7000, L <= 5000
* case4: 20 points: N <= 5000, M <= 20000, L <= 2500
* case5: 20 points: N <= 10000, M <= 30000, L <= 20000