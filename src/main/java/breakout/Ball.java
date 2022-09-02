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

    public void bounceX() {
        Point2D newVelocity = new Point2D(-this.getVelocity().getX(), this.getVelocity().getY());
        this.setVelocity(newVelocity);
    }

    public void bounceY() {
        Point2D newVelocity = new Point2D(this.getVelocity().getX(), -this.getVelocity().getY());
        this.setVelocity(newVelocity);
    }
}
