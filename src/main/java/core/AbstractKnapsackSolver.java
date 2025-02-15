package core;

import utils.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AbstractKnapsackSolver serves as the base class for various knapsack solving algorithms
 * (e.g., Brute Force, Greedy, Dynamic Programming). It provides common functionality and
 * data structures for handling knapsack items, selected items, and results.
 *
 * <p>Subclasses are required to implement the {@code solve()} method with specific logic
 * for solving the knapsack problem.</p>
 */
public abstract class AbstractKnapsackSolver {

  protected Knapsack knapsack;
  protected Map<Item, Double> selectedItems; // The map of the key value pair of selected
  // item and selected weight after solving.
  protected double selectedTotalValue; // The total value of the items selected in the knapsack.

  /**
   * Constructs an AbstractKnapsackSolver with the given Knapsack.
   *
   * @param knapsack The input knapsack instance.
   */
  public AbstractKnapsackSolver(Knapsack knapsack) {
    this.knapsack = knapsack;
  }

  /**
   * Solves the knapsack problem.
   * Subclasses must implement this method.
   */
  public abstract void solve();


  /**
   * Prints the details of the selected items in the knapsack,
   * including item values, selected weights, and total value.
   */
  public void printResult(){
    List<Integer> values = new ArrayList<>();
    List<Double> selectedWeights = new ArrayList<>();
    for (Map.Entry<Item, Double> entry: selectedItems.entrySet()){
      Item item = entry.getKey();
      double weight = entry.getValue();
      values.add(item.getValue());
      selectedWeights.add(weight);
    }

    StringBuilder result = new StringBuilder();
    result.append("Knapsack #: ").append(knapsack.getKnapsackNumber()).append("\n");
    result.append("Knapsack Capacity: ").append(knapsack.getKnapsackCapacity()).append("\n");
    result.append("Knapsack Values: ").append(values).append("\n");
    result.append("Knapsack Weights: ").append(selectedWeights).append("\n");
    result.append("Total Value: ").append(selectedTotalValue).append("\n");

    System.out.println(result);
  }

  /** @return List of selected items. */
  public Map<Item, Double> getSelectedItems() {
    return selectedItems;
  }


  /** @return Total value of selected items. */
  public double getsSelectedTotalValue() {
    return selectedTotalValue;
  }


  /**
   * Sets the total value of selected items.
   *
   * @param totalValue The total value.
   */
  protected void setSelectedTotalValue(double totalValue) {
    this.selectedTotalValue = totalValue;
  }

}