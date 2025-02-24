package fractionalknapsack;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import utils.Item;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GreedyFractional extends AbstractKnapsackSolver {
  List<Item> items = knapsack.getItems();
  int capacity = knapsack.getKnapsackCapacity();

  /**
   * Constructs an AbstractKnapsackSolver with the given Knapsack.
   *
   * @param knapsack The input knapsack instance.
   */
  public GreedyFractional(Knapsack knapsack) {
    super(knapsack);
    selectedTotalValue = 0;
  }

  /**
   * Solves the knapsack problem.
   * Subclasses must implement this method.
   */
  @Override
  public void solve() {
    selectedTotalValue = 0;
    selectedItems = new HashMap<>();
    if (capacity == 0 || items.isEmpty()) {
      return;
    }

    // Sort the knapsack items by ratio in descending order
    items.sort(Comparator.comparingDouble(Item::getRatio).reversed());
    // Fill the selectedItems in the sorted order

    double selectedWeight = 0;

    for (Item item : items) {
      if (selectedWeight < capacity) {
        // If the capacity left is no less than the item's weight, add all of the item to
        // selection
        if (item.getWeight() <= capacity - selectedWeight) {
          selectedItems.put(item, (double) item.getWeight());
          selectedWeight += item.getWeight();
          selectedTotalValue += item.getValue();
        } else {
          // If the capacity left is smaller than the item's weight, add the item till fill the
          // capacity
          double fraction = Math.floor((capacity - selectedWeight) / item.getWeight() * 10) / 10.0;
          fraction = Math.round(fraction * 10) / 10.0; // Set float limitation to 0.1
          selectedItems.put(item, fraction * item.getWeight());
          selectedTotalValue += item.getRatio() * (fraction * item.getWeight());
          selectedWeight += fraction * item.getWeight();
          break;
        }
      }
    }
  }
}
