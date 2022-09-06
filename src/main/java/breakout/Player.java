package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Player represents the Paddle that the user controls. Its internal Shape is a Rectangle with
 * rounded edges. There are functions provided to move left and right based on the speed of the
 * Player.
 *
 * @author Tyler Feldman
 * @see Paddle
 */
public class Player extends Paddle {

  private double playerSpeed;
  private double xMin;
  private double xMax;

  /**
   * Constructor for Player.
   *
   * @param width    Width of the Paddle.
   * @param height   Height of the Paddle.
   * @param position Point2D representing the initial top left position of the Player.
   * @param color    Color of the Rectangle that is displayed.
   * @param speed    double for how far the Player moves every time left or right is pressed.
   * @param xMin     Leftmost x value that the Player cannot go past.
   * @param xMax     Rightmost x value that the Player cannot go past.
   */
  public Player(double width, double height, Point2D position, Color color, double speed,
      double xMin,
      double xMax) {
    super(width, height, position, color, false);
    playerSpeed = speed;
    this.xMin = xMin;
    this.xMax = xMax;
  }

  /**
   * Move the Player's Paddle to the left by playerSpeed.
   */
  public void moveLeft() {
    moveX(-playerSpeed);
  }

  /**
   * Move the Player's Paddle to the right by playerSpeed.
   */
  public void moveRight() {
    moveX(playerSpeed);
  }

  /**
   * Move the Player's Paddle horizontally.
   *
   * @param distanceX to move in x direction.
   */
  private void moveX(double distanceX) {
    Point2D newPosition = getPosition().add(distanceX, 0);

    if (isTooFarLeft(newPosition)) {
      this.setPositionX(xMin);
    } else if (isTooFarRight(newPosition)) {
      this.setPositionX(xMax - this.getWidth());
    } else {
      setPosition(newPosition);
    }
  }

  /**
   * Returns true if the position is too far to the left.
   *
   * @param position Point2D top left position of the Player.
   * @return Whether the left side of the player is to the left than the minimum x value provided.
   */
  private boolean isTooFarLeft(Point2D position) {
    return (position.getX() < xMin);
  }

  /**
   * Returns true if the position is too far to the right.
   *
   * @param position Point2D top left position of the Player.
   * @return Whether the right side of the player is past the maximum x value provided.
   */
  private boolean isTooFarRight(Point2D position) {
    return (position.getX() + this.getWidth() > xMax);
  }
}
