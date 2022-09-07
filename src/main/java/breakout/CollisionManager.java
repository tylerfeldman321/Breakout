package breakout;

import java.util.List;
import javafx.scene.shape.Shape;

/**
 * CollisionManager handles detecting collisions between sprites in the game, and then
 * handling the collisions by calling the handleCollisionWith() function for the
 * Sprite objects that have collided.
 */
public class CollisionManager {
  private SpriteManager spriteManager;
  private GameWorldManager gameWorldManager;

  /**
   * Constructor for collision manager.
   *
   * @param gameWorldManager GameWorldManager used for the game.
   * @param spriteManager SpriteManager for the game.
   */
  public CollisionManager(GameWorldManager gameWorldManager, SpriteManager spriteManager) {
    this.gameWorldManager = gameWorldManager;
    this.spriteManager = spriteManager;
  }

  /**
   * Check for collisions between Sprite objects that are moving and Sprite objects.
   * Checking for collisions between just moving sprites and other sprites saves computation
   * since two sprites that aren't moving will not be able to collide.
   */
  public void checkCollisions() {
    Sprite movingSprite;
    Sprite sprite;

    List<Sprite> sprites = spriteManager.getSprites();
    List<Sprite> movingSprites = spriteManager.getMovingSprites();

    for (int i = 0; i < movingSprites.size(); i++) {
      movingSprite = movingSprites.get(i);
      for (int j = 0; j < sprites.size(); j++) {
        sprite = sprites.get(j);
        if (spritesHaveCollided(sprite, movingSprite)) {
          break;
        }
      }
    }
  }

  /**
   * Check if collision has occurred, handle collision between Sprite and Projectile, and return if
   * a collision has occurred.
   *
   * @param spriteA     Sprite object that has collided with projectile.
   * @param spriteB Projectile object that has collided with sprite.
   * @return Whether a collision has occurred.
   */
  public boolean spritesHaveCollided(Sprite spriteA, Sprite spriteB) {
    if (spriteA == spriteB) {
      return false;
    }

    if (isIntersecting(spriteA, spriteB)) {
      spriteA.handleCollisionWith(spriteB, gameWorldManager);
      spriteB.handleCollisionWith(spriteA, gameWorldManager);
      return true;
    }
    return false;
  }

  /**
   * Checks if two Sprite objects are intersecting.
   *
   * @param spriteA First Sprite object.
   * @param spriteB Second Sprite object.
   * @return Whether the two Sprite objects have collided.
   */
  public boolean isIntersecting(Sprite spriteA, Sprite spriteB) {
    Shape intersection = Shape.intersect(spriteA.getShape(), spriteB.getShape());
    return (intersection.getBoundsInLocal().getWidth() != -1);  // From example_animation
  }
}
