package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Ball is a Projectile that has a circular shape and bounces off other Sprites during collisions.
 * @author Tyler Feldman
 * @see Projectile
 */
public class Ball extends Projectile {

  private double radius;

  /**
   * Constructor for Ball.
   * @param radius Radius of the Circle.
   * @param position Point2D initial position of the center of the Ball.
   * @param velocity Point2D initial velocity for the ball.
   * @param color Color of the Circle.
   */
  public Ball(double radius, Point2D position, Point2D velocity, Color color) {
    // TODO: handle discrepancy between top left position and center position, since
    //  translateX and translateY are the center position for a Circle
    super(new Circle(radius, color),
        position,
        velocity);
    this.radius = radius;
  }

  /**
   * Handles collision with another Sprite. Bounces off other Sprites. Bounces off Paddles at an
   * angle based on where the Ball collides with the Paddle.
   * @param sprite Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
    // TODO: fix bug with collisions occurring in wrong direction

    Rectangle ballBBoxRect = getBoundingBoxRect();
    Rectangle collidingBBoxRect = sprite.getBoundingBoxRect();

    double intersectionUpper = Math.min(collidingBBoxRect.getTranslateY(),
        ballBBoxRect.getTranslateY());
    double intersectionLower = Math.max(
        collidingBBoxRect.getTranslateY() + collidingBBoxRect.getHeight(),
        ballBBoxRect.getTranslateY() + collidingBBoxRect.getHeight());
    double intersectionLeft = Math.max(collidingBBoxRect.getTranslateX(),
        ballBBoxRect.getTranslateX());
    double intersectionRight = Math.min(
        collidingBBoxRect.getTranslateX() + collidingBBoxRect.getWidth(),
        ballBBoxRect.getTranslateX() + collidingBBoxRect.getWidth());
    double intersectionHeight = intersectionLower - intersectionUpper;
    double intersectionWidth = intersectionRight - intersectionLeft;

    if (intersectionHeight > intersectionWidth) {
      this.bounceX();
    } else {
      this.bounceY();
    }

    if (sprite instanceof Paddle) {
      double centerXBall = ballBBoxRect.getTranslateX() + radius;

      double differenceInXPositions = centerXBall - collidingBBoxRect.getTranslateX();
      double proportionalLocationAlongPaddle =
          differenceInXPositions / collidingBBoxRect.getWidth();
      double proportionalLocationAlongPaddleClamped = Math.max(0,
          Math.min(1, proportionalLocationAlongPaddle));

      double angle =
          Paddle.ANGLE_MIN + ((1 - proportionalLocationAlongPaddleClamped) * Paddle.ANGLE_RANGE);

      this.setVelocity(angle, this.getSpeed());
    }
  }

  /**
   * Get bounding box Rectangle (which happens to be a square) that encloses the Ball.
   */
  public Rectangle getBoundingBoxRect() {
    double radius = ((Circle) this.getShape()).getRadius();
    Rectangle bboxRect = new Rectangle(radius * 2, radius * 2);
    bboxRect.setTranslateX(getPosition().getX() - radius);
    bboxRect.setTranslateY(getPosition().getY() - radius);
    return bboxRect;
  }

  /**
   * Update the Ball. Update position based on velocity. Check if the ball has gone below
   * the screen and if so then decrement lives.
   * @param time Elapsed time since last frame.
   * @param gameWorldManager GameWorldManager for the game.
   */
  @Override
  public void update(double time, GameWorldManager gameWorldManager) {
    if (isBelowScreen()) {
      gameWorldManager.decrementLives();
      gameWorldManager.getSpriteManager().removeSprites(this);
    }
    updatePosition(time);
  }

  /**
   * Checks if the Ball is below the screen.
   * @return If the Ball is below the screen.
   */
  public boolean isBelowScreen() {
    return (getPosition().getY() >= Breakout.SIZE);
  }

  /**
   * Gets radius of the ball.
   * @return radius of the ball as a double.
   */
  public double getRadius() {
    return this.radius;
  }
}
