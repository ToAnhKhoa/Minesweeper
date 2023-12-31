import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;

public class MineFunctions extends Setting {

  int numCols = numRows;
  int boardWidth = numCols * tileSize;
  int boardHeight = numRows * tileSize;
  MineTile[][] board = new MineTile[numRows][numCols];
  ArrayList<MineTile> mineList;
  Random random = new Random();

  int tilesClicked = 0;
  boolean gameOver = false;

  void setMines() {
    mineList = new ArrayList<MineTile>();
    int mineLeft = mineCount;
    while (mineLeft > 0) {
      int r = random.nextInt(numRows);
      int c = random.nextInt(numCols);

      MineTile tile = board[r][c];
      if (!mineList.contains(tile)) {
        mineList.add(tile);
        mineLeft -= 1;
      }
    }
  }

  JLabel textLabel;

  void revealMines() {
    for (int i = 0; i < mineList.size(); i++) {
      MineTile tile = mineList.get(i);
      tile.setText("B");
    }

    gameOver = true;
    textLabel.setText("Game Over!");
  }

  void checkMine(int r, int c) {
    if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
      return;
    }

    MineTile tile = board[r][c];
    if (!tile.isEnabled()) {
      return;
    }
    tile.setEnabled(false);
    tilesClicked += 1;

    int minesFound = 0;

    //upper check
    minesFound += countMine(r - 1, c - 1); //left
    minesFound += countMine(r - 1, c); //upper
    minesFound += countMine(r - 1, c + 1); //right

    //mid check
    minesFound += countMine(r, c - 1); //left
    minesFound += countMine(r, c + 1); //right

    //bot check
    minesFound += countMine(r + 1, c - 1); //left
    minesFound += countMine(r + 1, c); //bot
    minesFound += countMine(r + 1, c + 1); //right

    if (minesFound > 0) {
      tile.setText(Integer.toString(minesFound));
    } else {
      tile.setText("");

      //upper
      checkMine(r - 1, c - 1); //left
      checkMine(r - 1, c); //upper
      checkMine(r - 1, c + 1); //right

      //mid
      checkMine(r, c - 1); //left
      checkMine(r, c + 1); //right

      //bot
      checkMine(r + 1, c - 1); //left
      checkMine(r + 1, c); //bot
      checkMine(r + 1, c + 1); //right
    }

    JLabel textLabel = new JLabel();

    if (tilesClicked == numRows * numCols - mineList.size()) {
      gameOver = true;
      textLabel.setText("Mines Cleared!");
    }
  }

  int countMine(int r, int c) {
    if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
      return 0;
    }
    if (mineList.contains(board[r][c])) {
      return 1;
    }
    return 0;
  }
}
