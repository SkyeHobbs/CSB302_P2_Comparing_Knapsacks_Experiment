package knapsack01;
import core.AbstractKnapsackSolver;
import org.junit.jupiter.api.Test;
import utils.Item;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DynamicProgramming01Test {

  @Test
  void solveEmptyItems() {
    List<Item> itemList = new ArrayList<>();
    AbstractKnapsackSolver knapsackSolver = new DynamicProgramming01(itemList, 25, 1);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getTotalValue());
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
    AbstractKnapsackSolver knapsackSolver = new DynamicProgramming01(itemList, 0, 1);
    knapsackSolver.solve();
    assertEquals(0, knapsackSolver.getTotalValue());
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
    AbstractKnapsackSolver knapsackSolver = new DynamicProgramming01(itemList, 25, 1);
    knapsackSolver.solve();
    assertEquals(38, knapsackSolver.getTotalValue());
    List<Item> selected = knapsackSolver.getSelectedItems();
    assertEquals(itemList.get(2), selected.getFirst());
    assertEquals(itemList.get(0), selected.get(1));
  }
}