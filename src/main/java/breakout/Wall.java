package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Wall is a Sprite that has an internal Shape that is a Rectangle and does not move.
 * @author Tyler Feldman
 */
public class Wall extends Sprite {

    /**
     * Constructor for Wall
     * @param width Width of the wall.
     * @param height Height of the wall.
     * @param position Point2D for the top left corner of the wall.
     * @param color Color for the fill of the Rectangle.
     */
    public Wall(double width, double height, Point2D position, Color color) {
        super(new Rectangle(width, height, color),
                position,
                new Point2D(0, 0));
        this.getShape().setStrokeType(StrokeType.INSIDE);
        this.getShape().setStroke(Color.BLACK);
    }

    /**
     * Handles collision. Does nothing since this is a wall.
     * @param sprite Other sprite that is colliding with this Sprite.
     * @param gameWorldManager
     */
    public void handleCollisionWith(Sprite sprite, GameWorldManager gameWorldManager) {
        return;
    }

    /**
     * Returns the collision bounding box Rectangle for this object.
     * @return The collision bounding box Rectangle.
     */
    public Rectangle getBoundingBoxRect() {
        return (Rectangle) this.getShape();
    }
}
