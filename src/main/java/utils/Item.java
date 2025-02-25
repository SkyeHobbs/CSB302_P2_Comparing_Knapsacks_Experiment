package utils;

import java.util.Objects;

/**
 * Item object class Since the Item class only has integer weights and values, but in the fractional
 * case, selected items can have double weights and values, we’ll handle this by using only the Item
 * class. Therefore, we use double for weight and value.
 */
public class Item {
  /** Weight of the item. */
  private final int weight;

  /** Value of the item. */
  private final int value;

  /** Constructor initializing the item object. */
  public Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }

  /**
   * Retrieves the item's weight.
   *
   * @return weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Retrieves the item's value.
   *
   * @return value
   */
  public int getValue() {
    return value;
  }

  /**
   * Retrieves the ratio of the item.
   *
   * @return value / weight
   */
  public double getRatio() {
    return (double) value / weight;
  }

  /**
   * Indicates whether some other object is equal to this one.
   *
   * @param o the object we are comparing to
   * @return True if the object is the same as the other, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return weight == item.weight && value == item.value;
  }

  /**
   * Returns a hash code value for this Item.
   *
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(weight, value);
  }

  /**
   * Item's information.
   *
   * @return nicely printed string of the item's value and weight
   */
  @Override
  public String toString() {
    return value + " " + weight;
  }
}
