package breakout;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Projectile extends Sprite {

  public Projectile(Shape shape, Point2D position, Point2D velocity) {
    super(shape, position, velocity);
  }

  public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
    return;
  }
}
