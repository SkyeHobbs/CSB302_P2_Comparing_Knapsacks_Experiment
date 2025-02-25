package knapsackzeroone;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import java.util.HashMap;
import java.util.Map;
import utils.Item;

/**
 * Solves an 01 knapsack problem with brute force.
 */
public class BruteForce01 extends AbstractKnapsackSolver {
  /**
   * Constructs an AbstractKnapsackSolver with the given Knapsack.
   *
   * @param knapsack The input knapsack instance.
   */
  public BruteForce01(Knapsack knapsack) {
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
    double bestValue = bruteForce01(knapsack.getItems().size(),
            knapsack.getKnapsackCapacity(), bestSelection);
    selectedItems.putAll(bestSelection);
    setSelectedTotalValue(bestValue);
  }

  /**
   * Recursive brute force function that finds the best value by trying every combination.
   *
   * @param n             The number of items being considered.
   * @param capacity      The remaining capacity of the knapsack.
   * @param bestSelection A map to store the best selection of items.
   * @return The maximum value.
   */
  private double bruteForce01(int n, int capacity, Map<Item, Double> bestSelection) {
    // Ensure there are items and capacity
    if (n == 0 || capacity == 0) {
      return 0;
    }

    Item currentItem = knapsack.getItems().get(n - 1); // Save the current item

    // If the current item is to heavy skip it
    if (currentItem.getWeight() > capacity) {
      return bruteForce01(n - 1, capacity, bestSelection);
    }

    // Create copies of bestSelection to track item inclusion and exclusion
    Map<Item, Double> includeSelection = new HashMap<>(bestSelection);
    Map<Item, Double> excludeSelection = new HashMap<>(bestSelection);

    // Save the value if the item is included or excluded
    double include = currentItem.getValue() + bruteForce01(n - 1,
            capacity - currentItem.getWeight(), includeSelection);
    double exclude = bruteForce01(n - 1, capacity, excludeSelection);

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
}