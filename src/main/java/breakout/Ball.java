package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Projectile {
    public Ball(double radius, Point2D position, Point2D velocity, Color color) {
        super(new Circle(radius, color),
                position,
                velocity);
    }

    public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
        Rectangle ballBBoxRect = getBoundingBoxRect();
        Rectangle collidingBBoxRect = sprite.getBoundingBoxRect();

        double intersectionUpper = Math.max(collidingBBoxRect.getY(), ballBBoxRect.getY());
        double intersectionLower = Math.min(collidingBBoxRect.getY()+collidingBBoxRect.getHeight(),
                ballBBoxRect.getTranslateY()+collidingBBoxRect.getHeight());
        double intersectionLeft = Math.max(collidingBBoxRect.getX(), ballBBoxRect.getX());
        double intersectionRight = Math.min(collidingBBoxRect.getX()+collidingBBoxRect.getWidth(),
                ballBBoxRect.getTranslateX()+collidingBBoxRect.getWidth());
        double intersectionHeight = intersectionLower - intersectionUpper;
        double intersectionWidth = intersectionRight - intersectionLeft;

        if (intersectionHeight > intersectionWidth) {
            this.bounceX();
        } else {
            this.bounceY();
        }
    }

    public Rectangle getBoundingBoxRect() {
        double radius = ((Circle)this.getShape()).getRadius();
        return new Rectangle(getPosition().getX()-radius, getPosition().getY()-radius, radius*2, radius*2);
    }
}
