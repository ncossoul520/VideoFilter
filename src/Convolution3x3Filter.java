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

    private double[][] Gaussian5x5 =
            {
                    {-2, -1, 0},
                    {-1, 1, 1},
                    {0, 1, 2}   };



    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels = img.getBWPixelGrid();  // <-- overwrite these values
        double[][] kernel = blurKernel;

        int border = kernel.length/2;
        for (int r = border; r < img.getHeight() - border; r++) {
            for (int c = border; c < img.getWidth() - border; c++) {
                outputPixels[r][c] = findWeightedAverageAt(r, c, pixels, kernel);
            }
        }

        img.setPixels(outputPixels);
        return img;
    }

    private short findWeightedAverageAt(int r, int c, short[][] pixels, double[][] kernel) {
        int width = kernel.length/2;

        short sum = 0;
        for (int dr = -width; dr <= width ; dr++) {
            for (int dc = -width; dc <= width; dc++) {
                sum += pixels[r+dr][c+dc] * kernel[width+dr][width+dc];
            }
        }
        return sum;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}