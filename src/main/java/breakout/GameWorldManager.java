package breakout;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * GameWorldManager handles all within-game logic. This includes initializing the levels, handling
 * when the player loses or wins, and handling the Sprites through the SpriteManager contained in
 * this class.
 *
 * @author Tyler Feldman
 */
public class GameWorldManager {

  public static final int PADDLE_WIDTH = 40;
  public static final int PADDLE_HEIGHT = 5;
  public static final double WALL_WIDTH = 10;
  public static final double PLAYER_SPEED = 5;
  public static final double SCORE_PER_BLOCK = 100;
  public static final double LIVES_REMAINING_BASE_MULTIPLIER = 1;
  public static final double LIVES_REMAINING_MULTIPLIER_FACTOR = 0.1;
  public static final double MAX_BALL_LAUNCH_ANGLE = 100;
  public static final double MIN_BALL_LAUNCH_ANGLE = 80;
  public static final double BALL_RADIUS = 5;
  public static final double BALL_SPEED = 150;
  public static final int NUM_BASIC_BALLS_ALLOWED_IN_PLAY = 1;
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
   *
   * @param rootNode Group root node for the game.
   * @param scene    Scene for the game.
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
   *
   * @return SpriteManager for the game.
   */
  public SpriteManager getSpriteManager() {
    return spriteManager;
  }

  /**
   * Get the Player.
   *
   * @return Player object for the current player.
   */
  public Player getPlayer() {
    return myPlayer;
  }

  /**
   * Update active sprites.
   *
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

  /**
   * Build the game level.
   */
  private void buildLevel() {
    Powerup extraLifePowerup = new ExtraLifePowerup(10,
        new Point2D(myScene.getWidth() / 2 + 50, 35),
        new Point2D(0, 0));
    spriteManager.addSprites(extraLifePowerup);
    levelGenerator.generateFullLevel(10, 10, 10, 1.5, 1.5, WALL_WIDTH, 50);
  }

  /**
   * Initialize the Player in the middle of the screen, near the bottom.
   */
  private void initializePlayer() {
    Point2D paddleStartingPosition = new Point2D(myScene.getWidth() / 2,
        myScene.getHeight() - PADDLE_HEIGHT - 30);
    myPlayer = new Player(PADDLE_WIDTH,
        PADDLE_HEIGHT,
        paddleStartingPosition,
        Color.BLACK,
        PLAYER_SPEED,
        WALL_WIDTH,
        myScene.getWidth() - WALL_WIDTH);
    spriteManager.addSprite(myPlayer);
  }

  /**
   * Create counters for lives and score.
   */
  private void createCounters() {
    scoreCounter = new Counter(new Point2D(WALL_WIDTH + 10, myScene.getHeight() - 10),
        "Score: ", 0);
    livesCounter = new Counter(new Point2D(myScene.getWidth() - 60, myScene.getHeight() - 10),
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
      playerLoses();
    }
  }

  /**
   * Increment the user's lives.
   */
  public void incrementLives() {
    livesCounter.add(1);
  }

  /**
   * Checks if player has lost.
   *
   * @return If the player has lost the game.
   */
  public boolean noMoreLives() {
    return ((int) livesCounter.getValue() <= 0);
  }

  /**
   * Checks if there are active blocks remaining in play.
   *
   * @return true if no blocks are remaining
   */
  public boolean noBlocksRemaining() {
    return spriteManager.noBlocksRemaining();
  }

  /**
   * Checks if player has won.
   */
  public void checkIfPlayerHasWon() {
    if (noBlocksRemaining()) {
      playerWins();
    }
  }

  /**
   * Handle event when player has won.
   */
  public void playerWins() {
    increaseScoreBasedOnHowManyLivesAreLeft();
    stopGame("You Win!!!");
  }

  /**
   * Increase the user's score based on how many lives are remaining.
   */
  private void increaseScoreBasedOnHowManyLivesAreLeft() {
    scoreCounter.multiply(livesCounter.getValue() * LIVES_REMAINING_MULTIPLIER_FACTOR
        + LIVES_REMAINING_BASE_MULTIPLIER);
  }

  /**
   * Handle event when player has lost.
   */
  public void playerLoses() {
    stopGame("You lose :(");
  }

  /**
   * Clear all sprites from view, display end message, and stop game.
   */
  public void stopGame(String message) {
    spriteManager.clearAllSpritesFromView();
    displayGameEndMessage(message);
    this.breakout.stopGame();
  }

  /**
   * Display message to user at the end of the game.
   *
   * @param message String to display to user.
   */
  public void displayGameEndMessage(String message) {
    Text text = new Text(Breakout.SIZE / 2 - 60, Breakout.SIZE / 2, message);
    text.setFont(new Font(20));
    text.setTextAlignment(TextAlignment.LEFT);
    rootNode.getChildren().add(text);
  }

  /**
   * Create a ball moving upwards from the center of the Player. If the number of balls in play is
   * already at a maximum, do not create a new ball.
   */
  public void spawnBallFromPlayerPosition() {
    if (spriteManager.getNumBallsInPlay() >= NUM_BASIC_BALLS_ALLOWED_IN_PLAY) {
      return;
    }

    double launchAngle = getRandomBallLaunchAngle();

    Ball ball = new Ball(BALL_RADIUS,
        new Point2D(myPlayer.getPosition().getX() + myPlayer.getWidth() / 2 - BALL_RADIUS,
            myPlayer.getPosition().getY() - 2 * BALL_RADIUS),
        new Point2D(0, 0),
        Color.BLACK);
    ball.setVelocity(launchAngle, BALL_SPEED);
    getSpriteManager().addSprite(ball);
  }

  /**
   * Generates a random angle uniformly distributed between MIN_BALL_LAUNCH_ANGLE and
   * MAX_BALL_LAUNCH_ANGLE
   *
   * @return double angle to launch the ball.
   */
  public double getRandomBallLaunchAngle() {
    Random randomAngleGenerator = new Random();
    double launchAngle = MIN_BALL_LAUNCH_ANGLE + randomAngleGenerator.nextDouble() *
        (MAX_BALL_LAUNCH_ANGLE - MIN_BALL_LAUNCH_ANGLE);
    return launchAngle;
  }
}
