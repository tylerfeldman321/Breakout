package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Sprite {
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
