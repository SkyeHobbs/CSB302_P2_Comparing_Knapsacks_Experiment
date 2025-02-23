package utils;

import core.AbstractKnapsackSolver;
import core.Knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapsackDataGenerator {

  public static void generate(String filename, int numItems) {
    Random random = new Random();
    List<Item> items = new ArrayList<>();

    int maxWeight = 0;

    // Generate random items
    for (int i = 0; i < numItems; i++) {
      int weight = random.nextInt(75) + 1;
      int value = random.nextInt(100) + 1;
      items.add(new Item(weight, value));
      maxWeight = Math.max(maxWeight, weight);
    }

    // Calc knapsack capacity
    int capacity = (maxWeight * 2) + (random.nextInt(10) + 1);

    // Generate random knapsack number
    int knapsackNumber = random.nextInt(100) + 1;

    //Write CSV file
    try {
      CSVWriter writer = new CSVWriter(filename);
      writer.writeInputFile(items, knapsackNumber, capacity);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }
}
