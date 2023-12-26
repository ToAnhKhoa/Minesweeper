public class Setting {

    int tileSize = 70;
    int numRows = 8;
    int mineCount = 10;
    int numCols = numRows;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;
    MineTile[][] board = new MineTile[numRows][numCols];
    int tilesClicked = 0;
}
