package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.util.Duration;


/**
 *
 * @author Tyler Feldman
 */
public class Main extends Application {

  public static final float ballSpeed = 10;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final double SIZE = 400;
  public static final String TITLE = "Breakout";
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int PADDLE_WIDTH = 40;
  public static final int PADDLE_HEIGHT = 5;

  private Scene myScene;
  private Group rootNode;
  private SpriteManager spriteManager;
  private Player myPlayer;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Start the game. Create the scene and start the timeline.
   * Code taken from https://coursework.cs.duke.edu/compsci307_2022fall/example_animation, written
   * by Robert Duvall
   * @param stage the primary stage for this application, onto which
   * the application scene can be set.
   * Applications may create other stages, if needed, but they will not be
   * primary stages.
   */
  @Override
  public void start(Stage stage) {
    myScene = setupGame(SIZE, SIZE, BACKGROUND);
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();

    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  /**
   * Set up the game. Create the root node and scene. Initialize the Player and build the level.
   * Ideas and some code taken from https://coursework.cs.duke.edu/compsci307_2022fall/example_animation,
   * written by Robert Duvall
   * @param width
   * @param height
   * @param background
   * @return
   */
  public Scene setupGame(double width, double height, Paint background) {
    rootNode = new Group();

    spriteManager = new SpriteManager(rootNode);

    myScene = new Scene(rootNode, width, height, background);
    myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

    Point2D paddleStartingPosition = new Point2D(myScene.getWidth() / 2,
        myScene.getHeight() - PADDLE_HEIGHT - 30);
    myPlayer = new Player(PADDLE_WIDTH, PADDLE_HEIGHT, paddleStartingPosition, Color.BLACK, 3.5, 10,
        myScene.getWidth() - 10);
    spriteManager.addSprite(myPlayer);

    LevelGenerator levelGenerator = new LevelGenerator(spriteManager, myScene);
    levelGenerator.generateFullLevel(10, 10, 10, 1.5, 1.5, 10, 50);

    return myScene;
  }

  /**
   * Update sprites and check collisions. Runs every frame.
   * @param elapsedTime Time elapsed since last frame.
   */
  private void step(double elapsedTime) {
    spriteManager.updateSprites(elapsedTime);
    spriteManager.checkCollisions();
  }

  /**
   * Handles input from keyboard. Moves Player left/right if left/right arrows are pressed.
   * Creates a Ball if space bar is pressed.
   * @param code
   */
  private void handleKeyInput(KeyCode code) {
    if (code == KeyCode.RIGHT) {
      myPlayer.moveRight();
    } else if (code == KeyCode.LEFT) {
      myPlayer.moveLeft();
    }

    if (code == KeyCode.SPACE) {
      Ball ball = new Ball(5, new Point2D(200, myScene.getHeight() - 50), new Point2D(0, -100),
          Color.BLACK);
      spriteManager.addSprite(ball);
    }
  }
}