import processing.core.PApplet;

import javax.swing.*;

public class Convolution3x3Filter implements PixelFilter {
    private double[][] blurKernel =
            {       {1.0/9, 1.0/9, 1.0/9},
                    {1.0/9, 1.0/9, 1.0/9},
                    {1.0/9, 1.0/9, 1.0/9}   };

    private double[][] outlineKernel =
            {
                    {-1, -1, -1},
                    {-1, 8, -1},
                    {-1, -1, -1}   };

    private double[][] embossKernel =
            {
                    {-2, -1, 0},
                    {-1, 1, 1},
                    {0, 1, 2}   };

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels = img.getBWPixelGrid();  // <-- overwrite these values

        for (int r = 1; r < img.getHeight()-1; r++) {
            for (int c = 1; c < img.getWidth()-1; c++) {
                short sum = 0;
                for (int dr = -1; dr <= 1 ; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        sum += pixels[r+dr][c+dc] * embossKernel[dr+1][dc+1];
                    }
                }
                outputPixels[r][c] = sum;
            }
        }

        img.setPixels(outputPixels );

        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}