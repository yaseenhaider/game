import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // Added missing import

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
        SwingUtilities.invokeLater(() -> new CarGame()); // Fixed EDT issue
    }

    static class GamePanel extends JPanel implements ActionListener, KeyListener {
        // ... (rest of the code remains the same)
    }
}