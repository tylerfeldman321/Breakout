package breakout;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * GameWorldManager handles all within-game logic. This includes initializing the levels,
 * handling when the player loses or wins, and handling the Sprites through the SpriteManager
 * contained in this class.
 * @author Tyler Feldman
 */
public class GameWorldManager {
  public static final int PADDLE_WIDTH = 40;
  public static final int PADDLE_HEIGHT = 5;
  public static final double WALL_WIDTH = 10;
  public static final double SCORE_PER_BLOCK = 100;
  private SpriteManager spriteManager;
  private LevelGenerator levelGenerator;
  private Counter scoreCounter;
  private Counter livesCounter;
  private Scene myScene;
  private Group rootNode;
  private Player myPlayer;
  private Breakout breakout;

  /**
   * Constructor for GameWorldManager.
   * @param rootNode Group root node for the game.
   * @param scene Scene for the game.
   */
  public GameWorldManager(Group rootNode, Scene scene, Breakout breakout) {
    myScene = scene;
    this.rootNode = rootNode;
    this.breakout = breakout;
    spriteManager = new SpriteManager(rootNode, this);
    levelGenerator = new LevelGenerator(spriteManager, myScene);
    setupGame();
  }

  /**
   * Get the SpriteManager for the game.
   * @return SpriteManager for the game.
   */
  public SpriteManager getSpriteManager() {
    return spriteManager;
  }

  /**
   * Get the Player.
   * @return Player object for the current player.
   */
  public Player getPlayer() {
    return myPlayer;
  }

  /**
   * Update active sprites.
   * @param elapsedTime Time elapsed since last frame.
   */
  public void updateSprites(double elapsedTime, GameWorldManager gameWorldManager) {
    spriteManager.updateSprites(elapsedTime, this);
  }

  /**
   * Check for collisions occurring in the game.
   */
  public void checkCollisions() {
    spriteManager.checkCollisions();
  }

  /**
   * Clean up any sprites that have been declared dead.
   */
  public void cleanupSprites() {
    spriteManager.cleanupSprites();
  }

  /**
   * Set up the game level, including the Player and the Block and Wall configuration.
   */
  public void setupGame() {
    initializePlayer();
    buildLevel();
    createCounters();
  }

  private void buildLevel() {
    levelGenerator.generateFullLevel(10, 10, 10, 1.5, 1.5, WALL_WIDTH, 50);
  }

  /**
   * Initialize the Player in the middle of the screen, near the bottom.
   */
  private void initializePlayer() {
    Point2D paddleStartingPosition = new Point2D(myScene.getWidth() / 2,
        myScene.getHeight() - PADDLE_HEIGHT - 30);
    myPlayer = new Player(PADDLE_WIDTH, PADDLE_HEIGHT, paddleStartingPosition, Color.BLACK, 3.5, WALL_WIDTH,
        myScene.getWidth() - WALL_WIDTH);
    spriteManager.addSprite(myPlayer);
  }

  /**
   * Create counters for lives and score.
   */
  private void createCounters() {
    scoreCounter = new Counter(new Point2D(WALL_WIDTH+10, myScene.getHeight()-10),
        "Score: ", 0);
    livesCounter = new Counter(new Point2D(myScene.getWidth() - 60, myScene.getHeight()-10),
        "Lives: ", 3);

    rootNode.getChildren().addAll(scoreCounter.getText(), livesCounter.getText());
  }

  /**
   * Increment the score Counter.
   */
  public void incrementScore() {
    scoreCounter.add(SCORE_PER_BLOCK);
  }

  /**
   * Decrement the lives Counter;
   */
  public void decrementLives() {
    livesCounter.add(-1);
    if (noMoreLives()) {
      stopGame();
    }
  }

  /**
   * Checks if player has lost.
   * @return If the player has lost the game.
   */
  public boolean noMoreLives() {
    return ((int)livesCounter.getValue() <= 0);
  }

  /**
   * Checks if there are active blocks remaining in play.
   * @return true if no blocks are remaining
   */
  public boolean noBlocksRemaining() {
    return spriteManager.noBlocksRemaining();
  }


  /**
   * Stop the game.
   */
  public void stopGame() {
    this.breakout.stopGame();
  }
}
