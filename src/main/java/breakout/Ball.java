package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Projectile {
    private double radius;
    public Ball(double radius, Point2D position, Point2D velocity, Color color) {
        super(new Circle(radius, color),
                position,
                velocity);
        this.radius = radius;
    }

    public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
        Rectangle ballBBoxRect = getBoundingBoxRect();
        Rectangle collidingBBoxRect = sprite.getBoundingBoxRect();

        double intersectionUpper = Math.min(collidingBBoxRect.getTranslateY(), ballBBoxRect.getTranslateY());
        double intersectionLower = Math.max(collidingBBoxRect.getTranslateY()+collidingBBoxRect.getHeight(),
                ballBBoxRect.getTranslateY()+collidingBBoxRect.getHeight());
        double intersectionLeft = Math.max(collidingBBoxRect.getTranslateX(), ballBBoxRect.getTranslateX());
        double intersectionRight = Math.min(collidingBBoxRect.getTranslateX()+collidingBBoxRect.getWidth(),
                ballBBoxRect.getTranslateX()+collidingBBoxRect.getWidth());
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
            double proportionalLocationAlongPaddle = differenceInXPositions / collidingBBoxRect.getWidth();
            double proportionalLocationAlongPaddleClamped = Math.max(0, Math.min(1, proportionalLocationAlongPaddle));

            double angle = Paddle.ANGLE_MIN + ((1-proportionalLocationAlongPaddleClamped) * Paddle.ANGLE_RANGE);

            this.setVelocity(angle, this.getSpeed());
        }
    }

    public Rectangle getBoundingBoxRect() {
        double radius = ((Circle)this.getShape()).getRadius();
        Rectangle bboxRect = new Rectangle(radius*2, radius*2);
        bboxRect.setTranslateX(getPosition().getX()-radius);
        bboxRect.setTranslateY(getPosition().getY()-radius);
        return bboxRect;
    }
}
