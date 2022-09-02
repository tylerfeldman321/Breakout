package breakout;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Projectile extends Sprite {
    public Projectile(Shape shape, Rectangle boundingBox, Point2D position, Point2D velocity) {
        super(shape, boundingBox, position, velocity);
    }
}
