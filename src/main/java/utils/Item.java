package utils;

/**
 * Item object class
 * Since the Item class only has integer weights and values, but in the fractional case,
 * selected items can have double weights and values, we’ll handle this by using only the Item class.
 * Therefore, we use double for weight and value.
 */
public class Item {
  /**
   * Weight of the item
   */
  private final int weight;
  /**
   * Value of the item
   */
  private final int value;

  /**
   * Constructor initializing the item object
   */
  public Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }

  /**
   * Retrieves the item's weight
   *
   * @return weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Retrieves the item's value
   *
   * @return value
   */
  public int getValue() {
    return value;
  }

  public double getRatio() {
    return (double) value / weight;
  }

  /**
   * Item's information
   * 
   * @return nicely printed string of the item's value and weight
   */
  @Override
  public String toString() {
    return value + "\n" + weight;
  }
}
