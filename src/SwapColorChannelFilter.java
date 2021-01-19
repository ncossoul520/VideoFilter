import processing.core.PApplet;

public class SwapColorChannelFilter implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        short[][] tmp = red;
        red = green;
        green = blue;
        blue = tmp;

        img.setColorChannels(red, green, blue);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }

}

