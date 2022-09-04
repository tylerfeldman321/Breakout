package breakout;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpriteManager {

  private List<Sprite> sprites = new ArrayList<>();
  private List<Projectile> projectiles = new ArrayList<>();
  private List<Block> blocks = new ArrayList<>();

  private Group rootNode;

  public SpriteManager(Group rootNode) {
    this.rootNode = rootNode;
  }

  public void updateSprites(double elapsedTime) {
    for (Sprite sprite : sprites) {
      sprite.update(elapsedTime);
    }
  }

  public void addSprites(List<Sprite> sprites) {
    for (Sprite sprite : sprites) {
      addSprite(sprite);
    }
  }

  public void addSprites(Sprite... sprites) {
    addSprites(Arrays.asList(sprites));
  }

  public void addSprite(Sprite sprite) {
    addSpriteToRootNode(sprite);
    addSpriteToSpriteManagerLists(sprite);
  }

  public void addSpriteToRootNode(Sprite sprite) {
    rootNode.getChildren().add(sprite.getShape());
  }

  public void addSpriteToSpriteManagerLists(Sprite sprite) {
    sprites.add(sprite);
    if (sprite instanceof Projectile) {
      projectiles.add((Projectile) sprite);
    } else if (sprite instanceof Block) {
      blocks.add((Block) sprite);
    }
  }

  public void removeSprites(List<Sprite> sprites) {
    for (Sprite sprite : sprites) {
      removeSprite(sprite);
    }
  }

  public void removeSprites(Sprite... sprites) {
    removeSprites(Arrays.asList(sprites));
  }

  public void removeSprite(Sprite sprite) {
    removeSpriteFromRootNode(sprite);
    removeSpriteFromSpriteManagerLists(sprite);
  }

  public void removeSpriteFromRootNode(Sprite sprite) {
    rootNode.getChildren().remove(sprite.getShape());
  }

  public void removeSpriteFromSpriteManagerLists(Sprite sprite) {
    sprites.remove(sprite);
    if (sprite instanceof Projectile) {
      projectiles.remove((Projectile) sprite);
    } else if (sprite instanceof Block) {
      blocks.remove((Block) sprite);
    }
  }

  protected void checkCollisions() {
    Projectile projectile;
    Sprite sprite;

    for (int i = 0; i < projectiles.size(); i++) {
      projectile = projectiles.get(i);
      for (int j = 0; j < sprites.size(); j++) {
        sprite = sprites.get(j);
        if (handleCollision(sprite, projectile)) {
          break;
        }
      }
    }
  }

  public boolean handleCollision(Sprite sprite, Projectile projectile) {
    if (sprite == projectile) {
      return false;
    }

    if (isIntersecting(sprite, projectile)) {
      projectile.handleCollisionWith(sprite, this);
      sprite.handleCollisionWith(projectile, this);
      return true;
    }
    return false;
  }

  public boolean isIntersecting(Sprite spriteA, Sprite spriteB) {
    Shape intersection = Shape.intersect(spriteA.getShape(), spriteB.getShape());
    return (intersection.getBoundsInLocal().getWidth() != -1);  // From example_animation
  }
}
