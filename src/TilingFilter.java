import processing.core.PApplet;

import javax.swing.*;

public class TilingFilter implements PixelFilter {
    private int repeat_rows, repeat_cols;

    public TilingFilter() {
        do {
            repeat_rows = Integer.parseInt(JOptionPane.showInputDialog("Enter # of row repeats [1-10]"));
        } while (repeat_rows < 1 || repeat_rows > 10);
        do {
            repeat_cols = Integer.parseInt(JOptionPane.showInputDialog("Enter # of col repeats [1-10]"));
        } while (repeat_cols < 1 || repeat_cols > 10);
    }

    @Override
    public DImage processImage(DImage img) {
        int[][] grid = img.getColorPixelGrid();
        int[][] out_grid = new int[img.getHeight()*repeat_rows][img.getWidth()*repeat_cols];
        for (int row = 0; row < repeat_rows; row++) {
            for (int col = 0; col < repeat_cols; col++) {

                for (int r = 0; r < img.getHeight(); r++) {
                    for (int c = 0; c < img.getWidth(); c++) {
                        out_grid[row*img.getHeight()+r][col*img.getWidth()+c] = grid[r][c];
                    }
                }
            }
        }
        DImage out = new DImage( img.getWidth()*repeat_cols, img.getHeight()*repeat_rows );
        out.setPixels( out_grid );

        return out;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }
}
