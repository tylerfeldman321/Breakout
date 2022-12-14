package breakout.projectiles;

import breakout.Breakout;
import breakout.GameWorldManager;
import breakout.Paddle;
import breakout.powerups.PowerUp;
import breakout.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Ball is a Projectile that has a circular shape and bounces off other Sprites during collisions.
 *
 * @author Tyler Feldman
 * @see Projectile
 */
public class Ball extends Projectile {

  private double radius;

  /**
   * Constructor for Ball.
   *
   * @param radius   Radius of the Circle.
   * @param position Point2D initial top left position of the center of the Ball.
   * @param velocity Point2D initial velocity for the ball.
   * @param color    Color of the Circle.
   */
  public Ball(double radius, Point2D position, Point2D velocity, Color color) {
    super(new Circle(radius, color),
        position,
        velocity);
    this.radius = radius;
  }

  /**
   * Set the shape position for the Ball.
   *
   * @param position Position of the top left of the ball.
   */
  @Override
  public void setShapePosition(Point2D position) {
    Circle shape = (Circle) this.getShape();
    shape.setCenterX(position.getX() + this.getRadius());
    shape.setCenterY(position.getY() + this.getRadius());
  }

  /**
   * Handles collision with another Sprite. Bounces off other Sprites. Bounces off Paddles at an
   * angle based on where the Ball collides with the Paddle.
   *
   * @param sprite           Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
    if (sprite instanceof PowerUp) {
      return;
    }

    Rectangle ballBBoxRect = getBoundingBoxRect();
    Rectangle collidingBBoxRect = sprite.getBoundingBoxRect();

    if (sprite instanceof Paddle) {
      collideWithPaddle(ballBBoxRect, collidingBBoxRect);
    } else {
      bounceOffOtherSprite(ballBBoxRect, collidingBBoxRect);
    }
  }

  /**
   * Bounces this Ball object off another Sprite
   */
  public void bounceOffOtherSprite(Rectangle ballBBoxRect, Rectangle collidingBBoxRect) {
    double intersectionUpper = Math.max(collidingBBoxRect.getY(),
        ballBBoxRect.getY());
    double intersectionLower = Math.min(
        collidingBBoxRect.getY() + collidingBBoxRect.getHeight(),
        ballBBoxRect.getY() + ballBBoxRect.getHeight());
    double intersectionLeft = Math.max(collidingBBoxRect.getX(),
        ballBBoxRect.getX());
    double intersectionRight = Math.min(
        collidingBBoxRect.getX() + collidingBBoxRect.getWidth(),
        ballBBoxRect.getX() + ballBBoxRect.getWidth());
    double intersectionHeight = intersectionLower - intersectionUpper;
    double intersectionWidth = intersectionRight - intersectionLeft;

    if (intersectionHeight > intersectionWidth) {
      this.bounceX();
    } else {
      this.bounceY();
    }
  }

  /**
   * Handle collision with Paddle
   */
  public void collideWithPaddle(Rectangle ballBBoxRect, Rectangle paddleBBoxRect) {
    double centerXBall = ballBBoxRect.getX() + radius;

    double differenceInXPositions = centerXBall - paddleBBoxRect.getX();
    double proportionalLocationAlongPaddle =
        differenceInXPositions / paddleBBoxRect.getWidth();
    double proportionalLocationAlongPaddleClamped = Math.max(0,
        Math.min(1, proportionalLocationAlongPaddle));

    double angle =
        Paddle.ANGLE_MIN + ((1 - proportionalLocationAlongPaddleClamped) * Paddle.ANGLE_RANGE);

    this.setVelocity(angle, this.getSpeed());
  }

  /**
   * Get bounding box Rectangle (which happens to be a square) that encloses the Ball.
   */
  public Rectangle getBoundingBoxRect() {
    double radius = this.getRadius();
    Rectangle bboxRect = new Rectangle(radius * 2, radius * 2);
    bboxRect.setX(getPosition().getX());
    bboxRect.setY(getPosition().getY());
    return bboxRect;
  }

  /**
   * Update the Ball. Update position based on velocity. Check if the ball has gone below the screen
   * and if so then decrement lives.
   *
   * @param time             Elapsed time since last frame.
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
   *
   * @return If the Ball is below the screen.
   */
  public boolean isBelowScreen() {
    return (getPosition().getY() >= Breakout.SIZE);
  }

  /**
   * Gets radius of the ball.
   *
   * @return radius of the ball as a double.
   */
  public double getRadius() {
    return this.radius;
  }
}
