package knapsackzeroone;

import core.Knapsack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Greedy01Test {
  private static List<Item> items;

  private static Greedy01 greedy01;

  @BeforeAll
  static void setUp() {
    Item item1 = new Item(2, 14);
    Item item2 = new Item(4, 6);
    Item item3 = new Item(5, 9);
    Item item4 = new Item(3, 11);

    items = new ArrayList<>();
    items.add(item1);
    items.add(item2);
    items.add(item3);
    items.add(item4);

    Knapsack knapsack = new Knapsack(1, 10, items);
    greedy01 = new Greedy01(knapsack);
  }

  @Test
  void sort() {
    Item item1 = new Item(2, 14);
    Item item4 = new Item(4, 6);
    Item item3 = new Item(5, 9);
    Item item2 = new Item(3, 11);

    List<Item> items1 = new ArrayList<>();
    items1.add(item1);
    items1.add(item2);
    items1.add(item3);
    items1.add(item4);

    assertEquals(items1,greedy01.sort(items));

  }

  @Test
  void solve() {
    Item item1 = new Item(2, 14);
    Item item2 = new Item(4, 6);
    Item item3 = new Item(5, 9);
    Item item4 = new Item(3, 11);

    List<Item> items2 = new ArrayList<>();
    items2.add(item1);
    items2.add(item2);
    items2.add(item3);
    items2.add(item4);

    greedy01.solve();

    Map<Item, Double> expectedSelectedItems = new HashMap<>();
    expectedSelectedItems.put(item1, (double) item1.getWeight());
    expectedSelectedItems.put(item4, (double) item4.getWeight());
    expectedSelectedItems.put(item3, (double) item3.getWeight());

    int expectedTotalValue = item1.getValue() + item4.getValue() + item3.getValue();

    assertEquals(expectedSelectedItems, greedy01.getSelectedItems());
    assertEquals(expectedTotalValue, greedy01.getSelectedTotalValue());

  }
}