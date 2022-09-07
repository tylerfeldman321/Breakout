package breakout;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Sprite is the abstract base class for any object that exists in the game. It represents an object
 * that has a shape, position, and velocity and interacts with other objects in the game.
 * Inspiration taken from
 * https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
 * and https://dzone.com/articles/javafx-2-game-tutorial-part-2
 *
 * @author Tyler Feldman
 */
public abstract class Sprite {

  private ImageView image;
  private Shape shape;
  private Point2D position;
  private Point2D velocity;
  private boolean isAMovingSprite;

  /**
   * Creates a Sprite object.
   *
   * @param shape    shape of the sprite.
   * @param position initial position of the sprite.
   * @param velocity initial velocity of the sprite.
   */
  public Sprite(Shape shape, Point2D position, Point2D velocity, boolean isAMovingSprite) {
    image = null;
    this.shape = shape;
    this.velocity = velocity;
    this.isAMovingSprite = isAMovingSprite;

    setAllPositions(position);
  }

  /**
   * Creates sprite object with an initial position and velocity of (0, 0).
   *
   * @param shape Shape for the sprite object.
   */
  public Sprite(Shape shape) {
    this.shape = shape;
    this.position = Point2D.ZERO;
    this.velocity = Point2D.ZERO;
  }

  /**
   * Set the position for the Sprite object.
   *
   * @param position Point2D representing the top left of the sprite.
   */
  public void setPosition(Point2D position) {
    this.position = position;
  }

  /**
   * Set the position for the Sprite object.
   *
   * @param x x-position of the top left of the sprite.
   * @param y y-position of the top left of the sprite.
   */
  public void setPosition(double x, double y) {
    this.position = new Point2D(x, y);
  }

  /**
   * Set the x-position of the Sprite.
   *
   * @param x x-position of the top left of the sprite.
   */
  public void setPositionX(double x) {
    this.position = new Point2D(x, this.position.getY());
  }

  /**
   * Set the y-position of the Sprite.
   *
   * @param y y-position of the top left of the sprite.
   */
  public void setPositionY(double y) {
    this.position = new Point2D(this.position.getX(), y);
  }

  /**
   * Set the position for all components of the sprite. This includes the position and the x and y
   * translations for the internal shape.
   *
   * @param position
   */
  public void setAllPositions(Point2D position) {
    this.position = position;
    setShapePosition(position);
  }

  public abstract void setShapePosition(Point2D position);

  /**
   * Gets position.
   *
   * @return Point2D for the top left position of the sprite.
   */
  public Point2D getPosition() {
    return this.position;
  }

  /**
   * Sets the velocity of the sprite given a new velocity.
   *
   * @param velocity the new velocity
   */
  public void setVelocity(Point2D velocity) {
    this.velocity = velocity;
  }

  /**
   * Sets the velocity of the sprite, given an angle and speed. Assumes the angle is given in
   * radians.
   *
   * @param angle the angle in degrees of the velocity vector. From user's perspective, 0 degrees is
   *              to the right, increases positively going counterclockwise. For example, an angle
   *              of 45 degrees would be up and to the right, and angle of 180 degrees would be to
   *              the left when viewed from the user.
   * @param speed magnitude of new velocity
   */
  public void setVelocity(double angle, double speed) {
    double radians = Math.toRadians(angle);
    double velocityX = speed * Math.cos(radians);
    double velocityY = -speed * Math.sin(radians);
    setVelocity(new Point2D(velocityX, velocityY));
  }

  /**
   * Get the magnitude of the sprite's velocity.
   *
   * @return magnitude of the sprite's velocity.
   */
  public double getSpeed() {
    return getVelocity().magnitude();
  }

  /**
   * Get the velocity of the sprite.
   *
   * @return velocity of the sprite.
   */
  public Point2D getVelocity() {
    return this.velocity;
  }

  /**
   * Updates the sprite. Updates the position of the sprite.
   *
   * @param time Elapsed time since last frame.
   */
  public void update(double time, GameWorldManager gameWorldManager) {
    updatePosition(time);
  }

  /**
   * Updates the position of the sprite based on velocity.
   *
   * @param time How much time has elapsed since last frame.
   */
  public void updatePosition(double time) {
    Point2D displacement = new Point2D(velocity.getX() * time, velocity.getY() * time);
    setAllPositions(position.add(displacement));
  }

  /**
   * Get the internal shape of the Sprite object.
   *
   * @return Internal Shape of the Sprite object.
   */
  public Shape getShape() {
    return shape;
  }

  /**
   * Abstract function for Sprite object to handle collision with another Sprite.
   *
   * @param sprite           Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  public abstract void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager);

  /**
   * Abstract function to get the collision bounding box for the Sprite.
   *
   * @return Rectangle that represents the collision bounding box for the Sprite.
   */
  public abstract Rectangle getBoundingBoxRect();

  /**
   * Handle the death of the Sprite.
   *
   * @param gameWorldManager GameWorldManager for the game.
   */
  public void handleDeath(GameWorldManager gameWorldManager) {
    gameWorldManager.getSpriteManager().removeSprites(this);
  }

  /**
   * Reverses the velocity in the X direction.
   */
  public void bounceX() {
    Point2D newVelocity = new Point2D(-this.getVelocity().getX(), this.getVelocity().getY());
    this.setVelocity(newVelocity);
  }

  /**
   * Reverses the velocity in the Y direction.
   */
  public void bounceY() {
    Point2D newVelocity = new Point2D(this.getVelocity().getX(), -this.getVelocity().getY());
    this.setVelocity(newVelocity);
  }

  /**
   * Returns if the Sprite object is a moving Sprite.
   *
   * @return if the Sprite object is a moving Sprite.
   */
  public boolean isAMovingSprite() {
    return this.isAMovingSprite;
  }
}