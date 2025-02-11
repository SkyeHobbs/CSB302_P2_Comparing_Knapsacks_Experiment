package core;

import utils.Item;
import java.util.List;

/**
 * AbstractKnapsackSolver serves as the base class for various knapsack solving algorithms
 * (e.g., Brute Force, Greedy, Dynamic Programming). It provides common functionality and
 * data structures for handling knapsack items, selected items, and results.
 *
 * <p>Subclasses are required to implement the {@code solve()} method with specific logic
 * for solving the knapsack problem.</p>
 */
public abstract class AbstractKnapsackSolver {

  protected List<Item> items; // The list of all items available for the knapsack.
  protected List<Item> selectedItems; // The list of items selected to be included in the knapsack after solving.
  protected int capacity; // The maximum weight capacity of the knapsack.
  protected double totalValue; // The total value of the items selected in the knapsack.
  protected int knapsackNumber; // The ID for the knapsack instance

  /**
   * Constructs an AbstractKnapsackSolver with the given items, capacity, and knapsack number.
   *
   * @param items          The list of items available for selection.
   * @param capacity       The maximum weight capacity of the knapsack.
   * @param knapsackNumber The identifier for the knapsack instance.
   */
  public AbstractKnapsackSolver(List<Item> items, int capacity, int knapsackNumber) {
    this.items = items;
    this.capacity = capacity;
    this.knapsackNumber = knapsackNumber;
  }

  /**
   * Solves the knapsack problem.
   * Subclasses must implement this method.
   */
  public abstract void solve();


  /**
   * Returns the solution details, including knapsack number, capacity, total value, and selected items.
   *
   * @return Solution details as a string.
   */
  public String getResults(){
    StringBuilder result = new StringBuilder();
    result.append("Knapsack Number: ").append(knapsackNumber).append("\n");
    result.append("Knapsack Capacity: ").append(capacity).append("\n");
    result.append("Total Value: ").append(totalValue).append("\n");
    result.append("Selected Items (Weight, Value): \n");
    for (Item item: selectedItems) {
      result.append(" - (").append(item.getWeight()).append(", ").append(item.getValue()).append(")\n");
    }
    return result.toString();
  }

  /** @return List of selected items. */
  public List<Item> getSelectedItems() {
    return selectedItems;
  }

  /** @return Total value of selected items. */
  public double getTotalValue() {
    return totalValue;
  }

  /** @return Knapsack's weight capacity. */
  public int getKnapsackCapacity() {
    return capacity;
  }

  /** @return Knapsack number. */
  public int getKnapsackNumber() {
    return knapsackNumber;
  }

  /**
   * Sets the total value of selected items.
   *
   * @param totalValue The total value.
   */
  protected void setTotalValue(double totalValue) {
    this.totalValue = totalValue;
  }

  /**
   * Sets the list of selected items.
   *
   * @param selectedItems The selected items.
   */
  protected void setSelectedItems(List<Item> selectedItems) {
    this.selectedItems = selectedItems;
  }
}
