package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
  Item item1;
  Item item2;
  Item item3;

  @BeforeEach
  void setUp() {
    item1 = new Item(100, 10);
    item2 = new Item(100, 100);
    item3 = new Item(10, 100);

  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getWeight() {
    assertEquals(100, item1.getWeight());
    assertEquals(100, item2.getWeight());
    assertEquals(10, item3.getWeight());
  }

  @Test
  void getValue() {
    assertEquals(10, item1.getValue());
    assertEquals(100, item2.getValue());
    assertEquals(100, item3.getValue());
  }

  @Test
  void getRatio() {
    assertEquals(0.1, item1.getRatio(), 0.001);
    assertEquals(1.0, item2.getRatio(), 0.001);
    assertEquals(10.0, item3.getRatio(), 0.001);
  }
}