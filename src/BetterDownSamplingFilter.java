import processing.core.PApplet;

import javax.swing.*;

public class BetterDownSamplingFilter implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        short[][] grid_small = new short[ (img.getHeight()+1)/2 ][ (img.getWidth()+1)/2 ];

        for (int r = 0; r < img.getHeight()-1; r+=2) {
            for (int c = 0; c < img.getWidth()-1; c+=2) {
                short sum = 0;
                for (int drow = 0; drow < 2; drow++) {
                    for (int dcol = 0; dcol < 2; dcol++) {
                        sum += grid[r+drow][c+dcol];
                    }
                }
                grid_small[r/2][c/2] = (short)(sum/4); // average
            }
        }

        DImage img_small = new DImage((img.getWidth()+1)/2, (img.getHeight()+1)/2);
        img_small.setPixels( grid_small );
        return img_small;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}

