package breakout;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Sprite {
    public Block(int width, int height, Point2D position, Color color) {
        super(new Rectangle(width, height, color),
                new BoundingBox(position.getX(), position.getY(), width, height),
                position,
                new Point2D(0, 0));
    }
}
