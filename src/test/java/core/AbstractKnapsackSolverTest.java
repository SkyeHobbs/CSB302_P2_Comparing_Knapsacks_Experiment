package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractKnapsackSolverTest {
  private AbstractKnapsackSolver knapsackSolver;
  private List<Item> items;

  @BeforeEach
  void setUp() {
    items = Arrays.asList(
      new Item(10, 5), new Item(20, 15),new Item(30, 9));
    knapsackSolver = new MockKnapsackSolver(items, 30, 1);
  }

  @Test
  public void testSolve() {
    knapsackSolver.solve();
    List<Item> selectedItems = knapsackSolver.getSelectedItems();
    double totalValue = knapsackSolver.getTotalValue();

    assertEquals(20.0, totalValue, "Total value should be 20.0");
    assertEquals(2, selectedItems.size(), "Should select 2 items");
  }
  @Test
  public void testGetResults() {
    knapsackSolver.solve();
    String results = knapsackSolver.getResults();

    assertTrue(results.contains("Knapsack Number: 1"));
    assertTrue(results.contains("Knapsack Capacity: 30"));
    assertTrue(results.contains("Total Value: 20.0"));
    assertTrue(results.contains("(10.0, 5.0)"));
    assertTrue(results.contains("(20.0, 15.0)"));
  }
}