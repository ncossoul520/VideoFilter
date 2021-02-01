import java.util.ArrayList;

public class Cluster {
    private Point center;
    private ArrayList<Point> colorPointList;

    public Cluster() {
        // Create empty list and randomly init center
        center = new Point();
        colorPointList = new ArrayList<>();
    }

    public void add(Point p) {
        colorPointList.add(p);
    }


    public Boolean calculateCenter() {
        if (size() == 0) { return false; }

        boolean stable;
        int avgR = 0, avgG = 0, avgB = 0;
        for (Point point : colorPointList) {
            avgR += point.getRed();
            avgG += point.getGreen();
            avgB += point.getBlue();
        }
        avgR /= size();
        avgG /= size();
        avgB /= size();

        stable = avgR == getCenter().getRed() && avgG == getCenter().getGreen() && avgB == getCenter().getBlue();

        center.setRed(   (short)avgR );
        center.setGreen( (short)avgG );
        center.setBlue(  (short)avgB );

        return stable;
    }

    public void clearPoints() {
        colorPointList.clear();
    }


    public Point getCenter() {
        return center;
    }

    public int size() {
        return colorPointList.size();
    }

    public ArrayList<Point> getColorPointList() {
        return colorPointList;
    }
}