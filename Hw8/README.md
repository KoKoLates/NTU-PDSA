# Budget Control
`2022PDSA`、`Graph`、`prim MST`

You are the best architect who lives on Greed Island. Greed Island is composed of a lot of islands. In order to connect all the territories, the president asks you to help him. The president will give you the information that you need, including the total islands of a country and all the bridges that are possible to build, in the format of (island 1, island 2, cost). Your responsibility is to help president to connect all the territories in the lowest budget.
By the way, Greed Island is always in war. When a new goverment establishes, the number of the islands a country controls will change. The technology of building bridges evolves, too. Once the new leader takes over, he will destroies all bridges and ask you to reconnect all the territories.

**Definition of “connecting all islands” :**

You can always travel from one island to another island through one bridge or multiple bridges.

## Template
```java
import java.util.List;
import java.util.ArrayList;

class Budget {
    public Budget() {};
    //return the total costs of the bridges
    public int plan(int island, List<int[]> bridge) {

    }

    public static void main(String[] args) {
        Budget solution = new Budget();
        System.out.println(solution.plan(4, new ArrayList<int[]>(){{
            add(new int[]{0,1,2});
            add(new int[]{0,2,4});
            add(new int[]{1,3,5});
            add(new int[]{2,1,1});
        }}));
        System.out.println(solution.plan(4, new ArrayList<int[]>(){{
            add(new int[]{0,1,0});
            add(new int[]{0,2,4});
            add(new int[]{0,3,4});
            add(new int[]{1,2,1});
            add(new int[]{1,3,4});
            add(new int[]{2,3,2});
        }}));
    }
}
```
**Expected output:**
```
8
3
```
## Test Case
**Time Limit: 300ms**

* `N` is number of islands in the country
* The ID of each island is bounded by `0 <= id < N`
* `M` is the total number of bridges
0 <= cost <= 10000 (some bridges are sponsored by consortium)
* We **do not** guarantee there is only one possible bridge from island a to island b.
* There may be a bridge from island a to island a (useless but interesting).

**Cases:**

* case1: 20 points: N <= 4, M <= 10
* case2: 20 points: N <= 10001, M <= 20000(Special case)
* case3: 20 points: N <= 10, M <= 1000
* case4: 20 points: N <= 1000, M <= 100000
* case5: 20 points: N <= 10000, M <= 1000000