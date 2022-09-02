package breakout;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Tyler Feldman
 * Inspiration taken from https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
 */
public abstract class Sprite
{
    private ImageView image;
    private Shape shape;
    private Point2D position;
    private Point2D velocity;

    public Sprite(Shape shape, Point2D position, Point2D velocity)
    {
        image = null;
        this.shape = shape;
        setAllPositions(position);
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
    }

    public void setAllPositions(Point2D position) {
        this.position = position;
        this.shape.setTranslateX(position.getX());
        this.shape.setTranslateY(position.getY());
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
        setAllPositions(position.add(displacement));
    }

    public Shape getShape()
    {
        return shape;
    }

    public abstract void handleCollisionWith(Sprite sprite, SpriteManager spriteManager);

    public abstract Rectangle getBoundingBoxRect();

    public void handleDeath(SpriteManager spriteManager) {
        spriteManager.removeSprite(this);
    }

    public void bounceX() {
        Point2D newVelocity = new Point2D(-this.getVelocity().getX(), this.getVelocity().getY());
        this.setVelocity(newVelocity);
    }

    public void bounceY() {
        Point2D newVelocity = new Point2D(this.getVelocity().getX(), -this.getVelocity().getY());
        this.setVelocity(newVelocity);
    }
}