import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));
        initializeBoard();
        currentPlayer = 'X';
    }

    private void initializeBoard() {
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 50));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int clickedRow = -1;
                        int clickedCol = -1;


                        for (int r = 0; r < 3; r++) {
                            for (int c = 0; c < 3; c++) {
                                if (buttons[r][c] == e.getSource()) {
                                    clickedRow = r;
                                    clickedCol = c;
                                    break;
                                }
                            }
                        }

                        if (clickedRow != -1 && clickedCol != -1) {
                            buttonClicked(clickedRow, clickedCol);
                        }
                    }
                });
                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("") && !isGameOver()) {
            buttons[row][col].setText(Character.toString(currentPlayer));
            if (isWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetBoard();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean isWinner() {

        for (int i = 0; i < 3; i++) {

            if (buttons[i][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[i][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[i][2].getText().equals(Character.toString(currentPlayer))) {
                return true;
            }


            if (buttons[0][i].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][i].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][i].getText().equals(Character.toString(currentPlayer))) {
                return true;
            }
        }


        if (buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
            buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
            buttons[2][2].getText().equals(Character.toString(currentPlayer))) {
            return true;
        }

        if (buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
            buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
            buttons[2][0].getText().equals(Character.toString(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGameOver() {
        return isWinner() || isBoardFull();
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeGame().setVisible(true);
        });
    }
}
