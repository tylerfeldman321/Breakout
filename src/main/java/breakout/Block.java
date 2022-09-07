package breakout;

import breakout.projectiles.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Block is a Rectangle shaped object that is destroyed when a Projectile collides with it.
 *
 * @author Tyler Feldman
 */
public class Block extends Sprite {

  /**
   * Constructor for Block.
   *
   * @param width    Width of the block.
   * @param height   Height of the block.
   * @param position Point2D for the top left position of the Block.
   * @param color    Color for the fill of the Rectangle.
   */
  public Block(double width, double height, Point2D position, Color color) {
    super(new Rectangle(width, height, color),
        position,
        new Point2D(0, 0), false);
    this.getShape().setStrokeType(StrokeType.INSIDE);
    this.getShape().setStroke(Color.BLACK);
  }

  /**
   * Set shape position for the Block's shape.
   *
   * @param position Point2D top left position of the Block.
   */
  @Override
  public void setShapePosition(Point2D position) {
    Rectangle rectangle = (Rectangle) this.getShape();
    rectangle.setX(position.getX());
    rectangle.setY(position.getY());
  }

  /**
   * Handles collision with another Sprite. This block is destroyed on collision.
   *
   * @param sprite           Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
    if (sprite instanceof Projectile) {
      gameWorldManager.incrementScore();
      this.handleDeath(gameWorldManager);
      gameWorldManager.checkIfPlayerHasWon();
      gameWorldManager.getPowerUpGenerator().possiblyGenerateRandomPowerUp(this.getCenter());
    }
  }

  /**
   * Get the bounding box Rectangle for the Block.
   *
   * @return Rectangle for the collision bounding box for the Block.
   */
  public Rectangle getBoundingBoxRect() {
    return (Rectangle) this.getShape();
  }

  /**
   * Get center of the Block.
   * TODO: make static / move to another class since this could be used by any class that
   * has a rectangle as its internal Shape.
   *
   * @return Point2D for the center of the Block object.
   */
  private Point2D getCenter() {
    Rectangle rectangle = getBoundingBoxRect();
    return new Point2D(rectangle.getX() + rectangle.getWidth() / 2,
        rectangle.getY() + rectangle.getHeight() / 2);
  }
}
