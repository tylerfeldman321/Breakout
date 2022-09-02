package breakout;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Sprite {
    public Paddle(int width, int height, Point2D position, Color color) {
        super(new Rectangle(width, height, color),
                new BoundingBox(position.getX(), position.getY(), width, height),
                position,
                new Point2D(0, 0));
        setRoundedPaddleCorners();
    }

    private void setRoundedPaddleCorners() {
        Rectangle paddleRect = (Rectangle)this.getShape();
        paddleRect.setArcWidth(5);
        paddleRect.setArcHeight(5);
    }
}
