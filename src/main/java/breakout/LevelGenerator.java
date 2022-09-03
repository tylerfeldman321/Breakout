package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class LevelGenerator {
    private SpriteManager spriteManager;

    public LevelGenerator(SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    public void generateFullLevel(int numColumns, int numRows, double blockHeight, double horizontalBlockSpacing, double verticalBlockSpacing) {

        double blockCellWidth = (((double) Main.SIZE) / (double) numColumns);
        double blockWidth = blockCellWidth - horizontalBlockSpacing;
        double blockCellHeight = blockHeight + verticalBlockSpacing;
        double positionX, positionY;
        Color blockColor;

        for (int row = 0; row < numRows; row++) {
            positionY = row * blockCellHeight + verticalBlockSpacing/2;
            double redValue = (double) row / numRows;
            blockColor = new Color(redValue, 0.5, 0.5, 1.0);
            for (int col = 0; col < numColumns; col++) {
                positionX = col * blockCellWidth + horizontalBlockSpacing/2;
                Block block = new Block(blockWidth, blockHeight, new Point2D(positionX, positionY), blockColor);
                spriteManager.addSprite(block);
            }
        }
    }

    public void loadFromFile() {

    }


}
