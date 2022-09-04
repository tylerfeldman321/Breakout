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
 * Main class for breakout game. Sets the game up and launches it.
 *
 * @author Tyler Feldman
 */
public class Breakout extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final double SIZE = 400;
  public static final String TITLE = "Breakout";
  public static final Paint BACKGROUND = Color.AZURE;

  private Scene myScene;
  private Group rootNode;
  private GameWorldManager gameWorldManager;
  private Timeline timeline;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Start the game. Create the scene and start the timeline. Code taken from
   * https://coursework.cs.duke.edu/compsci307_2022fall/example_animation, written by Robert Duvall
   *
   * @param stage the primary stage for this application, onto which the application scene can be
   *              set. Applications may create other stages, if needed, but they will not be primary
   *              stages.
   */
  @Override
  public void start(Stage stage) {
    myScene = setupScene(SIZE, SIZE, BACKGROUND);
    gameWorldManager = new GameWorldManager(rootNode, myScene, this);

    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();

    timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    timeline.play();
  }

  /**
   * Set up the game. Create the root node and scene. Initialize the Player and build the level.
   * Ideas and some code taken from
   * https://coursework.cs.duke.edu/compsci307_2022fall/example_animation, written by Robert Duvall
   *
   * @param width
   * @param height
   * @param background
   * @return
   */
  public Scene setupScene(double width, double height, Paint background) {
    rootNode = new Group();

    myScene = new Scene(rootNode, width, height, background);
    myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

    return myScene;
  }

  /**
   * Update sprites and check collisions. Runs every frame.
   *
   * @param elapsedTime Time elapsed since last frame.
   */
  private void step(double elapsedTime) {
    gameWorldManager.updateSprites(elapsedTime, gameWorldManager);
    gameWorldManager.checkCollisions();
    gameWorldManager.cleanupSprites();
  }

  /**
   * Handles input from keyboard. Moves Player left/right if left/right arrows are pressed. Creates
   * a Ball if space bar is pressed.
   *
   * @param code
   */
  private void handleKeyInput(KeyCode code) {
    if (code == KeyCode.RIGHT) {
      gameWorldManager.getPlayer().moveRight();
    } else if (code == KeyCode.LEFT) {
      gameWorldManager.getPlayer().moveLeft();
    }

    if (code == KeyCode.SPACE) {
      gameWorldManager.spawnBallFromPlayerPosition();
    }
  }

  /**
   * Stop the timeline.
   */
  public void stopGame() {
    timeline.stop();
    stopUserInput();
  }

  /**
   * Stop user input.
   */
  private void stopUserInput() {
    myScene.setOnKeyPressed(null);
  }

}