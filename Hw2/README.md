# Percolation
`2022PDSA`、 `Linked-List` 、 `percolation`

## Assignment: Percolation
Give a system, a grid of NxN sites, of which all the sites are initially blocked . Then, we open the site one-by-one and ask you to check if a site is full or the system is percolated. The row and column indices i and j are integers between 0 and N − 1, where (0, 0) is the upper-left site

## Definition
* `Open` : Turn a blocked site into an open site
* `Full` : a site can connect to the top row of the grid via a chain of neighboring (left, right, up, down) open sites.
* `Percolation` : There exists a full site at the bottom row of the grid.

## Template
```java
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class Percolation {
   
   private static class Node {
      private Point2D site;
      private Node next;
   }
    
   public Percolation(int N)               
   // create N-by-N grid, with all sites blocked
   public void open(int i, int j)          
   // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)     
   // is site (row i, column j) open?
   public boolean isFull(int i, int j)     
   // is site (row i, column j) full?
   public boolean percolates()             
   // does the system percolate?
   public Point2D[] PercolatedRegion()     
   // return the array of the sites of the percolated region in order (using Point2D default compareTo())
   // This function should always return the content of the percolated region AT THE MOMENT when percolation just happened.
}
```

## TestCase
Time Limit 0.25s

* We grantee the (i, j) is a open site when we call `isFull` . Note 0 <= i < N and 0 <= j < N
* We grantee we will `open` a blocked grid.
* We grantee the system is percolated when we call `PercolatedRegion` .
* The total number our judger will call your function should be smaller than `3N^2`.

Total 100 points
* 20 points: `N < 5` Easy
* 20 points: `N < 50` Special Case
* 20 points: `N < 50`
* 20 points: `N < 100`
* 20 points: `N < 500`