package breakout;

import java.util.ArrayList;
import java.util.List;

public class SpriteManager {
    private List<Sprite> sprites = new ArrayList<>();

    public void updateSprites(double elapsedTime) {
        for (Sprite sprite : sprites) {
            sprite.update(elapsedTime);
        }
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void addSprites(List<Sprite> sprites) {
        for (Sprite sprite : sprites) {
            addSprite(sprite);
        }
    }
}
