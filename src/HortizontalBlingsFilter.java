import processing.core.PApplet;

import javax.swing.*;

public class HortizontalBlingsFilter implements PixelFilter {
    private int height;

    public HortizontalBlingsFilter() {
        do {
            height = Integer.parseInt(JOptionPane.showInputDialog("Enter a height value [0-100]"));
        } while (height < 0 || height > 100);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                if (r / height % 2 == 0) {
                    grid[r][c] = (short) (grid[r][c] / 2);
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

