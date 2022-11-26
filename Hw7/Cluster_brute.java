import java.util.*;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.ClosestPair;

class Cluster {
    public List<double[]> cluster(List<int[]> points, int cluster_num) {
        Point2D[] p = new Point2D[points.size()];
        for(int i = 0; i < points.size(); i++)
            p[i] = new Point2D(points.get(i)[0], points.get(i)[1]);

        HashMap<Point2D, ArrayList<Point2D>> map = new HashMap<>();
        for(Point2D i: p) map.put(i, new ArrayList<>(){{add(i);}});

        int n = p.length;
        while(n > cluster_num) {
            ClosestPair closestPair = new ClosestPair(p);
            int a = 0, b = 0;
            for(int i = 0; i < p.length; i++) {
                if(p[i].equals(closestPair.either())) {a = i; break;}
            }

            Point2D[] p1 = new Point2D[p.length - 1];
            for(int i = 0, j = 0; i < p.length; i++) if(i != a) p1[j++] = p[i];
            p = p1;

            for(int i = 0; i < p.length; i++)
                if(p[i].equals(closestPair.other())) { b = i; break;}

            Point2D[] p2 = new Point2D[p.length - 1];
            for(int i = 0, j = 0; i < p.length; i++) if(i != b) p2[j++] = p[i];
            p = p2;

            double x = 0, y = 0;
            for(Point2D i: map.get(closestPair.either())) {
                x += i.x(); y += i.y();
            }

            for(Point2D i: map.get(closestPair.other())) {
                x += i.x(); y += i.y();
            }

            x /= (map.get(closestPair.either()).size() + map.get(closestPair.other()).size());
            y /= (map.get(closestPair.either()).size() + map.get(closestPair.other()).size());

            Point2D[] p3 = new Point2D[p.length + 1];
            for(int i = 0; i < p3.length; i++) {
                if(i == p3.length - 1) {p3[i] = new Point2D(x, y); break;}
                p3[i] = p[i];
            }
            p = p3;

            ArrayList<Point2D> newCluster = new ArrayList<>();
            for(Point2D i: map.get(closestPair.either())) {
                newCluster.add(i); map.remove(i);
            }

            for(Point2D i: map.get(closestPair.other())) {
                newCluster.add(i); map.remove(i);
            }

            map.put(new Point2D(x, y), newCluster);
            n--;
        }

        Arrays.sort(p, Point2D.Y_ORDER); Arrays.sort(p, Point2D.X_ORDER);
        ArrayList<double[]> ans = new ArrayList<>();
        for(Point2D i: p) ans.add(new double[]{i.x(), i.y()});
        return ans;
    }
}