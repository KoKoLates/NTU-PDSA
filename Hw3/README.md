## Warriors
`2022PDSA`、 `stack` 、 `warriors`

There are warriors standing in a line. Each warrior has two important properties: `STH` and `RNG` . STH stands for “strength”, the power of each warrior. RNG stands for “range”, the effective radius that the warrior can attack.

Suppose that there are N warriors in this contest. An index i (i=0, 1, …, N-1) indicates one’s position (an integer coordinate). An attack will be blocked if there is a warrior with a higher or equal STH within its RNG distance. Formally speaking, let `{s0, s1, …, sN−1}` be the sequence of STH for the N warriors, and `{r0, r1, …, rN−1}` be the sequence of RNG for them. Then the i-th warrior can attack the j-th warrior if and only if the following conditions are satisfied:
* `|j−i|≤ri`
* `sj<si`
* `{sk}<si` for all k between i and j
* `ai` the index of the leftmost standing warrior that the i-th warrior can attack.
* `bi` the index of the rightmost standing warrior that the i-th warrior can attack.

Please determine the sequence of pairs `{(a0, b0), …, (aN−1, bN−1)}` .

## Template
```java
import java.util.Arrays;

class Warriors {
    public int[] warriors(int[] strength, int[] range) {
        // Given the attributes of each warrior and output the minimal and maximum 
        // index of warrior can be attacked by each warrior.
        return ....; // complete the code by returning an int[]
    }

    public static void main(String[] args) {
        Warriors sol = new Warriors();
        System.out.println(Arrays.toString(
            sol.warriors(new int[] {11, 13, 11, 7, 15},
                         new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
    }
}
```

## Test Data
Time Limit: `0.3s`
* `N` is the number of warriors. 
* `0 <= strength <= 1000000000`
* `0 <= attack_range <= M`
* We guarantee the length of STH is always equal to the length of RNG.

Case:
* case1: 20 points: `N <= 10, M < 10`
* case2: 20 points: `N <= 200000, M <= 200000`
* case3: 20 points: `N <= 10000, M <= 5000`
* case4: 20 points: `N <= 400000, M <= 200000`
* case5: 20 points: `N <= 1000000, M <= 500000`