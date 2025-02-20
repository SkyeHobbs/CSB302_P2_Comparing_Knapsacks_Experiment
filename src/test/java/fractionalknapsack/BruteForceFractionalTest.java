package fractionalknapsack;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import fractionalknapsack.BruteForceFractional;
import knapsack01.BruteForce01;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceFractionalTest {

  @Test
  void solveEmptyItems() {
    List<Item> itemList = new ArrayList<>();
    Knapsack knapsack = new Knapsack(1, 25, itemList);
    AbstractKnapsackSolver knapsackSolver = new BruteForceFractional(knapsack);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getsSelectedTotalValue());
    assertTrue(knapsackSolver.getSelectedItems().isEmpty());
  }

  @Test
  void solveAllItemsToHeavy() {
    List<Item> items = new ArrayList<>();
    items.add(new Item(10, 10));
    items.add(new Item(10, 5));
    items.add(new Item(10, 2));
    Knapsack knapsack = new Knapsack(1, 5, items);
    AbstractKnapsackSolver knapsackSolver = new BruteForceFractional(knapsack);
    knapsackSolver.solve();
    assertEquals(5.0, knapsackSolver.getsSelectedTotalValue());
    assertFalse(knapsackSolver.getSelectedItems().isEmpty());
  }

  @Test
  void solve() {
    List<Item> items = new ArrayList<>();
    items.add(new Item(14, 24));
    items.add(new Item(22, 18));
    items.add(new Item(7, 14));
    items.add(new Item(13, 22));
    items.add(new Item(16, 122));
    Knapsack knapsack = new Knapsack(1, 30, items);
    AbstractKnapsackSolver knapsackSolver = new BruteForceFractional(knapsack);
    knapsackSolver.solve();
    Map<Item, Double> selectedItems = knapsackSolver.getSelectedItems();

    assertEquals(148, knapsackSolver.getsSelectedTotalValue());
    assertEquals(5, selectedItems.size());
  }
}
