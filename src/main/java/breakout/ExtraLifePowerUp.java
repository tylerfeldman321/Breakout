package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    super(new Rectangle(length, length, Color.GREEN), position, velocity);
    this.length = length;
  }

  /**
   * Sets the shape position.
   *
   * @param position Point2D top left position.
   */
  @Override
  public void setShapePosition(Point2D position) {
    Rectangle rectangle = (Rectangle) this.getShape();
    rectangle.setX(position.getX());
    rectangle.setY(position.getY());
  }

  /**
   * Handles acquiring the powerup. Increments the lives for the player.
   *
   * @param gameWorldManager GameWorldManager for the game.
   */
  @Override
  public void acquirePowerup(GameWorldManager gameWorldManager) {
    gameWorldManager.incrementLives();
  }

  /**
   * Gets the bounding box Rectangle for collisions.
   *
   * @return
   */
  @Override
  public Rectangle getBoundingBoxRect() {
    return (Rectangle) this.getShape();
  }

  @Override
  public PowerUp copy() {
    return new ExtraLifePowerUp(this.length, this.getPosition(), this.getVelocity());
  }
}
