package knapsack01;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import fractionalknapsack.BruteForceFractional;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BruteForce01Test {

  @Test
  void solveEmptyItems() {
    List<Item> itemList = new ArrayList<>();
    Knapsack knapsack = new Knapsack(1, 25, itemList);
    AbstractKnapsackSolver knapsackSolver = new BruteForce01(knapsack);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getSelectedTotalValue());
    assertTrue(knapsackSolver.getSelectedItems().isEmpty());
  }

  @Test
  void solveAllItemsToHeavy() {
    List<Item> items = new ArrayList<>();
    items.add(new Item(10, 24));
    items.add(new Item(10, 18));
    items.add(new Item(10, 14));
    Knapsack knapsack = new Knapsack(1, 5, items);
    AbstractKnapsackSolver knapsackSolver = new BruteForce01(knapsack);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getSelectedTotalValue());
    assertTrue(knapsackSolver.getSelectedItems().isEmpty());
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
    AbstractKnapsackSolver knapsackSolver = new BruteForce01(knapsack);
    knapsackSolver.solve();

    assertEquals(146, knapsackSolver.getSelectedTotalValue());
    assertEquals(2, knapsackSolver.getSelectedItems().size());
  }
}
