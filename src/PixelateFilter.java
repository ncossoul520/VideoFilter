import processing.core.PApplet;

import javax.swing.*;

public class PixelateFilter implements PixelFilter {
    private int radius;

    public PixelateFilter() {
        do {
            radius = Integer.parseInt(JOptionPane.showInputDialog("Enter a radius value [odd 3-15]"));
        } while (radius < 3 || radius > 15 || radius%2 == 0);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        int d = radius/2;
        for (int r = d; r < img.getHeight()-d; r+=d) {
            for (int c = d; c < img.getWidth()-d; c+=d) {
                for (int drow = -d; drow < d; drow++) {
                    for (int dcol = -d; dcol < d; dcol++) {
                        grid[r+drow][c+dcol] = grid[r][c];
                    }
                }
            }
        }
        img.setPixels( grid );
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}

