public class Point {
    private short red, green, blue;
    private short r, c;

    // Assign random values
    public Point() {
        this.red   = (short)(Math.random()*256);
        this.green = (short)(Math.random()*256);
        this.blue  = (short)(Math.random()*256);
    }

    public Point(short red, short green, short blue) {
        this.red   = red;
        this.green = green;
        this.blue  = blue;
    }

    public Point(short r, short c, short red, short green, short blue) {
        this.r = r;
        this.c = c;
        this.red   = red;
        this.green = green;
        this.blue  = blue;
    }


    public double distanceFrom(short rt, short gt, short bt) {
        return Math.sqrt( (red - rt)*(red - rt) + (green - gt)*(green - gt) + (blue - bt)*(blue - bt) );
    }

    public double distanceFrom(Point p2) {
        return Math.sqrt( (red   - p2.getRed())  *(red   - p2.getRed()) +
                          (green - p2.getGreen())*(green - p2.getGreen()) +
                          (blue  - p2.getBlue()) *(blue  - p2.getBlue()) );
    }


    public short getRed() {
        return red;
    }

    public void setRed(short red) {
        this.red = red;
    }

    public short getGreen() {
        return green;
    }

    public void setGreen(short green) {
        this.green = green;
    }

    public short getBlue() {
        return blue;
    }

    public void setBlue(short blue) {
        this.blue = blue;
    }

    public short getRow() {
        return r;
    }

    public short getCol() {
        return c;
    }

    @Override
    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")";
    }
}
