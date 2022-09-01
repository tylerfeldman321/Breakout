package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Player extends Paddle {
    private float playerSpeed;

    public Player(int width, int height, Point2D position, Color color, float speed) {
        super(width, height, position, color);
        playerSpeed = speed;
    }

    public void moveLeft() {
        moveX(-playerSpeed);
    }

    public void moveRight() {
        moveX(playerSpeed);
    }

    private void moveX(float distanceX) {
        Point2D newPosition = getPosition().add(distanceX, 0);

        // TODO: Check if the new position is too far left or right

        setPosition(newPosition);
    }
}
