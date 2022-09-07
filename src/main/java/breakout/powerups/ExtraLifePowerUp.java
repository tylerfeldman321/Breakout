package breakout.powerups;

import breakout.GameWorldManager;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * PowerUp that gives the player an extra life.
 *
 * @author Tyler Feldman
 * @see PowerUp
 */
public class ExtraLifePowerUp extends PowerUp {
  private double length;

  /**
   * Constructor for ExtraLifePowerUp. The shape is a square with side lengths equal to Length.
   *
   * @param length   Length of the square.
   * @param position Point2D top left position of the powerup.
   * @param velocity Point2D initial velocity of the powerup.
   */
  public ExtraLifePowerUp(double length, Point2D position, Point2D velocity) {
    super(length, length, Color.GREEN, position, velocity);
    this.length = length;
  }

  /**
   * Handles acquiring the powerup. Increments the lives for the player.
   *
   * @param gameWorldManager GameWorldManager for the game.
   */
  @Override
  public void acquirePowerUp(GameWorldManager gameWorldManager) {
    gameWorldManager.incrementLives();
  }

  /**
   * Create copy of this object.
   *
   * @return Copy of this object.
   */
  @Override
  public PowerUp copy() {
    return new ExtraLifePowerUp(this.length, this.getPosition(), this.getVelocity());
  }
}
