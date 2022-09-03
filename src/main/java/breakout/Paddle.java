package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Sprite {
    public static final double ANGLE_MIN = 30;
    public static final double ANGLE_MAX = 150;
    public static final double ANGLE_RANGE = ANGLE_MAX - ANGLE_MIN;

    public Paddle(int width, int height, Point2D position, Color color) {
        super(new Rectangle(width, height, color),
                position,
                new Point2D(0, 0));
        setRoundedPaddleCorners();
    }

    private void setRoundedPaddleCorners() {
        Rectangle paddleRect = (Rectangle)this.getShape();
        paddleRect.setArcWidth(5);
        paddleRect.setArcHeight(5);
    }

    public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
        return;
    }

    public Rectangle getBoundingBoxRect() {
        return (Rectangle) this.getShape();
    }
}
