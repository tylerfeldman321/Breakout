package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Paddle class is a Rectangle shaped Sprite. When a Ball object collides with a Paddle, it bounces
 * off the Paddle at an angle based on where the Ball collides with the Paddle.
 *
 * @author Tyler Feldman
 */
public class Paddle extends Sprite {

  public static final double ANGLE_MIN = 30;
  public static final double ANGLE_MAX = 150;
  public static final double ANGLE_RANGE = ANGLE_MAX - ANGLE_MIN;

  private double width;
  private double height;

  /**
   * Constructor for Paddle.
   *
   * @param width    Width of the Rectangle.
   * @param height   Height of the Rectangle.
   * @param position Top left position of the Paddle.
   * @param color    Color for the fill of the Rectangle.
   */
  public Paddle(double width, double height, Point2D position, Color color,
      boolean isAMovingSprite) {
    super(new Rectangle(width, height, color),
        position,
        new Point2D(0, 0), isAMovingSprite);
    setRoundedPaddleCorners(height);
    this.width = width;
    this.height = height;
  }

  /**
   * Set position of the shape.
   *
   * @param position Point2D top left position of the Rectangle.
   */
  @Override
  public void setShapePosition(Point2D position) {
    Rectangle rectangle = (Rectangle) this.getShape();
    rectangle.setX(position.getX());
    rectangle.setY(position.getY());
  }

  /**
   * Set the corners of the Paddle to be rounded.
   */
  private void setRoundedPaddleCorners(double arcSize) {
    Rectangle paddleRect = (Rectangle) this.getShape();
    paddleRect.setArcWidth(arcSize);
    paddleRect.setArcHeight(arcSize);
  }

  /**
   * Handles collision with another Sprite.
   *
   * @param sprite           Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
    return;
  }

  /**
   * Get the Rectangle collision bounding box of the Paddle.
   *
   * @return The bounding box Rectangle for the Paddle.
   */
  public Rectangle getBoundingBoxRect() {
    return (Rectangle) this.getShape();
  }

  /**
   * Get the width of the Paddle.
   *
   * @return double for the width of the Paddle.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Get the height of the Paddle.
   *
   * @return double for the height of the Paddle.
   */
  public double getHeight() {
    return this.height;
  }
}
