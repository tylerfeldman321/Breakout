package breakout;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class Projectile extends Sprite {
    public Projectile(Shape shape, BoundingBox boundingBox, Point2D position, Point2D velocity) {
        super(shape, boundingBox, position, velocity);
    }
}
