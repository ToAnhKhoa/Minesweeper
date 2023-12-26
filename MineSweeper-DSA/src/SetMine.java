import java.util.ArrayList;
import java.util.Random;

public class SetMine extends Setting {

    ArrayList<MineTile> mineList;
    Random random = new Random();

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
}
