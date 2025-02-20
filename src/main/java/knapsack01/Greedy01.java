package knapsack01;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import utils.Item;

import java.util.*;

/**
 * Performs the Greedy algorithm on an 01 knapsack problem
 */
public class Greedy01 extends AbstractKnapsackSolver {

  /**
   * Number of the knapsack instance
   */
  private int knapsackNum;

  /**
   * List of items
   */
  private List<Item> items;

  /**
   * Total weight capacity of knapsack
   */
  private int capacity;

  /**
   * Creates a Greedy01 object
   *
   * @param knapsack the knapsack we want to perform the algorithm on
   */
  public Greedy01(Knapsack knapsack) {
    super(knapsack);
    this.items = knapsack.getItems();
    this.capacity = knapsack.getKnapsackCapacity();
    this.knapsackNum = knapsack.getKnapsackNumber();
    selectedItems = new HashMap<>();
    selectedTotalValue = 0.0;
  }

  /**
   * Sorts a list of Items into descending order
   *
   * @param items List of Item objects
   * @return sorted list of Items
   */
  public List<Item> sort(List<Item> items) {

    items.sort((item1, item2) -> Integer.compare(item2.getValue(), item1.getValue()));

    return items;
  }

  /**
   * Solves the knapsack problem.
   * Subclasses must implement this method.
   */
  @Override
  public void solve() {

    List<Item> sortedItems = sort(items);

    selectedTotalValue = 0;
    double selectedWeight = 0;

    if (capacity == 0 || sortedItems.isEmpty()) {
      return;
    }

    for (Item item : sortedItems) {
      if (selectedWeight + item.getWeight() <= capacity) {
          selectedItems.put(item, (double) item.getWeight());
          selectedWeight += item.getWeight();
          selectedTotalValue += item.getValue();

        }
      }
    }
  }
