# Flood
`2022PDSA`、`Dijkstra algorithm`、`Graph`

Severals villages are located in the remote mountain areas. There is only one reservoir in the mountain. The reservoir and all villages are connected with roads. The roads have specific directions, and not only do people follow them, but also the water does (because the road has a slope). One day, the reservoir burst and floodwater ran through all the villages along the roads at the same speed. Which village was the last one to be flooded?

* **The vertex of the reservoir is always `0`.**
* **If multiple villages are flooded at the same time, return the smallest index.**
* **Some villages may not be attacked by flood, we don’t care about them.**

## Template
```java
import java.util.List;
import java.util.ArrayList;

class Flood {
    public Flood() {};
    //return which village is the latest one flooded
    public int village(int villages, List<int[]> road) {

    }

    public static void main(String[] args) {
        Flood solution = new Flood();
        System.out.println(solution.village(4, new ArrayList<int[]>(){{
            add(new int[]{0,1,3});
            add(new int[]{0,2,6});
            add(new int[]{1,2,2});
            add(new int[]{2,1,3});
            add(new int[]{2,3,3});
            add(new int[]{3,1,1});
        }}));
        System.out.println(solution.village(6, new ArrayList<int[]>(){{
            add(new int[]{0,1,5});
            add(new int[]{0,4,3});
            add(new int[]{1,2,1});
            add(new int[]{1,3,3});
            add(new int[]{1,5,2});
            add(new int[]{2,3,4});
            add(new int[]{3,2,1});
            add(new int[]{4,0,2});
            add(new int[]{4,1,4});
            add(new int[]{5,0,3});
        }}));
    }
}
```
**Expected output:**
```
3
3
```

# Test Case

**Time Limit: 300ms**

* `N` is number of villages
* The ID of each island is bounded by `1 <= id < N`
* M is the total number of roads
* 1 <= roads length <= 10000
* We **do not** guarantee there is only one possible road from village a to village b.

**Cases:**

* case1: 20 points: N <= 4, M <= 10
* case2: 20 points: N <= 10001, M <= 20000(Special case)
* case3: 20 points: N <= 10, M <= 1000
* case4: 20 points: N <= 1000, M <= 100000
* case5: 20 points: N <= 10000, M <= 1000000