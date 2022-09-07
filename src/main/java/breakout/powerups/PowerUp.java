package breakout.powerups;

import breakout.GameWorldManager;
import breakout.Player;
import breakout.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class for any PowerUp.
 *
 * @author Tyler Feldman
 */
public abstract class PowerUp extends Sprite {

  public PowerUp(double width, double height, Color color, Point2D position, Point2D velocity) {
    super(new Rectangle(width, height, color), position, velocity, true);
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
    if (sprite instanceof Player) {
      acquirePowerUp(gameWorldManager);
      handleDeath(gameWorldManager);
    }
  }

  /**
   * Handles acquiring the powerup.
   *
   * @param gameWorldManager GameWorldManager for the game.
   */
  public abstract void acquirePowerUp(GameWorldManager gameWorldManager);

  /**
   * Abstract function to create copy of the PowerUp object.
   *
   * @return PowerUp copy of this PowerUp object.
   */
  public abstract PowerUp copy();
}
