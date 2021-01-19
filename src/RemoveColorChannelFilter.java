import processing.core.PApplet;

import javax.swing.*;

public class RemoveColorChannelFilter implements PixelFilter {
    private int target_color;

    public RemoveColorChannelFilter() {
        do {
            target_color = Integer.parseInt(JOptionPane.showInputDialog("Select color channel to remove [1:red 2:green 3:blue"));
        } while (target_color < 1 || target_color > 3);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        if (target_color == 1)      { red   = new short[ img.getHeight() ][ img.getWidth() ]; }
        else if (target_color == 2) { green = new short[ img.getHeight() ][ img.getWidth() ]; }
        else                        { blue  = new short[ img.getHeight() ][ img.getWidth() ]; }

        img.setColorChannels(red, green, blue);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }

}

