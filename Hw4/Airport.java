import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.GrahamScan;

class Airport {
    /**
     * Output the smallest average distance with optimal selection of airport location.
     * @param houses The houses location coordinate
     * @return The smallest average distance
     */
    public double airport(List<int[]> houses){
        if(houses.size() < 3) return 0;
        double avgDistance = Double.MAX_VALUE;
        Point2D[] town = new Point2D[houses.size()];

        // Loading the house location
        for(int i = 0; i < houses.size(); i++)
            town[i] = new Point2D(houses.get(i)[0], houses.get(i)[1]);

        // Find the extreme points on the convex hull by Graham Scan method.
        GrahamScan graham = new GrahamScan(town);
        ArrayList<Point2D> hullPoints = new ArrayList<>();
        for(Point2D i: graham.hull()) hullPoints.add(i);

        // Calculate the inner equivalents points for the town.
        double equal_x = 0, equal_y = 0;
        for(Point2D i: town) {equal_x += i.x(); equal_y += i.y();}
        equal_x /= town.length; equal_y /= town.length;

        for(int i = 0; i < hullPoints.size(); i++){
            double distance = 0;
            if(i == hullPoints.size() - 1)
                distance = distanceCalc(hullPoints, i, 0, equal_x, equal_y);
            else
                distance = distanceCalc(hullPoints, i, i + 1, equal_x, equal_y);

            if(Double.compare(distance, avgDistance) < 0) avgDistance = distance;
        }

        return avgDistance;
    }

    /**
     * Calculate the average distance for all possible straight line
     * @param arr The extreme points on the convex hull.
     * @param a The start point.
     * @param b The end point.
     * @param ex The inner equivalent x position.
     * @param ey The inner equivalent y position.
     * @return The average distance for indicate start and end points.
     */
    private double distanceCalc(ArrayList<Point2D> arr, int a, int b, double ex, double ey){
        double x = arr.get(a).x() - arr.get(b).x();
        double y = arr.get(a).y() - arr.get(b).y();
        double k = x * arr.get(a).y() - y * arr.get(a).x();
        return Math.abs((ex * y - ey * x + k) / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }
}