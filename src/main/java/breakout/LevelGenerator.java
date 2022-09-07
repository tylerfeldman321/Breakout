package breakout;

import breakout.staticSprites.Block;
import breakout.staticSprites.Wall;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * LevelGenerator creates breakout levels, including the layout of blocks and walls.
 *
 * @author Tyler Feldman
 */
public class LevelGenerator {

  private SpriteManager spriteManager;
  private Scene scene;

  /**
   * Constructor for LevelGenerator.
   *
   * @param spriteManager SpriteManager used for the game.
   * @param scene         Scene for the game.
   */
  public LevelGenerator(SpriteManager spriteManager, Scene scene) {
    this.spriteManager = spriteManager;
    this.scene = scene;
  }

  /**
   * Generate Block objects and Wall objects for a full level of breakout.
   *
   * @param numColumns             Number of Block objects horizontally.
   * @param numRows                Number of Block objects vertically.
   * @param blockHeight            Height of each Block.
   * @param horizontalBlockSpacing Horizontal space between each Block object.
   * @param verticalBlockSpacing   Vertical space between each Block object.
   * @param wallWidth              Width of the Wall objects on the top, left, and right sides.
   * @param emptySpaceHeight       Amount of empty space above the top row of Block objects.
   */
  public void generateFullLevel(int numColumns, int numRows, double blockHeight,
      double horizontalBlockSpacing, double verticalBlockSpacing, double wallWidth,
      double emptySpaceHeight) {

    double levelWidth = Breakout.SIZE - 2 * wallWidth;
    double blockCellWidth = levelWidth / numColumns;
    double blockWidth = blockCellWidth - horizontalBlockSpacing;
    double blockCellHeight = blockHeight + verticalBlockSpacing;
    double positionX, positionY;
    Color blockColor;

    generateOuterWalls(wallWidth);

    for (int row = 0; row < numRows; row++) {
      positionY = row * blockCellHeight + verticalBlockSpacing / 2 + wallWidth + emptySpaceHeight;
      double redValue = (double) row / numRows;
      blockColor = new Color(redValue, 0.5, 0.5, 1.0);
      for (int col = 0; col < numColumns; col++) {
        positionX = col * blockCellWidth + horizontalBlockSpacing / 2 + wallWidth;
        Block block = new Block(blockWidth, blockHeight, new Point2D(positionX, positionY),
            blockColor);
        spriteManager.addSprite(block);
      }
    }
  }

  /**
   * Create walls on the left, right, and top sides of the screen.
   *
   * @param wallWidth Width of the walls.
   */
  public void generateOuterWalls(double wallWidth) {
    Wall wallLeft = new Wall(wallWidth, scene.getHeight(),
        new Point2D(0, 0), Color.BLACK);
    Wall wallRight = new Wall(wallWidth, scene.getHeight(),
        new Point2D(scene.getWidth() - wallWidth, 0), Color.BLACK);
    Wall wallTop = new Wall(scene.getWidth(), wallWidth, new Point2D(0, 0), Color.BLACK);
    spriteManager.addSprites(wallTop, wallLeft, wallRight);
  }
}
