import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;

public class Car extends Applet {
    public void paint(Graphics g) {
        // Draw car body
        g.setColor(Color.RED);
        g.fillRect(50, 100, 200, 50);
        g.fillRect(75, 75, 150, 50);

        // Draw windows
        g.setColor(Color.CYAN);
        g.fillRect(110, 80, 30, 30);
        g.fillRect(160, 80, 30, 30);

        // Draw wheels
        g.setColor(Color.BLACK);
        g.fillOval(75, 140, 50, 50);
        g.fillOval(175, 140, 50, 50);

    }
}