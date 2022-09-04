package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Block is a Rectangle shaped object that is destroyed when a Projectile collides with it.
 * @author Tyler Feldman
 */
public class Block extends Sprite {

  /**
   * Constructor for Block.
   * @param width Width of the block.
   * @param height Height of the block.
   * @param position Point2D for the top left position of the Block.
   * @param color Color for the fill of the Rectangle.
   */
  public Block(double width, double height, Point2D position, Color color) {
    super(new Rectangle(width, height, color),
        position,
        new Point2D(0, 0));
    this.getShape().setStrokeType(StrokeType.INSIDE);
    this.getShape().setStroke(Color.BLACK);
  }

  /**
   * Handles collision with another Sprite. This block is destroyed on collision.
   * @param sprite Other sprite that is colliding with this Sprite.
   * @param gameWorldManager GameWorldManager object for the game.
   */
  public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
    gameWorldManager.incrementScore();
    this.handleDeath(gameWorldManager);
  }

  /**
   * Get the bounding box Rectangle for the Block.
   * @return Rectangle for the collision bounding box for the Block.
   */
  public Rectangle getBoundingBoxRect() {
    return (Rectangle) this.getShape();
  }
}
