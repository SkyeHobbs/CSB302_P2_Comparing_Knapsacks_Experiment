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
<<<<<<< HEAD
   * Retrieves items weight
   *
   * @return items weight
=======
   * Retrieves the item's weight
   *
   * @return weight
>>>>>>> upstream/main
   */
  public int getWeight() {
    return weight;
  }

  /**
<<<<<<< HEAD
   * Retrieves item's value
   *
   * @return item value
=======
   * Retrieves the item's value
   *
   * @return value
>>>>>>> upstream/main
   */
  public int getValue() {
    return value;
  }

  /**
   * Retrieves the ratio of the item
   *
   * @return value / weight
   */
  public double getRatio() {
    return (double) value / weight;
  }

  /**
<<<<<<< HEAD
   * Presents this item's information in a nice and neat way
   *
   * @return A neat display of this item's information
=======
   * Item's information
   * 
   * @return nicely printed string of the item's value and weight
>>>>>>> upstream/main
   */
  @Override
  public String toString() {
    return value + "\n" + weight;
  }
}
