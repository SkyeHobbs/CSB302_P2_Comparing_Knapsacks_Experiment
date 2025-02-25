package fractionalknapsack;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Item;

/** Brute force algorithm for solving fractional knapsacks. */
public class BruteForceFractional extends AbstractKnapsackSolver {
  private final Map<String, Double> memo = new HashMap<>(); // Memoization for sub problems

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

  /** Solves the knapsack problem. Subclasses must implement this method. */
  @Override
  public void solve() {
    Map<Item, Double> bestSelection = new HashMap<>();
    double bestValue =
        bruteForceFractional(
            knapsack.getItems().size(), knapsack.getKnapsackCapacity(), bestSelection);
    selectedItems.putAll(bestSelection);
    setSelectedTotalValue(bestValue);
  }

  /**
   * Recursive brute force function that finds the best value by trying every combination breaking
   * the final item into a fractional value to fully use capacity.
   *
   * @param n The number of items being considered.
   * @param capacity The remaining capacity of the knapsack.
   * @param bestSelection A map to store the best selection of items.
   * @return The maximum value.
   */
  private double bruteForceFractional(int n, double capacity, Map<Item, Double> bestSelection) {
    // Ensure there are items and capacity
    if (n == 0 || capacity == 0) {
      return 0;
    }
    String key = n + "-" + capacity;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    Item currentItem = knapsack.getItems().get(n - 1); // Save the current item

    // Case 1: Skip the item
    Map<Item, Double> excludeSelection = new HashMap<>(bestSelection);
    double exclude = bruteForceFractional(n - 1, capacity, excludeSelection);

    // Case 2: Take full item if it fits
    double include = 0;
    Map<Item, Double> includeSelection = new HashMap<>(bestSelection);
    if (currentItem.getWeight() <= capacity) {
      include =
          currentItem.getValue()
              + bruteForceFractional(n - 1, capacity - currentItem.getWeight(), includeSelection);
      includeSelection.put(currentItem, 1.0);
    }

    // Case 3: Take fraction of item if it's too heavy
    double maxFraction =
        Math.floor(Math.min(1.0, (double) capacity / currentItem.getWeight()) * 10) / 10.0;
    double maxFractionalValue = 0;
    Map<Item, Double> maxFractionSelection = new HashMap<>(bestSelection);
    for (double fraction = 0.1; fraction <= maxFraction; fraction += 0.1) {
      Map<Item, Double> fractionalSelection = new HashMap<>(bestSelection);
      fractionalSelection.put(currentItem, fraction);
      double fractionalValue =
          fraction * currentItem.getValue()
              + bruteForceFractional(
                  n - 1, capacity - currentItem.getWeight() * fraction, fractionalSelection);
      if (fractionalValue > maxFractionalValue) {
        maxFractionSelection = fractionalSelection;
        maxFractionalValue = fractionalValue;
      }
    }

    // Select the best of the three choices
    double bestValue;
    if (include >= exclude && include >= maxFractionalValue) {
      bestSelection.clear();
      bestSelection.putAll(includeSelection);
      bestValue = include;
    } else if (maxFractionalValue >= exclude) {
      bestSelection.clear();
      bestSelection.putAll(maxFractionSelection);
      bestValue = maxFractionalValue;
    } else {
      bestSelection.clear();
      bestSelection.putAll(excludeSelection);
      bestValue = exclude;
    }

    // Store in memo
    memo.put(key, bestValue);
    return bestValue;
  }

  /**
   * Helper method to get the best fractional value of the current items.
   *
   * @param n The number of items being considered.
   * @param capacity The remaining capacity of the knapsack.
   * @param bestSelection A map to store the best selection of items.
   * @return The maximum value.
   */
  private double fractionalHelper(int n, double capacity, Map<Item, Double> bestSelection) {
    List<Item> sortedItems = knapsack.getItems().subList(0, n);
    sortedItems.sort(
        (a, b) ->
            Double.compare(
                (double) b.getValue() / (double) b.getWeight(),
                (double) a.getValue() / (double) a.getWeight()));
    double totalValue = 0.0;

    for (Item item : sortedItems) {
      if (capacity == 0) {
        break; // Stop when knapsack is full
      }
      double weightToTake = Math.min(item.getWeight(), capacity);
      double fraction = weightToTake / item.getWeight();
      bestSelection.put(item, fraction);
      totalValue += fraction * item.getValue();
      capacity -= weightToTake;
    }
    return totalValue;
  }
}
