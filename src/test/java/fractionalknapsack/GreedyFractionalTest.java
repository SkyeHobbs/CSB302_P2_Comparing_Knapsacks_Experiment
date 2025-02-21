package fractionalknapsack;

import core.Knapsack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GreedyFractionalTest {
  private Knapsack knapsack;
  private GreedyFractional solver;

  @BeforeEach
  void setUp() {
    List<Item> items = Arrays.asList(
      new Item(10, 60),  // Ratio = 6.0
      new Item(20, 100), // Ratio = 5.0
      new Item(30, 120)  // Ratio = 4.0
    );

    knapsack = new Knapsack(1, 25, items);
    solver = new GreedyFractional(knapsack);
  }

  @Test
  void testSolveWithCapacity25() {
    solver.solve();

    assertEquals(135, solver.getSelectedTotalValue(), 0.01, "Total value should be 135");
    Map<Item, Double> selectedItems = solver.getSelectedItems();
    assertEquals(10.0, selectedItems.get(knapsack.getItems().get(0)), 0.01);
    assertEquals(15.0, selectedItems.get(knapsack.getItems().get(1)), 0.01);
  }
  @Test
  void testZeroCapacity() {
    knapsack = new Knapsack(2,0, knapsack.getItems());  // Zero capacity
    solver = new GreedyFractional(knapsack);
    solver.solve();

    assertEquals(0, solver.getSelectedTotalValue(), 0.01);
    assertTrue(solver.getSelectedItems().isEmpty(), "No items should be selected");
  }
}