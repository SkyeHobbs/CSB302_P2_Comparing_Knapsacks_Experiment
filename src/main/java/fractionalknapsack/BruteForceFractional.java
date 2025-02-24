package fractionalknapsack;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import utils.Item;

import java.util.HashMap;
import java.util.Map;

public class BruteForceFractional extends AbstractKnapsackSolver {
  /**
   * Constructs an AbstractKnapsackSolver with the given Knapsack.
   *
   * @param knapsack The input knapsack instance.
   */
  public BruteForceFractional(Knapsack knapsack) {
    super(knapsack);
    selectedItems = new HashMap<>();
    selectedTotalValue = 0;
  }

  /**
   * Solves the knapsack problem.
   * Subclasses must implement this method.
   */
  @Override
  public void solve() {
    Map<Item, Double> bestSelection = new HashMap<>();
    double bestValue = fractionalHelper(knapsack.getItems().size(), knapsack.getKnapsackCapacity(), bestSelection);
    selectedItems.putAll(bestSelection);
    setSelectedTotalValue(bestValue);
  }

  /**
   * Recursive brute force function that finds the best value by trying every combination
   * breaking the final item into a fractional value to fully use capacity.
   *
   * @param n             The number of items being considered.
   * @param capacity      The remaining capacity of the knapsack.
   * @param bestSelection A map to store the best selection of items.
   * @return The maximum value.
   */
  private double bruteForceFractional(int n, int capacity, Map<Item, Double> bestSelection) {
    // Ensure there are items and capacity
    if (n == 0 || capacity == 0) {
      return 0;
    }

    Item currentItem = knapsack.getItems().get(n - 1); // Save the current item

    // If the current item is to heavy skip it
    if (currentItem.getWeight() > capacity) {
      return fractionalHelper(n, capacity, bestSelection);
    }

    // Create copies of bestSelection to track item inclusion and exclusion
    Map<Item, Double> includeSelection = new HashMap<>(bestSelection);
    Map<Item, Double> excludeSelection = new HashMap<>(bestSelection);

    // Save the value if the item is included or excluded
    double include = currentItem.getValue() + bruteForceFractional(n - 1, capacity - currentItem.getWeight(), includeSelection);
    double exclude = bruteForceFractional(n - 1, capacity, excludeSelection);

    // Select the better option
    if (include > exclude) {
      includeSelection.put(currentItem, currentItem.getRatio());
      bestSelection.clear();
      bestSelection.putAll(includeSelection);
      return include;
    } else {
      bestSelection.clear();
      bestSelection.putAll(excludeSelection);
      return exclude;
    }
  }


  /**
   * Helper method to get the best fractional value of the current items
   *
   * @param n             The number of items being considered.
   * @param capacity      The remaining capacity of the knapsack.
   * @param bestSelection A map to store the best selection of items.
   * @return The maximum value.
   */
  private double fractionalHelper(int n, double capacity, Map<Item, Double> bestSelection) {
    // Ensure there are items and capacity
    if (n <= 0 || capacity <= 0) {
      return 0;
    }

    Item currentItem = knapsack.getItems().get(n - 1);  // Save the current item
    Map<Item, Double> bestCurrentSelection = new HashMap<>(); // Save the best current selection
    double bestValue = 0.0; // Save the current total value

    for (int i = 0; i <= 10; i++) {
      double fraction = i / 10.0; // Iterate through each fraction

      // Save the fractional weight and value
      double fractionalWeight = currentItem.getWeight() * fraction;
      double fractionalValue = currentItem.getValue() * fraction;

      // If the fractional item is to heavy end the loop
      if (fractionalWeight > capacity) {
        break;
      }

      // Save the current item and fractional value included in a temp current selection
      Map<Item, Double> includeSelection = new HashMap<>(bestSelection);
      includeSelection.put(currentItem, fractionalValue);

      // Get the total value
      double totalValue = fractionalValue + fractionalHelper(n - 1, capacity - fractionalWeight, includeSelection);

      // If the total value with the fractional value included is better than the current best value
      // Add the current item to the current best selection and update the
      if (totalValue > bestValue) {
        bestValue = totalValue;
        bestCurrentSelection = new HashMap<>(includeSelection);
      }
    }

    // Update the current best selection
    bestSelection.clear();
    bestSelection.putAll(bestCurrentSelection);
    return bestValue; // Return the best value
  }
}
