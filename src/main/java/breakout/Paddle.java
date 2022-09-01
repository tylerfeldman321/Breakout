package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Sprite {
    public Paddle(int width, int height, Point2D position, Color color) {
        super(new Rectangle(width, height, color), position, new Point2D(0, 0));
    }
}