package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AbstractKnapsackSolverTest {
  private Knapsack knapsack;
  private AbstractKnapsackSolver solver;

  @BeforeEach
  void setUp() {
    Item item1 = new Item(13, 26);
    Item item2 = new Item(5, 15);
    List<Item> itemList = Arrays.asList(item1, item2);

    knapsack = new Knapsack(1, 10, itemList);

    solver = new DummyKnapsackSolver(knapsack);

    Map<Item, Double> selectedItems = new HashMap<>();
    selectedItems.put(item1, 5.0);
    selectedItems.put(item2, 3.0);
    solver.selectedItems = selectedItems;
    solver.setSelectedTotalValue(41);
  }

  @Test
  void testKnapsackInitialization() {
    assertEquals(1, knapsack.getKnapsackNumber(), "Knapsack number should be 1");
    assertEquals(10, knapsack.getKnapsackCapacity(), "Knapsack capacity should be 10");
    assertEquals(2, knapsack.getItems().size(), "Knapsack should contain 2 items");
  }

  @Test
  void testGetSelectedItems() {
    assertNotNull(solver.getSelectedItems(), "Selected items map should not be null");
    assertEquals(2, solver.getSelectedItems().size(), "Should have selected two items");
  }

  @Test
  void testGetSelectedTotalValue() {
    assertEquals(41, solver.getSelectedTotalValue(), "Total selected value should be 41");
  }

  @Test
  void testPrintResult() {
    assertDoesNotThrow(() -> solver.printResult(), "printResult() should execute without exceptions");
  }

  /**
   * Dummy subclass to test AbstractKnapsackSolver
   */
  static class DummyKnapsackSolver extends AbstractKnapsackSolver {
    public DummyKnapsackSolver(Knapsack knapsack) {
      super(knapsack);
    }

    @Override
    public void solve() {
      // Dummy implementation for testing
    }
  }
}