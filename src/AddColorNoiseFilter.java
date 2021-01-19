import processing.core.PApplet;

import javax.swing.*;

public class AddColorNoiseFilter implements PixelFilter {
    private int noise_max;

    public AddColorNoiseFilter() {
        do {
            noise_max = Integer.parseInt(JOptionPane.showInputDialog("Select a max radius [5-100]"));
        } while (noise_max < 5 || noise_max > 100);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                red[r][c]   += Math.random()*noise_max;
                green[r][c] += Math.random()*noise_max;
                blue[r][c]  += Math.random()*noise_max;

                if (red[r][c] < 0) { red[r][c] = 0; }
                else if (red[r][c] > 255) { red[r][c] = 255; };

                if (green[r][c] < 0) { green[r][c] = 0; }
                else if (green[r][c] > 255) { green[r][c] = 255; };

                if (blue[r][c] < 0) { blue[r][c] = 0; }
                else if (blue[r][c] > 255) { blue[r][c] = 255; };
            }
        }

        img.setColorChannels(red, green, blue);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }

}

