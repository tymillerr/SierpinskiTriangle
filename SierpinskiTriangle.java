//tymiller

import javax.swing.*;
import java.awt.*;

public class SierpinskiTriangle extends JFrame {

    public SierpinskiTriangle() {
        setTitle("Sierpinski Triangle");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new SierpinskiCanvas());
        setVisible(true);
    }

    public static void main(String[] args) {
        new SierpinskiTriangle();
    }

    class SierpinskiCanvas extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int[] xPoints = {400, 100, 700};
            int[] yPoints = {100, 700, 700};
            drawTriangle(g, xPoints, yPoints, 4);
        }

        private void drawTriangle(Graphics g, int[] xPoints, int[] yPoints, int pixelLimit) {
            // Base case: stop if side length is less than or equal to pixelLimit
            if (Math.abs(xPoints[1] - xPoints[0]) <= pixelLimit) {
                return;
            }

            // Draw the main triangle
            g.setColor(Color.BLACK);
            g.fillPolygon(xPoints, yPoints, 3);

            // Calculate midpoints
            int midX1 = (xPoints[0] + xPoints[1]) / 2;
            int midX2 = (xPoints[1] + xPoints[2]) / 2;
            int midX3 = (xPoints[2] + xPoints[0]) / 2;
            int midY1 = (yPoints[0] + yPoints[1]) / 2;
            int midY2 = (yPoints[1] + yPoints[2]) / 2;
            int midY3 = (yPoints[2] + yPoints[0]) / 2;

            // Draw the inverted triangle
            g.setColor(Color.WHITE);
            g.fillPolygon(new int[]{midX1, midX2, midX3}, new int[]{midY1, midY2, midY3}, 3);

            // Recursively draw three smaller triangles
            drawTriangle(g, new int[]{xPoints[0], midX1, midX3}, new int[]{yPoints[0], midY1, midY3}, pixelLimit);
            drawTriangle(g, new int[]{midX1, xPoints[1], midX2}, new int[]{midY1, yPoints[1], midY2}, pixelLimit);
            drawTriangle(g, new int[]{midX3, midX2, xPoints[2]}, new int[]{midY3, midY2, yPoints[2]}, pixelLimit);
        }
    }
}
