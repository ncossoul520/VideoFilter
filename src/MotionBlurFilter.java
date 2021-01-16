import processing.core.PApplet;

import javax.swing.*;

public class MotionBlurFilter implements PixelFilter {
    private int radius;

    public MotionBlurFilter() {
        do {
            radius = Integer.parseInt(JOptionPane.showInputDialog("Enter a height value [2-50]"));
        } while (radius < 2 || radius > 50);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                int sum = 0, count = 0;
                for (int dc = 0; dc < radius; dc++) {
                    if (c+dc < img.getWidth()) {
                        sum += grid[r][c + dc];
                        count++;
                    }
                }
                grid[r][c] = (short)(sum/count);
            }
        }
        img.setPixels( grid );
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}

