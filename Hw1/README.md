# Board Game
`2022PDSA`

## Judge
Grading Status:
* AC: ACcepted
* TLE: Time Limit Excess
* WA: Wrong Answer
* RE: Runtime Error (Mostly your program raise some error unexpectly)

Memory: 1G

Handin the file with utf-8 encoding if there are 中文 words inside your code (including comments).

## Board Game
Play a board game. The playing pieces in our board game are called stones. There are two types of stones: `O` and `X`. A set of stones can be flipped if the connected stones are surrounded by another type of stones (not including border).

We define connected as two stones are next to each other in vertically or horizontally.

For example:
```
.XXOX
XOXO.
XXOX.
XXOXX
..XOX
```

After the flipStone('O') operation:
```
.XXOX
XXXO.
XXXX.
XXXXX
..XOX
```

## Template
``` java
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BoardGame {
    // create a board of size h*w
    public BoardGame(int h, int w)
    // put stones on the board according to the coordinates
    public void putStone(int[] x, int[] y, char stoneType) 
    // Answer if the stone and its connected stones are surrounded by another type of stones
    public boolean surrounded(int x, int y) 
    // Get the type of the stone at (x,y)
    public char getStoneType(int x, int y) 
}
```

## Example
Given a board 
```
...    ...    .X.    .X.    .X.
... -> .O. -> XOX -> XOX -> XOX
...    ...    ...    .X.    OX.
```

``` java
public static void main(String args[]){ 
    BoardGame g = new BoardGame(3,3); 
    g.putStone(new int[]{1}, new int[]{1}, 'O');
    System.out.println(g.surrounded(1, 1)); 
    System.out.println(g.countConnectedRegions()); 
       
    g.putStone(new int[]{0, 1, 1}, new int[]{1, 0, 2}, 'X');
    System.out.println(g.surrounded(1, 1)); 
    System.out.println(g.countConnectedRegions()); 

    g.putStone(new int[]{2},new int[]{1}, 'X');
    System.out.println(g.surrounded(1, 1));
    System.out.println(g.surrounded(2, 1)); 
    System.out.println(g.countConnectedRegions()); 

    g.putStone(new int[]{2}, new int[]{0}, 'O');
    System.out.println(g.surrounded(2, 0)); 
    System.out.println(g.countConnectedRegions()); 
}

-> 

False
1
False
4
True
False
5
False
6
```

Note the stone at the corner will never be surrounded.

More example
```
# Both O are not surrounded by X
# There are 5 ConnectedRegions.

.X.
XOOX
.XX.
```
```
# Both O are surrounded by X
# There are 5 ConnectedRegions.

.XX
XOOX
.XX.
```
```
# Neither O or X is surrounded
# There are 6 ConnectedRegions.

OXO
XOX
```
```
# Neither O or X is surrounded
# There are 3 ConnectedRegions.

.XXXX
XO.OX
XXXXX
```

## TestCase
Time Limit 2s

* `N` is the number of stones on the board.
* `M` is the total times we call `putStone` or `surrounded`
* We guarantee there is stone at (x,y) when we call surrounded(x,y)
* And there is no stone at (x,y) when we call putStone(x,y).


Total 100 points (Not sure)
* 20 points: `w,h < 3` `M < 100` Simple
* 20 points: `w,h <= 1001` `M < 7000` Specical Case
* 20 points: `w,h <= 101` `M < 1000` 
* 20 points: `w,h <= 101` `M < 4000`
* 20 points: `w,h <= 10001` `M < 4000`
