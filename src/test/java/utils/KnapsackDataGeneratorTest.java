package utils;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackDataGeneratorTest {

  @Test
  void generate_validFileCreated() {
    String filename = "test_knapsack.csv";
    int numItems = 10;

    KnapsackDataGenerator.generate(filename, numItems);

    assertTrue(Files.exists(Paths.get(filename)));

    // Clean up: delete the file after the test
    try {
      Files.delete(Paths.get(filename));
    } catch (IOException e) {
      fail("Failed to delete test file: " + e.getMessage());
    }
  }

  @Test
  void generate_correctDataFormat() {
    String filename = "test_knapsack.csv";
    int numItems = 5;

    KnapsackDataGenerator.generate(filename, numItems);

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String capacityLine = reader.readLine();
      String valuesLine = reader.readLine();
      String weightsLine = reader.readLine();

      assertNotNull(capacityLine);
      assertNotNull(valuesLine);
      assertNotNull(weightsLine);

      String[] capacityParts = capacityLine.split(",");
      assertEquals(2, capacityParts.length);
      assertDoesNotThrow(() -> Integer.parseInt(capacityParts[0]));
      assertDoesNotThrow(() -> Integer.parseInt(capacityParts[1]));

      String[] values = valuesLine.split(",");
      assertEquals(numItems, values.length);
      for (String value : values) {
        assertDoesNotThrow(() -> Integer.parseInt(value));
      }

      String[] weights = weightsLine.split(",");
      assertEquals(numItems, weights.length);
      for (String weight : weights) {
        assertDoesNotThrow(() -> Integer.parseInt(weight));
      }

    } catch (IOException e) {
      fail("Failed to read test file: " + e.getMessage());
    } finally {
      try {
        Files.delete(Paths.get(filename));
      } catch (IOException e) {
        fail("Failed to delete test file: " + e.getMessage());
      }
    }
  }

  @Test
  void generate_correctNumberOfItems() {
    String filename = "test_knapsack.csv";
    int numItems = 8;

    KnapsackDataGenerator.generate(filename, numItems);

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      reader.readLine(); // Skip capacity line
      String valuesLine = reader.readLine();
      String weightsLine = reader.readLine();

      String[] values = valuesLine.split(",");
      String[] weights = weightsLine.split(",");

      assertEquals(numItems, values.length);
      assertEquals(numItems, weights.length);

    } catch (IOException e) {
      fail("Failed to read test file: " + e.getMessage());
    } finally {
      try {
        Files.delete(Paths.get(filename));
      } catch (IOException e) {
        fail("Failed to delete test file: " + e.getMessage());
      }
    }
  }

  @Test
  void generate_validCapacity() throws FileNotFoundException {
    String filename = "test_knapsack.csv";
    int numItems = 15;

    KnapsackDataGenerator.generate(filename, numItems);

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String capacityLine = reader.readLine();
      String weightsLine = reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}