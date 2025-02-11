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
  protected int totalValue; // The total value of the items selected in the knapsack.
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

  public abstract void solve();
}
