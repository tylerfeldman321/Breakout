package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Block extends Sprite {
    public Block(double width, double height, Point2D position, Color color) {
        super(new Rectangle(width, height, color),
                position,
                new Point2D(0, 0));
        this.getShape().setStrokeType(StrokeType.INSIDE);
        this.getShape().setStroke(Color.BLACK);
    }

    public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
        this.handleDeath(spriteManager);
    }

    public Rectangle getBoundingBoxRect() {
        return (Rectangle) this.getShape();
    }
}
