package breakout;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.awt.*;

// https://gist.github.com/jewelsea/910dae32ef6d1bd4257850031aefcba2#file-breakoutgame-java

public class Sprite
{
    private Shape shape;
    private Point2D position;
    private Point2D velocity;

    public Sprite(Shape shape, Point2D position, Point2D velocity)
    {
        this.shape = shape;
        this.position = position;
        this.velocity = velocity;
    }

    public void setPosition(Point2D position)
    {
        this.position = position;
        this.shape.
    }

    public void setVelocity(Point2D velocity)
    {
        this.velocity = velocity;
    }

    public void update(double time)
    {
        Point2D displacement = new Point2D(velocity.getX() * time, velocity.getY() * time);
        position = position.add(displacement);
    }


    public Shape getShape()
    {
        return shape;
    }

//    public boolean intersects(Sprite s)
//    {
//        return s.getShape().intersects( this.getShape() );
//    }

}