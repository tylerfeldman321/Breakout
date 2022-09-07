package breakout.powerups;

import breakout.GameWorldManager;
import breakout.projectiles.ExtraBall;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class ExtraBallsPowerUp extends PowerUp {

  /**
   * Constructor for ExtraBallsPowerUp. The shape is a square with side lengths equal to Length.
   *
   * @param length   Length of the square.
   * @param position Point2D top left position of the powerup.
   * @param velocity Point2D initial velocity of the powerup.
   */
  public ExtraBallsPowerUp(double length, Point2D position, Point2D velocity) {
    super(length, Color.RED, position, velocity);
  }

  /**
   * Handles acquiring the powerup. Increments the lives for the player.
   *
   * @param gameWorldManager GameWorldManager for the game.
   */
  @Override
  public void acquirePowerUp(GameWorldManager gameWorldManager) {
    ExtraBall ball1 = new ExtraBall(this.getPosition(), Point2D.ZERO);
    ball1.setVelocity(45, ExtraBall.EXTRA_BALL_SPEED);

    ExtraBall ball2 = new ExtraBall(this.getPosition(), Point2D.ZERO);
    ball2.setVelocity(135, ExtraBall.EXTRA_BALL_SPEED);

    gameWorldManager.getSpriteManager().addSprites(ball1, ball2);
  }

  /**
   * Create copy of this object.
   *
   * @return Copy of this object.
   */
  @Override
  public PowerUp copy() {
    return new ExtraBallsPowerUp(this.getSideLength(), this.getPosition(), this.getVelocity());
  }
}
