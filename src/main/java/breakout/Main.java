package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Tyler Feldman
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Breakout";
    public static final int SIZE = 400;
    public static final String RESOURCE_PATH = "/breakout/";
    public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";
    public static final float ballSpeed = 10;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    private Scene myScene;


    /**
     * Initialize what will be displayed. Code from example_animation / Robert Duvall
     */
    @Override
    public void start (Stage stage) {
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
        Group root = new Group();
        myScene = new Scene(root, width, height, background);
        return myScene;
    }

    private void step(double elapsedTime) {

    }


    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
