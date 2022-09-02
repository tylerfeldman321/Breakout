package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Sprite {
    public Block(int width, int height, Point2D position, Color color) {
        super(new Rectangle(width, height, color),
                position,
                new Point2D(0, 0));
    }

    public void handleCollisionWith(Sprite sprite, SpriteManager spriteManager) {
        this.handleDeath(spriteManager);
    }

    public Rectangle getBoundingBoxRect() {
        return (Rectangle) this.getShape();
    }
}
