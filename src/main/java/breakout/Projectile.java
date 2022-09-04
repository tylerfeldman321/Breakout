package breakout;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Abstract class for any projectiles that are used in the game.
 * @author Tyler Feldman
 */
public abstract class Projectile extends Sprite {

  /**
   * Constructor for Projectile.
   * @param shape Shape of the projectile.
   * @param position Point2D initial position.
   * @param velocity Point2D intial velocity.
   */
  public Projectile(Shape shape, Point2D position, Point2D velocity) {
    super(shape, position, velocity);
  }

  /**
   * Handle collision with another Sprite. Does nothing.
   * @param sprite Other sprite that is colliding with this Sprite.
   * @param spriteManager SpriteManager object for the game.
   */
  public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
    return;
  }
}
