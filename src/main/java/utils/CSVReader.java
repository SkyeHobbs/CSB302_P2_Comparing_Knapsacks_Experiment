package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CSVReader class that reads an input CSV file and
 * returns a list of Items
 */
public class CSVReader {

  /**
   * The total weight capacity the knapsack can hold
   */
  private double totalKnapsackCapacity;

  /**
   * Reads an input CSV file and returns a list of Items
   *
   * @param path CSV file name
   * @return list of Item objects
   */
  public List<Item> readCSV(String path) {
    List<Item> list = new ArrayList<>();
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File(path));
    } catch (FileNotFoundException e) {
      System.err.println("File not found" + path);
      // Return an empty list if file is not found
      return list;
    }

    try {
      // This stores the knapsackCapacity values from the CSV file
      if (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] val = line.split(",");
        double totalKnapsackCapacity = Double.parseDouble(val[1]);
        // Set the total knapsack capacity, so it can be accessed via getter
        setTotalKnapsackCapacity(totalKnapsackCapacity);
      }

      if (scanner.hasNextLine()) {
        // Retrieving all the values and storing them in a list of strings
        String valuesLine = scanner.nextLine();
        String[] values = valuesLine.split(",");

        if (scanner.hasNextLine()) {
          // Retrieving all the weights and storing them in a list of strings
          String weightsLine = scanner.nextLine();
          String[] weights = weightsLine.split(",");

          // Make sure all values have a corresponding weight, otherwise throw an exception
          if (values.length != weights.length) {
            throw new IllegalArgumentException("All values must have a corresponding weight");
          }

          // Loop through the values and weighs to create Items
          for (int i = 0; i < values.length; i++) {
            double value;
            double weight;
            value = Double.parseDouble(values[i]);
            weight = Double.parseDouble(weights[i]);
            Item item = new Item(weight, value);
            // Add Item to the list
            list.add(item);
          }
        } else {
          throw new IllegalArgumentException("Weights line missing in file " + path);
        }
      } else {
        throw new IllegalArgumentException("Values line missing in file " + path);
      }

    } finally {
      // Make sure scanner is closed
      scanner.close();
    }
      return list;
    }

    private void setTotalKnapsackCapacity(double totalKnapsackCapacity) {
      this.totalKnapsackCapacity = totalKnapsackCapacity;
    }

    public double getTotalKnapsackCapacity() {
      return totalKnapsackCapacity;
    }
  }
