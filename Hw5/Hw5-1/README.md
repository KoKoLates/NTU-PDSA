#  Warriors - Top k Kings
`2022PDSA` 、 `stack` 、 `Priority Queue` 、 `top k-th kings`

Suppose that there are N warriors in this contest. An index i (i=0, 1, …, N-1) indicates one’s position (an integer coordinate). An attack will be blocked if there is a warrior with a higher or equal STH within its RNG distance. Formally speaking, let `{s0, s1, …, sN−1}` be the sequence of STH for the N warriors, and `{r0, r1, …, rN−1}` be the sequence of RNG for them. Then the i-th warrior can attack the j-th warrior if and only if the following conditions are satisfied:
* `|j−i|≤ri`
* `sj<si`
* `{sk}<si` for all k between i and j

if a warrior cannot be knocked down under any circumstances, we call them “Kings”.

So again there are N warriors in this contest. An index i (i=0, 1, …, N-1) indicates one’s position (an integer coordinate). Suppose that there are M Kings among all warriors, we wish you to find them and return the indexes of the top k strongest kings in the descending order of strength. If there are more than two kings with the same strength, return them in the order of ascending indices.
if K is larger than the number of kings, just return all the kings.

## Template
```java
import java.util.Arrays;

class King{
    // optional, for reference only  
    int Strength;
    int Range;
    int Index;
    King(int str,int rng, int i){
        Strength=str;
        Range=rng;
        Index=i;
    }
}

class Kings {
    public Kings(int[] strength, int[] range){
        // Given the attributes of each warrior
    }
    public int[] topKKings(int k) {
          
        return ....; 
        // complete the code by returning an int[]
        // remember to return the array of indexes in the descending order of strength         
    }

    public static void main(String[] args) {
        Kings sol = new Kings(new int[] {15, 3, 26, 2, 5, 19, 12, 8}
                                       , new int[] { 1, 6, 1, 3, 2, 0, 1, 5});
        System.out.println(Arrays.toString(sol.topKKings(3)));
        // In this case, the kings are [0, 2, 4, 5, 6] (without sorting, only by the order of ascending indices)
        // Output: [2, 5, 0]
    }
}
```
## Test Data
Time Limit: `0.5s`
* `N` is the number of warriors
* `0 <= strength <= 1000000000`
* `0 <= attack_range <= M`
* We guarantee the length of STH is always equal to the length of RNG.

Case:
* case1: 20 points: `N <= 10, M < 10`
* case2: 20 points: `N <= 200000, M <= 200000`
* case3: 20 points: `N <= 10000, M <= 5000`
* case4: 20 points: `N <= 400000, M <= 200000`
* case5: 20 points: `N <= 1000000, M <= 500000`