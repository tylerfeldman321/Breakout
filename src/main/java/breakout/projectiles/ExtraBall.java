package breakout.projectiles;

import breakout.GameWorldManager;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * ExtraBall is a class that extends Ball and is used for any PowerUp that creates extra balls.
 * It is the same as the normal Ball object, but does not decrement the lives the user has
 * when it goes off the screen.
 */
public class ExtraBall extends Ball {
  private static final Color EXTRA_BALL_COLOR = Color.GREEN;
  private static final double EXTRA_BALL_RADIUS = 3;
  public static final double EXTRA_BALL_SPEED = 170;

  public ExtraBall(Point2D position, Point2D velocity) {
    super(EXTRA_BALL_RADIUS, position, velocity, EXTRA_BALL_COLOR);
  }

  /**
   * Update the ExtraBall. Update position based on velocity.
   * Check if the ball has gone below the screen and if so do NOT decrement lives, just destroy
   * the sprite.
   *
   * @param time             Elapsed time since last frame.
   * @param gameWorldManager GameWorldManager for the game.
   */
  @Override
  public void update(double time, GameWorldManager gameWorldManager) {
    if (isBelowScreen()) {
      gameWorldManager.getSpriteManager().removeSprites(this);
    }
    updatePosition(time);
  }
}
