import processing.core.PApplet;

import javax.swing.*;

public class MonochromeFilter implements PixelFilter {
    private int threshold;

    public MonochromeFilter() {
        do {
            threshold = Integer.parseInt(JOptionPane.showInputDialog("Enter a threshold value [0-255]"));
        } while (threshold < 0 || threshold > 255);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                grid[r][c] = grid[r][c] > threshold ? (short)255 : 0;
            }
        }
        img.setPixels( grid );
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}

