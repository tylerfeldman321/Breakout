package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Projectile {
    public Ball(double radius, Point2D position, Color color) {
        super(new Circle(radius, color),
                position,
                new Point2D(0, 0));
    }
}
