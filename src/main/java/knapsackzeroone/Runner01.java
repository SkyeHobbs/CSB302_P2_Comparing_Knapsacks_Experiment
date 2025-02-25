package knapsackzeroone;

import core.Knapsack;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.CSVReader;
import utils.ChartGenerator;
import utils.Timer;

/**
 * Runner for 0/1 Knapsack algorithms. Executes Brute Force, Dynamic Programming, and Greedy
 * algorithms. Generates execution time and profit comparison charts.
 */
public class Runner01 {
  private static final String[] INPUT_FILES = {
    "data/inputs.csv",
    "data/inputs2.csv",
    "data/inputs3.csv",
    "data/inputs_20.csv",
    "data/inputs_25.csv",
    "data/inputs_30.csv",
  };

  /**
   * Runs all 01 knapsack algorithms.
   */
  @SuppressWarnings("checkstyle:VariableDeclarationUsageDistance")
  public static void runKnapsack01() {
    System.out.println("======================================================================");
    System.out.println("        Running 0/1 Knapsack");
    System.out.println("======================================================================");

    // Separate datasets for each algorithm
    XYSeries bruteForceTimeSeries = new XYSeries("Brute Force Time (ms)");
    XYSeries dynamicProgrammingTimeSeries = new XYSeries("Dynamic Programming Time (ms)");
    XYSeries greedyTimeSeries = new XYSeries("Greedy Time (ms)");
    XYSeriesCollection overallTimeDataset = new XYSeriesCollection();

    DefaultCategoryDataset bruteForceProfitDataset = new DefaultCategoryDataset();
    DefaultCategoryDataset dynamicProgrammingProfitDataset = new DefaultCategoryDataset();
    DefaultCategoryDataset greedyProfitDataset = new DefaultCategoryDataset();
    DefaultCategoryDataset overallProfitDataset = new DefaultCategoryDataset();

    CSVReader csvReader = new CSVReader();

    for (String filePath : INPUT_FILES) {
      System.out.println("Reading file: " + filePath);
      Knapsack knapsack = csvReader.readCSV(filePath);

      int numOfItems = knapsack.getItems().size(); // X-Axis should be the number of items
      int capacity = knapsack.getKnapsackCapacity();

      // Print Knapsack Details BEFORE solving
      knapsack.printKnapsack();
      System.out.println("-----------------------------------------------------");

      Timer timer = new Timer();
      BruteForce01 bruteForce = new BruteForce01(knapsack);
      DynamicProgramming01 dynamicProgramming = new DynamicProgramming01(knapsack);
      Greedy01 greedy = new Greedy01(knapsack);

      // Run Brute Force
      System.out.println("Running 0/1 Knapsack Brute Force...");
      timer.start();
      bruteForce.solve();
      timer.stop();
      long bruteForceTime = timer.getTime();
      double bruteForceProfit = bruteForce.getSelectedTotalValue();

      // Run Dynamic Programming
      System.out.println("Running 0/1 Knapsack Dynamic Programming...");
      timer.start();
      dynamicProgramming.solve();
      timer.stop();
      long dynamicProgrammingTime = timer.getTime();
      double dynamicProgrammingProfit = dynamicProgramming.getSelectedTotalValue();

      // Run Greedy Algorithm
      System.out.println("Running 0/1 Knapsack Greedy Algorithm...");
      timer.start();
      greedy.solve();
      timer.stop();
      long greedyTime = timer.getTime();
      double greedyProfit = greedy.getSelectedTotalValue();

      // Store Execution Time as Scatter Points (Dots)
      bruteForceTimeSeries.add(numOfItems, bruteForceTime);
      dynamicProgrammingTimeSeries.add(numOfItems, dynamicProgrammingTime);
      greedyTimeSeries.add(numOfItems, greedyTime);

      // Store Profit Values (As Bars)
      bruteForceProfitDataset.addValue(bruteForceProfit, "Brute Force", String.valueOf(numOfItems));
      dynamicProgrammingProfitDataset.addValue(
          dynamicProgrammingProfit, "Dynamic Programming", String.valueOf(numOfItems));
      greedyProfitDataset.addValue(greedyProfit, "Greedy", String.valueOf(numOfItems));
      overallProfitDataset.addValue(bruteForceProfit, "Brute Force", String.valueOf(numOfItems));
      overallProfitDataset.addValue(
          dynamicProgrammingProfit, "Dynamic Programming", String.valueOf(numOfItems));
      overallProfitDataset.addValue(greedyProfit, "Greedy", String.valueOf(numOfItems));

      // Print final results
      System.out.println("Best Value for Brute Force: " + bruteForceProfit);
      System.out.println("Best Value for Dynamic Programming: " + dynamicProgrammingProfit);
      System.out.println("Best Value for Greedy Algorithm: " + greedyProfit);
      System.out.println("=======================================================\n");
    }

    // Generate individual algorithm runtime charts as Scatter Plots
    ChartGenerator.exportScatterChart(
        "0/1 Knapsack Execution Time - Brute Force",
        "Number of Items",
        "Time (ms)",
        bruteForceTimeSeries,
        "knapsack01_brute_force_time.png");
    ChartGenerator.exportScatterChart(
        "0/1 Knapsack Execution Time - Dynamic Programming",
        "Number of Items",
        "Time (ms)",
        dynamicProgrammingTimeSeries,
        "knapsack01_dynamic_programming_time.png");
    ChartGenerator.exportScatterChart(
        "0/1 Knapsack Execution Time - Greedy Algorithm",
        "Number of Items",
        "Time (ms)",
        greedyTimeSeries,
        "knapsack01_greedy_time.png");

    overallTimeDataset.addSeries(bruteForceTimeSeries);
    overallTimeDataset.addSeries(dynamicProgrammingTimeSeries);
    overallTimeDataset.addSeries(greedyTimeSeries);
    ChartGenerator.exportScatterChart(
        "0/1 Knapsack Execution Time - All Algorithms",
        "Number of Items",
        "Time (ms)",
        overallTimeDataset,
        "knapsack01_all_algorithms_time.png");

    // Generate Profit Charts as Bar Charts
    ChartGenerator.exportBarChart(
        "0/1 Knapsack Profit - Brute Force",
        "Number of Items",
        "Profit",
        bruteForceProfitDataset,
        "knapsack01_brute_force_profit.png");
    ChartGenerator.exportBarChart(
        "0/1 Knapsack Profit - Dynamic Programming",
        "Number of Items",
        "Profit",
        dynamicProgrammingProfitDataset,
        "knapsack01_dynamic_programming_profit.png");
    ChartGenerator.exportBarChart(
        "0/1 Knapsack Profit - Greedy Algorithm",
        "Number of Items",
        "Profit",
        greedyProfitDataset,
        "knapsack01_greedy_profit.png");
    ChartGenerator.exportBarChart(
        "0/1 Knapsack Profit - All Algorithms",
        "Number of Items",
        "Profit",
        overallProfitDataset,
        "knapsack01_all_algorithms_profit.png");
  }
}
