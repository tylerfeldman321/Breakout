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
 * @author Tyler Feldman
 * Game setup code taken from example_animation / Robert Duvall
 */
public class Main extends Application
{
    public static final float ballSpeed = 10;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int SIZE = 400;
    public static final String TITLE = "Breakout";
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int PADDLE_WIDTH = 40;
    public static final int PADDLE_HEIGHT = 5;

    private Scene myScene;
    private Group myRootNode;
    private SpriteManager spriteManager;
    private Player myPlayer;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    public Scene setupGame(int width, int height, Paint background) {
        spriteManager = new SpriteManager();

        myRootNode = new Group();

        myScene = new Scene(myRootNode, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

        Point2D paddleStartingPosition = new Point2D(myScene.getWidth()/2, myScene.getHeight()/2);
        myPlayer = new Player(PADDLE_WIDTH, PADDLE_HEIGHT, paddleStartingPosition, Color.BLACK, 10);
        addSprite(myPlayer);

        return myScene;
    }

    public void addSprite(Sprite sprite) {
        spriteManager.addSprite(sprite);
        myRootNode.getChildren().add(sprite.getShape());
    }

    private void step(double elapsedTime) {
        spriteManager.updateSprites(elapsedTime);
        spriteManager.checkCollisions();
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            myPlayer.moveRight();
        }
        else if (code == KeyCode.LEFT) {
            myPlayer.moveLeft();
        }
    }
}