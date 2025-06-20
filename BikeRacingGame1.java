import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


 class Bike1 {
    private int x, y;
    private Color color;
    private int speed;

    public Bike1(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = 5 + (int)(Math.random() * 5); // random speed
    }

    public void moveForward() {
        x += speed;
    }

    public void moveUp() {
        y -= 5;
    }

    public void moveDown() {
        y += 5;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow
        g2d.setColor(new Color(50, 50, 50, 100)); // translucent gray
        g2d.fillOval(x - 5, y + 30, 90, 10);

        // Bike body
        g2d.setColor(color);
        g2d.fillRect(x, y, 80, 20);

        // Bike Seat
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x + 20, y - 10, 30, 10);

        // Wheels (3D effect)
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(x - 10, y + 15, 25, 25); // back wheel
        g2d.fillOval(x + 60, y + 15, 25, 25); // front wheel
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x - 5, y + 20, 15, 15);  // wheel inner
        g2d.fillOval(x + 65, y + 20, 15, 15);

        // Handle
        g2d.setColor(Color.GRAY);
        g2d.drawLine(x + 75, y, x + 90, y - 10);
        g2d.drawLine(x + 90, y - 10, x + 85, y - 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

 class GamePanel extends JPanel implements ActionListener {
    private ArrayList<Bike1> bikes;
    private Timer timer;
    private Image background;
    private int bgX = 0;
    private int finishLine = 1500;

    public GamePanel() {
        bikes = new ArrayList<>();
        bikes.add(new Bike1(50, 150, Color.RED));    // Player bike
        bikes.add(new Bike1(50, 250, Color.BLUE));   // Opponent bike
        bikes.add(new Bike1(50, 350, Color.GREEN));  // Opponent bike

        background = new ImageIcon("road_background.png").getImage(); // You need to add this image!

        timer = new Timer(40, this); // smooth animation
        timer.start();

        setFocusable(true);
        setBackground(Color.GRAY);

        // Controls
        getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
        getActionMap().put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                bikes.get(0).moveUp();
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        getActionMap().put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                bikes.get(0).moveDown();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Bike1 bike : bikes) {
            bike.moveForward();
        }
        bgX -= 2; // move background to create moving effect
        repaint();

        if (bikes.get(0).getX() >= finishLine) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "🏆 You Won the Race!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Moving Background (map)
        for (int i = 0; i < getWidth(); i += background.getWidth(null)) {
            g.drawImage(background, (bgX + i) % background.getWidth(null), 0, null);
        }

        // Draw Finish Line
        g.setColor(Color.BLACK);
        g.fillRect(finishLine - bikes.get(0).getX() + 100, 0, 10, getHeight());

        // Draw Bikes
        for (Bike1 bike : bikes) {
            bike.draw(g);
        }
    }
}


public class BikeRacingGame1 extends JFrame {
    public BikeRacingGame1() {
        setTitle("🏍️ 3D Bike Racing Game");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GamePanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BikeRacingGame1().setVisible(true);
        });
    }
}
