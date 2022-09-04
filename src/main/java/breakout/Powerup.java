package breakout;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Abstract class for any powerup.
 */
public abstract class Powerup extends Sprite {

  public Powerup(Shape shape, Point2D position, Point2D velocity) {
    super(shape, position, velocity);
  }

  /**
   * Handles collision with another object. This results in acquiring the powerup and then the death
   * of the powerup.
   *
   * @param sprite           Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  @Override
  public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
    acquirePowerup(gameWorldManager);
    handleDeath(gameWorldManager);
  }

  /**
   * Handles acquiring the powerup.
   *
   * @param gameWorldManager GameWorldManager for the game.
   */
  public abstract void acquirePowerup(GameWorldManager gameWorldManager);
}