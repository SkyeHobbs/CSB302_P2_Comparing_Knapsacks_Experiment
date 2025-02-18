package knapsack01;
import core.AbstractKnapsackSolver;
import core.Knapsack;
import org.junit.jupiter.api.Test;
import utils.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgramming01Test {

  @Test
  void solveEmptyItems() {
    List<Item> itemList = new ArrayList<>();
    Knapsack knapsack = new Knapsack(1, 25, itemList);
    AbstractKnapsackSolver knapsackSolver = new DynamicProgramming01(knapsack);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getsSelectedTotalValue());
    assertTrue(knapsackSolver.getSelectedItems().isEmpty());
  }

  @Test
  void solveZeroCapacity() {
    List<Item> itemList = new ArrayList<>();
    itemList.add(new Item(14, 24));
    itemList.add(new Item(22, 18));
    itemList.add(new Item(7, 14));
    itemList.add(new Item(13, 22));
    itemList.add(new Item(16, 122));
    Knapsack knapsack = new Knapsack(1, 0, itemList);
    AbstractKnapsackSolver knapsackSolver = new DynamicProgramming01(knapsack);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getsSelectedTotalValue());
    assertTrue(knapsackSolver.getSelectedItems().isEmpty());
  }

  @Test
  void solve() {
    List<Item> itemList = new ArrayList<>();
    itemList.add(new Item(14, 24));
    itemList.add(new Item(22, 18));
    itemList.add(new Item(7, 14));
    itemList.add(new Item(13, 22));
    itemList.add(new Item(16, 22));
    Knapsack knapsack = new Knapsack(1, 25, itemList);
    AbstractKnapsackSolver knapsackSolver = new DynamicProgramming01(knapsack);
    knapsackSolver.solve();
    assertEquals(38, knapsackSolver.getsSelectedTotalValue());
    Map<Item, Double> selected = knapsackSolver.getSelectedItems();
    assertTrue(selected.containsKey(itemList.get(2)));
    assertTrue(selected.containsKey(itemList.get(0)));
  }
}