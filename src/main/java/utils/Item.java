package utils;

/**
 * Item object class
 * Since the Item class only has integer weights and values, but in the fractional case,
 * selected items can have double weights and values, we’ll handle this by using only the Item class.
 * Therefore, we use double for weight and value.
 */
public class Item {
  private final double weight;
  private final double value;

  /**
   * Constructor initializing the item object
   */
  public Item(double weight, double value) {
    this.weight = weight;
    this.value = value;
  }

  public double getWeight() {
    return weight;
  }

  public double getValue() {
    return value;
  }

  public double getRatio() {
    return (double) value / weight;
  }
}
