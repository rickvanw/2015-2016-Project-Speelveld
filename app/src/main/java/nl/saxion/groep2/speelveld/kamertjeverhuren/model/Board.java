package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import java.util.ArrayList;

/**
 * Created by thymengroen on 19-05-16.
 */
public class Board {
    private ArrayList<Point> points = new ArrayList<>();

    public ArrayList<Point> getPoints(){
        return points;
    }
    //adds points to Arraylist Point
    public Point addPoint(int x, int y){
        Point p = new Point(x,y);
        points.add(p);
        return p;
    }
    //generates points with x & y
    public void generatePoints(int width, int heigth, int pointAmountx, int pointAmountY){

        System.out.println("height"+ heigth);
        System.out.println("width" + width);
        int spaceBetweenPointsX = width/(pointAmountx + 2);
        int spaceBetweenPointsY = heigth/(pointAmountY + 2);

        for (int x = 0; x < pointAmountx;x++){

            for (int y = 0; y < pointAmountY;y++){
                int pointX = (spaceBetweenPointsX * x) + spaceBetweenPointsX;
                int pointY = (spaceBetweenPointsY * y) + spaceBetweenPointsY;
                Point point = new Point(pointX, pointY);
                points.add(point);
            }
        }
    }
}
