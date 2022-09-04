package breakout;

import java.util.HashSet;
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

  private HashSet<Sprite> spritesToBeRemoved = new HashSet<>();
  private int numBlocksToBeRemoved = 0;
  private int numBallsInPlay = 0;

  private Group rootNode;
  private GameWorldManager gameWorldManager;

  /**
   * Constructor for SpriteManager.
   * @param rootNode Group root for the scene.
   */
  public SpriteManager(Group rootNode, GameWorldManager gameWorldManager) {
    this.rootNode = rootNode;
    this.gameWorldManager = gameWorldManager;
  }

  /**
   * Updates all sprites that are active.
   * @param elapsedTime double time elapsed since last frame.
   */
  public void updateSprites(double elapsedTime, GameWorldManager gameWorldManager) {
    for (Sprite sprite : sprites) {
      sprite.update(elapsedTime, gameWorldManager);
    }
  }

  /**
   * Remove all sprites that are in the List spritesToBeRemoved.
   */
  public void cleanupSprites() {
    for (Sprite sprite : spritesToBeRemoved) {
      removeSprite(sprite);
    }
    spritesToBeRemoved.clear();
    numBlocksToBeRemoved = 0;
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
      if (sprite instanceof Ball) numBallsInPlay++;
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
      if (sprite instanceof Block) numBlocksToBeRemoved++;
      spritesToBeRemoved.add(sprite);
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
  private void removeSprite(Sprite sprite) {
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
      if (sprite instanceof Ball) numBallsInPlay--;
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
        if (spritesHaveCollided(sprite, projectile)) {
          break;
        }
      }
    }
  }

  /**
   * Check if collision has occurred, handle collision between Sprite and Projectile, and return
   * if a collision has occurred.
   * @param sprite Sprite object that has collided with projectile.
   * @param projectile Projectile object that has collided with sprite.
   * @return Whether a collision has occurred.
   */
  public boolean spritesHaveCollided(Sprite sprite, Projectile projectile) {
    if (sprite == projectile) {
      return false;
    }

    if (isIntersecting(sprite, projectile)) {
      projectile.handleCollisionWith(sprite, gameWorldManager);
      sprite.handleCollisionWith(projectile, gameWorldManager);
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

  /**
   * Checks if there are no active Block objects remaining.
   * @return true if no active Block objects remaining.
   */
  public boolean noBlocksRemaining() {
    return (blocks.size() - numBlocksToBeRemoved == 0);
  }

  /**
   * Clears all sprites Shape objects from the viewing scene.
   */
  public void clearAllSpritesFromView() {
    for (Sprite sprite : sprites) {
      rootNode.getChildren().remove(sprite.getShape());
    }
  }

  /**
   * Get number of balls in play.
   * @return Number of balls in play.
   */
  public int getNumBallsInPlay() {
    return numBallsInPlay;
  }
}
