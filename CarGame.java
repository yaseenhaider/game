import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CarGame extends JFrame {

    public CarGame() {
        setTitle("Racing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarGame());
    }

    static class GamePanel extends JPanel implements ActionListener, KeyListener {
        private static final int PANEL_WIDTH = 600;
        private static final int PANEL_HEIGHT = 600;
        private static final int CAR_WIDTH = 40;
        private static final int CAR_HEIGHT = 60;
        private static final int ROAD_SCROLL_SPEED = 5;
        private static final int CAR_MOVE_SPEED = 7;

        private int carX = PANEL_WIDTH/2 - CAR_WIDTH/2;
        private final int carY = PANEL_HEIGHT - 100;
        private boolean movingLeft = false;
        private boolean movingRight = false;
        private final ArrayList<Integer> roadLines = new ArrayList<>();

        public GamePanel() {
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.BLACK);
            setDoubleBuffered(true);

            for (int i = 0; i < PANEL_HEIGHT/100; i++) {
                roadLines.add(i * 100);
            }

            Timer timer = new Timer(16, this);
            timer.start();
            addKeyListener(this);
            setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.WHITE);
            for (Integer y : roadLines) {
                g.fillRect(getWidth()/2 - 5, y, 10, 40);
            }

            g.setColor(Color.RED);
            g.fillRect(carX, carY, CAR_WIDTH, CAR_HEIGHT);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < roadLines.size(); i++) {
                int newY = roadLines.get(i) + ROAD_SCROLL_SPEED;
                if (newY > PANEL_HEIGHT) {
                    newY = -40;
                }
                roadLines.set(i, newY);
            }

            if (movingLeft) carX = Math.max(0, carX - CAR_MOVE_SPEED);
            if (movingRight) carX = Math.min(PANEL_WIDTH - CAR_WIDTH, carX + CAR_MOVE_SPEED);
            repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> movingLeft = true;
                case KeyEvent.VK_RIGHT -> movingRight = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> movingLeft = false;
                case KeyEvent.VK_RIGHT -> movingRight = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}