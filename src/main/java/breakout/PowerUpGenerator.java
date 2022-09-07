package breakout;

import java.util.Random;
import javafx.geometry.Point2D;

/**
 * Class to generate random PowerUp objects.
 */
public class PowerUpGenerator {

  public static final double POWER_UP_GENERATION_CHANCE = 0.5;
  public static final Point2D POWER_UP_VELOCITY = new Point2D(0, 100);
  private GameWorldManager gameWorldManager;
  private static final Random rand = new Random();
  private static final PowerUp[] powerUpList = {
      new ExtraLifePowerUp(0, Point2D.ZERO, Point2D.ZERO)
  };

  /**
   * Constructor for PowerUpGenerator.
   *
   * @param gameWorldManager GameWorldManager used for the game.
   */
  public PowerUpGenerator(GameWorldManager gameWorldManager) {
    this.gameWorldManager = gameWorldManager;
  }

  /**
   * Generates a random PowerUp with probability POWER_UP_GENERATION_CHANCE.
   *
   * @param position Position for where to spawn the PowerUp.
   */
  public void possiblyGenerateRandomPowerUp(Point2D position) {
    boolean powerUpWillBeGenerated = rand.nextDouble() < POWER_UP_GENERATION_CHANCE;
    if (powerUpWillBeGenerated) {
      generateRandomPowerUp(position);
    }
  }

  /**
   * Create a random PowerUp from the powerUpList and spawn at position.
   *
   * @param position Point2D where to spawn the PowerUp
   */
  public void generateRandomPowerUp(Point2D position) {
    PowerUp randomPowerUp = powerUpList[rand.nextInt(powerUpList.length)];
    PowerUp newPowerUp = randomPowerUp.copy();
    newPowerUp.setPosition(position);
    newPowerUp.setVelocity(POWER_UP_VELOCITY);
    gameWorldManager.getSpriteManager().addSprites(newPowerUp);
  }

}
