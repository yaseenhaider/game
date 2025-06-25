import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// Abstract Player
abstract class Player {
    protected char symbol;
    public Player(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }
    public abstract void makeMove(Game game);
}

// Human Player
class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Game game) {
        // Human moves via GUI click, so nothing here
    }
}

// Computer Player
class ComputerPlayer extends Player {
    private Random rand = new Random();

    public ComputerPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Game game) {
        for (int i = 0; i < 9; i++) {
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            if (game.board.isEmpty(row, col)) {
                game.updateButton(row, col, symbol);
                break;
            }
        }
    }
}

// Board logic
class Board {
    private char[][] board = new char[3][3];

    public Board() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    public void setMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public boolean checkWin(char symbol) {
        // Rows, columns, diagonals
        for (int i = 0; i < 3; i++)
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;

        for (int j = 0; j < 3; j++)
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) return true;

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;

        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }
}

// Game GUI and
class Game extends JFrame implements ActionListener {
    JButton[][] buttons = new JButton[3][3];
    Board board = new Board();
    Player player1, player2, currentPlayer;

    public Game() {
        setTitle("Tic-Tac-Toe (OOP GUI)");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // GUI Setup
        Font font = new Font("Arial", Font.BOLD, 60);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        // Players
        player1 = new HumanPlayer('X');
        player2 = new ComputerPlayer('O');
        currentPlayer = player1;

        setVisible(true);
    }

    // Handle Button Click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(currentPlayer instanceof HumanPlayer)) return;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == buttons[i][j] && board.isEmpty(i, j)) {
                    updateButton(i, j, currentPlayer.getSymbol());
                    return;
                }
            }
        }
    }

    // Update board and button
    public void updateButton(int row, int col, char symbol) {
        board.setMove(row, col, symbol);
        buttons[row][col].setText(String.valueOf(symbol));
        buttons[row][col].setEnabled(false);

        // Check win/draw
        if (board.checkWin(symbol)) {
            JOptionPane.showMessageDialog(this, symbol + " wins!");
            resetGame();
        } else if (board.isFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        } else {
            switchTurn();
        }
    }

    // Switch player turn
    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        if (currentPlayer instanceof ComputerPlayer) {
            Timer timer = new Timer(500, e -> currentPlayer.makeMove(this));
            timer.setRepeats(false);
            timer.start();
        }
    }

    // Reset game
    private void resetGame() {
        board = new Board();
        currentPlayer = player1;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
    }

    // Main method
    public static void main(String[] args) {
        new Game();
    }
}
