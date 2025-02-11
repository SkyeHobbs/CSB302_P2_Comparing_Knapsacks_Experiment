package utils;

/**
 * Item object class
 */
public class Item {
  private final int weight;
  private final int value;

  /**
   * Constructor initializing the item object
   */
  public Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }

  public int getWeight() {
    return weight;
  }

  public int getValue() {
    return value;
  }

  public double getRatio() {
    return (double) value / weight;
  }
}
