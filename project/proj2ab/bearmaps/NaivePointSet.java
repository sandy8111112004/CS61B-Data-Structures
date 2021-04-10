package bearmaps;

import java.util.*;

public class NaivePointSet implements PointSet{
    private List<Point> points;

    public NaivePointSet(List<Point> points){
        this.points = new ArrayList<Point>();
        for(Point p:points){
            this.points.add(p);
        }
    }

    @Override
    public Point nearest(double x, double y){
        Point target = new Point(x,y);
        double distance = Point.distance(points.get(0),target);
        int resultInd=0;
        for(int i=0;i<points.size();i++){
            if(Point.distance(points.get(i),target)<distance){
                distance = Point.distance(points.get(i),target);
                resultInd=i;
            }
        }
        return points.get(resultInd);
    }
}
