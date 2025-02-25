package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV Writer creates csv files for results and for inputs.
 */
public class CSVWriter {
  /**
   * The name of the CSV file.
   */
  private String path;

  /**
   * Constructor creates a writer.
   *
   * @param path name of the CSV file
   */
  public CSVWriter(String path) {
    setPath(path);
  }

  /**
   * Writes results of Comparing Knapsack Experiments.
   *
   * @param items List of Item's with value's and weight's
   * @param totalValue total value of knapsack item's
   * @param knapsackNumber the number of knapsack
   * @param knapsackCapacity total weight capacity of the knapsack
   */
  public void writeResults(List<Item> items, double totalValue,
                           int knapsackNumber, int knapsackCapacity) {
    File file = new File(path);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      // First line: number and capacity
      writer.write(knapsackNumber + "," + knapsackCapacity);
      writer.newLine();

      // Second line: All items' values
      StringBuilder values = new StringBuilder();
      for (int i = 0; i < items.size(); i++) {
        values.append(items.get(i).getValue());
        if (i < items.size() - 1) {
          values.append(",");
        }
      }
      writer.write(values.toString());
      writer.newLine();

      // Third line: All items' weights
      StringBuilder weights = new StringBuilder();
      for (int i = 0; i < items.size(); i++) {
        weights.append(items.get(i).getWeight());
        if (i < items.size() - 1) {
          weights.append(",");
        }
      }
      writer.write(weights.toString());
      writer.newLine();

      // Fourth line: total value
      writer.write(String.valueOf(totalValue));
      writer.newLine();

    } catch (IOException e) {
      throw new RuntimeException("Failed to create file" + e.getMessage(), e);
    }
  }

  /**
   * Creates an input CSV file for the Comparing Knapsacks Experiment.
   *
   * @param items List of Items
   * @param knapsackNumber number of knapsack
   * @param knapsackCapacity total weight capacity of the knapsack
   */
  public void writeInputFile(List<Item> items, int knapsackNumber, int knapsackCapacity) {
    File file = new File(path);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      // First line: number and capacity
      writer.write(knapsackNumber + "," + knapsackCapacity);
      writer.newLine();

      // Second line: All items' values
      StringBuilder values = new StringBuilder();
      for (int i = 0; i < items.size(); i++) {
        values.append(items.get(i).getValue());
        if (i < items.size() - 1) {
          values.append(",");
        }
      }
      writer.write(values.toString());
      writer.newLine();

      // Third line: All items' weights
      StringBuilder weights = new StringBuilder();
      for (int i = 0; i < items.size(); i++) {
        weights.append(items.get(i).getWeight());
        if (i < items.size() - 1) {
          weights.append(",");
        }
      }
      writer.write(weights.toString());
      writer.newLine();

    } catch (IOException e) {
      throw new RuntimeException("Failed to create file" + e.getMessage(), e);
    }
  }

  /**
   * Sets the path for the Writer.
   *
   * @param path file name
   */
  private void setPath(String path) {
    this.path = path;
  }

  /**
   * Retrieves the path of the Writer.
   *
   * @return file name
   */
  public String getPath() {
    return path;
  }

}
