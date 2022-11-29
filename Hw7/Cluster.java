import java.util.*;
import java.util.List;

class Cluster {
    /**
     * The object Point to store the point data info and method like
     * distance calculate, compare and find the centroid of two points.
     */
    private static class Point implements Comparable<Point> {
        private final int weight;
        private final double x, y;
        public static final Comparator<Point> X_ORDER = new XOrder();
        public static final Comparator<Point> Y_ORDER = new YOrder();
        public Point(double x, double y, int weight) {
            this.x = x; this.y = y; this.weight = weight;
        }

        /**
         * Calculate the distance between two points.
         * @param p1 the point1.
         * @param p2 the point2.
         * @return the distance between point1 and point2.
         */
        public static double distance(Point p1, Point p2) {
            return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
        }

        /**
         * Find the centroid between of two points.
         * @param p1 the point1.
         * @param p2 the point2.
         * @return the centroid point of point1 and point2.
         */
        public static Point findCentroid(Point p1, Point p2) {
            double x = ((p1.x * p1.weight + p2.x * p2.weight) / (p1.weight + p2.weight));
            double y = ((p1.y * p1.weight + p2.y * p2.weight) / (p1.weight + p2.weight));
            return new Point(x, y, (p1.weight + p2.weight));
        }

        public int compareTo(Point that) {
            return this.y == that.y ? Double.compare(this.x, that.x) : Double.compare(this.y, that.y);
        }

        private static class XOrder implements Comparator<Point> {
                public int compare(Point p1, Point p2) {return Double.compare(p1.x, p2.x);}
        }

        private static class YOrder implements Comparator<Point> {
                public int compare(Point p1, Point p2) {return Double.compare(p1.y, p2.y);}
            }
    }

    /**
     * Using the divide and conquer method to find the closest pair.
     */
    private static class ClosestPair {
        public Point p1, p2;
        public double min = Double.POSITIVE_INFINITY;

        /**
         * Constructor for the Closest pair object.
         * @param points  The points to be found.
         * @param n the size (length) of points.
         */
        public ClosestPair(ArrayList<Point> points, int n) {
            Point[] px = new Point[n], py = new Point[n], aux = new Point[n];
            for(int i = 0; i < n; i++) px[i] = points.get(i);
            Arrays.sort(px, Point.Y_ORDER);
            Arrays.sort(px, Point.X_ORDER);
            System.arraycopy(px, 0, py, 0, n);
            partition(px, py, aux, 0, n - 1);
        }

        private double partition(Point[] px, Point[] py, Point[] aux, int lo, int hi) {
            if(hi <= lo) return Double.POSITIVE_INFINITY;
            int mid = lo + (hi - lo) / 2;
            Point middle = px[mid];

            double d1 = partition(px, py, aux, lo, mid);
            double d2 = partition(px, py, aux, mid + 1, hi);
            double d = Math.min(d1, d2);
            merge(py, aux, lo, mid, hi);

            int m = 0;
            for(int i = lo; i <= hi; i++) {
                if(Math.abs(py[i].x - middle.x) < d) aux[m++] = py[i];
            }

            for(int i = 0; i < m; i++) {
                for(int j = i + 1; (j < m) && (aux[j].y - aux[i].y < d); j++) {
                    double distance = Point.distance(aux[i], aux[j]);
                    if(distance < d) {
                        d = distance;
                        if(distance < min) { min = distance; p1 = aux[i]; p2 = aux[j]; }}
                }
            }
            return d;
        }

        private void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
            if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
            int i = lo, j = mid + 1;
            for(int k = lo; k <= hi; k++) {
                if(i > mid) a[k] = aux[j++];
                else if(j > hi) a[k] = aux[i++];
                else if(aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }

    }

    public List<double[]> cluster(List<int[]> points, int cluster_num) {
        ArrayList<Point> p = new ArrayList<>();
        for(int[] i: points) p.add(new Point(i[0], i[1], 1));

        while (p.size() > cluster_num) {
            ClosestPair closestPair = new ClosestPair(p, p.size());
            p.add(Point.findCentroid(closestPair.p1, closestPair.p2));
            p.remove(closestPair.p1); p.remove(closestPair.p2);
        }

        ArrayList<double[]> ans = new ArrayList<>();
        for(int i = 0; i < cluster_num; i++) {
            ans.add(new double[]{p.get(i).x, p.get(i).y});
        }
        ans.sort(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return o1[0] == o2[0] ? Double.compare(o1[1], o2[1]) : Double.compare(o1[0], o2[0]);
            }
        });
        return ans;
    }
}