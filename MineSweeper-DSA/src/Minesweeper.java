import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Minesweeper extends MineFunctions {

  JFrame frame = new JFrame("Minesweeper");
  JLabel textLabel = new JLabel();
  JPanel textPanel = new JPanel();
  JPanel boardPanel = new JPanel();

  Minesweeper() {
    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    textLabel.setFont(new Font("Arial", Font.BOLD, 25));
    textLabel.setHorizontalAlignment(JLabel.CENTER);
    textLabel.setText("Minesweeper: " + Integer.toString(mineCount));
    textLabel.setOpaque(true);

    textPanel.setLayout(new BorderLayout());
    textPanel.add(textLabel);
    frame.add(textPanel, BorderLayout.NORTH);

    boardPanel.setLayout(new GridLayout(numRows, numCols));

    frame.add(boardPanel);

    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        MineTile tile = new MineTile(r, c);
        board[r][c] = tile;

        tile.setFocusable(false);
        tile.setMargin(new Insets(0, 0, 0, 0));
        tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
        tile.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              if (gameOver) {
                return;
              }
              MineTile tile = (MineTile) e.getSource();

              //left click
              if (e.getButton() == MouseEvent.BUTTON1) {
                if (tile.getText() == "") {
                  if (mineList.contains(tile)) {
                    revealMines();
                  } else {
                    checkMine(tile.r, tile.c);
                  }
                }
              }
              //right click
              else if (e.getButton() == MouseEvent.BUTTON3) {
                if (tile.getText() == "" && tile.isEnabled()) {
                  tile.setText("F");
                } else if (tile.getText() == "F") {
                  tile.setText("");
                }
              }
            }
          }
        );

        boardPanel.add(tile);
      }
    }

    frame.setVisible(true);

    setMines();
  }
}
