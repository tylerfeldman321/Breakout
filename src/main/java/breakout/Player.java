package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Player extends Paddle {

  private double playerSpeed;
  private double xMin;
  private double xMax;

  public Player(int width, int height, Point2D position, Color color, double speed, double xMin,
      double xMax) {
    super(width, height, position, color);
    playerSpeed = speed;
    this.xMin = xMin;
    this.xMax = xMax;
  }

  public void moveLeft() {
    moveX(-playerSpeed);
  }

  public void moveRight() {
    moveX(playerSpeed);
  }

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

  private boolean isTooFarLeft(Point2D position) {
    return (position.getX() < xMin);
  }

  private boolean isTooFarRight(Point2D position) {
    return (position.getX() + this.getWidth() > xMax);
  }
}
