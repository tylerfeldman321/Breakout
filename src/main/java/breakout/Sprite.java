package breakout;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Tyler Feldman
 * Inspiration taken from https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
 */
public class Sprite
{
    private ImageView image;
    private Shape shape;
    private Rectangle boundingBox;
    private Point2D position;
    private Point2D velocity;

    public Sprite(Shape shape, Rectangle boundingBox, Point2D position, Point2D velocity)
    {
        image = null;
        this.shape = shape;
        this.boundingBox = boundingBox;
        setPosition(position);
        this.velocity = velocity;
    }

    public Sprite(Shape shape) {
        this.shape = shape;
        this.position = new Point2D(0, 0);
        this.velocity = new Point2D(0, 0);
    }

    public void setPosition(Point2D position)
    {
        this.position = position;
        this.shape.setTranslateX(position.getX());
        this.shape.setTranslateY(position.getY());
        this.boundingBox.
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void setVelocity(Point2D velocity)
    {
        this.velocity = velocity;
    }

    public Point2D getVelocity() { return this.velocity; }

    public void update(double time)
    {
        Point2D displacement = new Point2D(velocity.getX() * time, velocity.getY() * time);
        setPosition(position.add(displacement));
    }

    public Shape getShape()
    {
        return shape;
    }

    public Rectangle getBounds() { return boundingBox; }

    public void setBounds(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
}