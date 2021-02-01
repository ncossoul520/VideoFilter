import processing.core.PApplet;

import javax.swing.*;
import java.util.ArrayList;

public class PolychromeFilter implements PixelFilter {
    private int k;
    private ArrayList<Cluster> clusterList;

    public PolychromeFilter() {
        do {
            k = Integer.parseInt(JOptionPane.showInputDialog("Enter a number of clusters [1-256]"));
        } while (k < 1 || k > 256);
    }


    @Override
    public DImage processImage(DImage img) {
        clusterList = createRandomClusters(k);

        short[][] red   = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue  = img.getBlueChannel();

        ArrayList<Point> allPoints = makePointList(red, green, blue);

        Boolean stable = false;
        do {
            clearAllClusters( clusterList );  // remove all points from clusters
            assignPointsToClusters( allPoints, clusterList );
            stable = reCalculateCenters( clusterList );
//            displayInfo( img );
        } while ( !stable );

        reAssignImageColors( red, green, blue, clusterList );
        img.setColorChannels( red, green, blue );
        return img;
    }


    private void displayInfo(DImage img) {
        int sum = 0;
        for (Cluster cluster : clusterList) {
            sum += cluster.size();
            System.out.println( "DEBUG: " + "Center:" + cluster.getCenter() + " #Points: " + cluster.size());
        }
        System.out.println("DEBUG: #points clusters=" + sum + " vs #points img=" + img.getHeight() * img.getWidth());
    }

    private ArrayList<Cluster> createRandomClusters(int k) {
        ArrayList<Cluster> out = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            out.add( new Cluster() );
        }
        return out;
    }

    private ArrayList<Point> makePointList(short[][] red, short[][] green, short[][] blue) {
        ArrayList<Point> out = new ArrayList<>();
        for (short r = 0; r < red.length; r++) {
            for (short c = 0; c < red[0].length; c++) {
                out.add( new Point(r, c, red[r][c], green[r][c], blue[r][c]) );
            }
        }
        return out;
    }

    private void clearAllClusters(ArrayList<Cluster> clusterList) {
        for (Cluster cluster : clusterList) {
            cluster.clearPoints();
        }
    }

    private void assignPointsToClusters(ArrayList<Point> allPoints, ArrayList<Cluster> clusterList) {
        for (Point point : allPoints) {
            Cluster closest = findClosest(point, clusterList);
            closest.add( point );
        }
    }

    private Cluster findClosest(Point point, ArrayList<Cluster> clusterList) {
        Cluster out = new Cluster();
        double dist, dist_min = Integer.MAX_VALUE;
        for (Cluster cluster : clusterList) {
            dist = point.distanceFrom( cluster.getCenter() );
            if (dist < dist_min) {
                out = cluster;
                dist_min = dist;
            }
        }
        return out;
    }

    private Boolean reCalculateCenters(ArrayList<Cluster> clusterList) {
        Boolean stable = true;
        for (Cluster cluster : clusterList) {
            stable &= cluster.calculateCenter();
        }
        return stable;
    }

    private void reAssignImageColors(short[][] red, short[][] green, short[][] blue, ArrayList<Cluster> clusterList) {
        for (Cluster cluster : clusterList) {
            for (Point point : cluster.getColorPointList()) {
                short r = point.getRow();
                short c = point.getCol();
                red[r][c]   = cluster.getCenter().getRed();
                green[r][c] = cluster.getCenter().getGreen();
                blue[r][c]  = cluster.getCenter().getBlue();
            }
        }
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}

