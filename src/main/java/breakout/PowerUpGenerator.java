package breakout;

import java.util.Random;
import javafx.geometry.Point2D;

/**
 * Class to generate random PowerUp objects.
 */
public class PowerUpGenerator {

  public static final double POWER_UP_GENERATION_CHANCE = 0.5;
  public static final Point2D POWER_UP_VELOCITY = new Point2D(0, 100);
  public static final double POWER_UP_RECTANGLE_SIZE = 10;
  private GameWorldManager gameWorldManager;
  private static final Random rand = new Random();
  private static final PowerUp[] powerUpList = {
      new ExtraLifePowerUp(POWER_UP_RECTANGLE_SIZE, Point2D.ZERO, Point2D.ZERO)
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
   * @param centerPosition Position for where to spawn the PowerUp.
   */
  public void possiblyGenerateRandomPowerUp(Point2D centerPosition) {
    boolean powerUpWillBeGenerated = rand.nextDouble() < POWER_UP_GENERATION_CHANCE;
    if (powerUpWillBeGenerated) {
      generateRandomPowerUp(centerPosition);
    }
  }

  /**
   * Create a random PowerUp from the powerUpList and spawn at position.
   *
   * @param centerPosition Point2D center position to spawn the PowerUp
   */
  public void generateRandomPowerUp(Point2D centerPosition) {
    Point2D topLeftPosition = centerPosition.add(new Point2D(-POWER_UP_RECTANGLE_SIZE / 2,
        -POWER_UP_RECTANGLE_SIZE / 2));

    PowerUp randomPowerUp = powerUpList[rand.nextInt(powerUpList.length)];
    PowerUp newPowerUp = randomPowerUp.copy();
    newPowerUp.setPosition(topLeftPosition);
    newPowerUp.setVelocity(POWER_UP_VELOCITY);
    gameWorldManager.getSpriteManager().addSprites(newPowerUp);
  }

}
