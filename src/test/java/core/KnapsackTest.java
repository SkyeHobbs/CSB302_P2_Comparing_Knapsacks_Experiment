package core;

import org.junit.jupiter.api.Test;
import utils.Item;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnapsackTest {

  @Test
  void testKnapsackInitialization() {
    Item item1 = new Item(13, 26);
    Item item2 = new Item(5, 15);
    List<Item> itemList = Arrays.asList(item1, item2);

    Knapsack knapsack = new Knapsack(1, 10, itemList);

    assertEquals(1, knapsack.getKnapsackNumber(), "Knapsack number should be 1");
    assertEquals(10, knapsack.getKnapsackCapacity(), "Knapsack capacity should be 10");
    assertEquals(itemList, knapsack.getItems(), "Items list should match the provided list");
  }
}