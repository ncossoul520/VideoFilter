import processing.core.PApplet;

import javax.swing.*;

public class ColorMaskingFilter implements PixelFilter {
    private int threshold, red_target, green_target, blue_target;

    public ColorMaskingFilter() {
        do {
            red_target = Integer.parseInt(JOptionPane.showInputDialog("Enter a red target [0-255]"));
        } while (threshold < 0 || threshold > 255);
        do {
            green_target = Integer.parseInt(JOptionPane.showInputDialog("Enter a green target [0-255]"));
        } while (threshold < 0 || threshold > 255);
        do {
            blue_target = Integer.parseInt(JOptionPane.showInputDialog("Enter a blue target [0-255]"));
        } while (threshold < 0 || threshold > 255);
        do {
            threshold = Integer.parseInt(JOptionPane.showInputDialog("Enter a distance threshold [5-100]"));
        } while (threshold < 5 || threshold > 100);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                if (colorDistance(red[r][c], green[r][c], blue[r][c], red_target, green_target, blue_target) < threshold) {
                    red[r][c] = 255;
                    green[r][c] = 255;
                    blue[r][c] = 255;
                } else {
                    red[r][c] = 0;
                    green[r][c] = 0;
                    blue[r][c] = 0;
                }
            }
        }

        img.setColorChannels(red, green, blue);
        return img;
    }

    private double colorDistance(short r, short g, short b, int rt, int gt, int bt) {
        return Math.sqrt((r - rt) * (r - rt) + (g - gt) * (g - gt) + (b - bt) * (b - bt));
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}
