package core;

import utils.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of AbstractKnapsackSolver for testing.
 */
public class MockKnapsackSolver extends AbstractKnapsackSolver{
  public MockKnapsackSolver(List<Item> items, int capacity, int knapsackNumber) {
    super(items, capacity, knapsackNumber);
  }

  @Override
  public void solve() {
    List<Item> selected = new ArrayList<>();
    double totalWeight = 0;
    double totalValue = 0;

    for (Item item : items) {
      if (totalWeight + item.getWeight() <= capacity) {
        selected.add(item);
        totalWeight += item.getWeight();
        totalValue += item.getValue();
      }
    }
    setSelectedItems(selected);
    setTotalValue(totalValue);
  }
}
