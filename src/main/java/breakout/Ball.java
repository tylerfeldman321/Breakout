package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Projectile {
    public Ball(double radius, Point2D position, Point2D velocity, Color color) {
        super(new Circle(radius, color),
                position,
                velocity);
    }
}
