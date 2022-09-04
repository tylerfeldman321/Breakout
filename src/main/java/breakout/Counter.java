package breakout;

import javafx.geometry.Point2D;
import javafx.scene.text.Text;

/**
 * Counter displays a label and a value to the user. The value can be incremented or set, and the
 * counter will be updated accordingly.
 *
 * @author Tyler Feldman
 */
public class Counter {

  private double value;
  private Text text;
  private String baseString;

  /**
   * Constructor for Counter. The value for the counter is appended to the baseString, so if the
   * counter is supposed to display "Lives: 3", then baseString would be "Lives: ", and the
   * startValue would be 3.
   *
   * @param position   Point2D for top left position of Counter.
   * @param baseString String for the label.
   * @param startValue Starting value for the counter.
   */
  public Counter(Point2D position, String baseString, double startValue) {
    String startString = baseString + (int) startValue;
    text = new Text(position.getX(), position.getY(), startString);

    this.baseString = baseString;
    this.value = startValue;
  }

  /**
   * Get the Text object used in the Counter.
   *
   * @return Text object.
   */
  public Text getText() {
    return this.text;
  }

  /**
   * Increment the counter value by the input and update the display text.
   *
   * @param value double value by which to change the counter's value.
   */
  public void add(double value) {
    this.value += value;
    String newCounterString = baseString + (int) this.value;
    this.text.setText(newCounterString);
  }

  /**
   * Set the counter value to the input and update the display.
   *
   * @param value double to which to set the counter value.
   */
  public void setValue(double value) {
    this.value = value;
    String newCounterString = baseString + (int) this.value;
    this.text.setText(newCounterString);
  }

  /**
   * Get value of the Counter.
   *
   * @return value of the Counter.
   */
  public double getValue() {
    return this.value;
  }
}
