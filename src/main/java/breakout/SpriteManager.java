package breakout;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SpriteManager handles the sprites that are active in the game. It keeps track of all projectiles,
 * and blocks that are active. It also has functions for checking and handling collisions.
 * @author Tyler Feldman
 */
public class SpriteManager {

  private List<Sprite> sprites = new ArrayList<>();
  private List<Projectile> projectiles = new ArrayList<>();
  private List<Block> blocks = new ArrayList<>();

  private Group rootNode;

  /**
   * Constructor for SpriteManager.
   * @param rootNode Group root for the scene.
   */
  public SpriteManager(Group rootNode) {
    this.rootNode = rootNode;
  }

  /**
   * Updates all sprites that are active.
   * @param elapsedTime double time elapsed since last frame.
   */
  public void updateSprites(double elapsedTime) {
    for (Sprite sprite : sprites) {
      sprite.update(elapsedTime);
    }
  }

  /**
   * Adds List of sprites to the List of active sprites.
   * @param sprites List of Sprites.
   */
  public void addSprites(List<Sprite> sprites) {
    for (Sprite sprite : sprites) {
      addSprite(sprite);
    }
  }

  /**
   * Adds a variable number of Sprite objects to the List of active sprites.
   * @param sprites Variable number of Sprite objects.
   */
  public void addSprites(Sprite... sprites) {
    addSprites(Arrays.asList(sprites));
  }

  /**
   * Adds a Sprite to the List of active Sprite objects.
   * @param sprite Sprite to add.
   */
  public void addSprite(Sprite sprite) {
    addSpriteToRootNode(sprite);
    addSpriteToSpriteManagerLists(sprite);
  }

  /**
   * Add Sprite to children of root node.
   * @param sprite Sprite to add to root node children.
   */
  public void addSpriteToRootNode(Sprite sprite) {
    rootNode.getChildren().add(sprite.getShape());
  }

  /**
   * Add Sprite to the SpriteManager lists.
   * @param sprite Sprite to add.
   */
  public void addSpriteToSpriteManagerLists(Sprite sprite) {
    sprites.add(sprite);
    if (sprite instanceof Projectile) {
      projectiles.add((Projectile) sprite);
    } else if (sprite instanceof Block) {
      blocks.add((Block) sprite);
    }
  }

  /**
   * Remove List of Sprite objects from active sprites.
   * @param sprites List of Sprite objects to remove.
   */
  public void removeSprites(List<Sprite> sprites) {
    for (Sprite sprite : sprites) {
      removeSprite(sprite);
    }
  }

  /**
   * Removes a variable number of Sprite objects from active sprites.
   * @param sprites
   */
  public void removeSprites(Sprite... sprites) {
    removeSprites(Arrays.asList(sprites));
  }

  /**
   * Remove Sprite from the active sprites.
   * @param sprite Sprite to remove.
   */
  public void removeSprite(Sprite sprite) {
    removeSpriteFromRootNode(sprite);
    removeSpriteFromSpriteManagerLists(sprite);
  }

  /**
   * Remove a Sprite from the list of children of the root node.
   * @param sprite Sprite to remove.
   */
  public void removeSpriteFromRootNode(Sprite sprite) {
    rootNode.getChildren().remove(sprite.getShape());
  }

  /**
   * Remove Sprite from SpriteManager internal lists.
   * @param sprite
   */
  public void removeSpriteFromSpriteManagerLists(Sprite sprite) {
    sprites.remove(sprite);
    if (sprite instanceof Projectile) {
      projectiles.remove((Projectile) sprite);
    } else if (sprite instanceof Block) {
      blocks.remove((Block) sprite);
    }
  }

  /**
   * Check for collisions between Projectile objects and Sprite objects.
   */
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

  /**
   * Handle collision between Sprite and Projectile.
   * @param sprite Sprite object that has collided with projectile.
   * @param projectile Projectile object that has collided with sprite.
   * @return Whether a collision has occured.
   */
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

  /**
   * Checks if two Sprite objects are intersecting.
   * @param spriteA First Sprite object.
   * @param spriteB Second Sprite object.
   * @return Whether the two Sprite objects have collided.
   */
  public boolean isIntersecting(Sprite spriteA, Sprite spriteB) {
    Shape intersection = Shape.intersect(spriteA.getShape(), spriteB.getShape());
    return (intersection.getBoundsInLocal().getWidth() != -1);  // From example_animation
  }
}
