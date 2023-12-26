public class CheckMine extends SetMine {

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
        //top row
        minesFound += countMine(r - 1, c - 1); //1
        minesFound += countMine(r - 1, c); //2
        minesFound += countMine(r - 1, c + 1); //3
        //mid
        minesFound += countMine(r, c - 1); //4
        minesFound += countMine(r, c + 1); //6
        //bot
        minesFound += countMine(r + 1, c - 1); //7
        minesFound += countMine(r + 1, c); //8
        minesFound += countMine(r + 1, c + 1); //9

        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        } else {
            tile.setText("");

            checkMine(r - 1, c - 1);
            checkMine(r - 1, c);
            checkMine(r - 1, c + 1);

            checkMine(r, c - 1);
            checkMine(r, c + 1);

            checkMine(r + 1, c - 1);
            checkMine(r + 1, c);
            checkMine(r + 1, c + 1);
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
