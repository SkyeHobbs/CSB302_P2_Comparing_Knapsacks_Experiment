package fractionalknapsack;

import core.Knapsack;
import utils.CSVReader;
import utils.ChartGenerator;
import utils.Timer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Runner for Fractional Knapsack algorithms.
 * Executes Brute Force and Greedy algorithms.
 * Generates multiple execution time and profit comparison charts.
 */
public class RunnerFractional {
    private static final String[] INPUT_FILES = {
            "data/inputs.csv",
            "data/inputs2.csv",
            "data/inputs3.csv",
            "inputs_20.csv",
            "inputs_25.csv",
            "inputs_30.csv",
    };

    public static void runFractionalKnapsack() {
        System.out.println("======================================================");
        System.out.println("Running fractional knapsack");
        System.out.println("======================================================");

        // Separate datasets for each algorithm
        XYSeries bruteForceTimeSeries = new XYSeries("Brute Force Time (ms)");
        XYSeries greedyTimeSeries = new XYSeries("Greedy Time (ms)");
        XYSeriesCollection overallTimeDataset = new XYSeriesCollection();

        DefaultCategoryDataset bruteForceProfitDataset = new DefaultCategoryDataset();
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
            BruteForceFractional bruteForce = new BruteForceFractional(knapsack);
            GreedyFractional greedy = new GreedyFractional(knapsack);

            // Run Brute Force
            System.out.println("Running fractional knapsack Brute Force...");
            timer.start();
            bruteForce.solve();
            timer.stop();
            long bruteForceTime = timer.getTime();
            double bruteForceProfit = bruteForce.getSelectedTotalValue();

            // Run Greedy Algorithm
            System.out.println("Running fractional knapsack Greedy Algorithm...");
            timer.start();
            greedy.solve();
            timer.stop();
            long greedyTime = timer.getTime();
            double greedyProfit = greedy.getSelectedTotalValue();

            // Store Execution Time as Scatter Points (Dots)
            bruteForceTimeSeries.add(numOfItems, bruteForceTime);
            greedyTimeSeries.add(numOfItems, greedyTime);

            // Store Profit Values (As Bars)
            bruteForceProfitDataset.addValue(bruteForceProfit, "Brute Force", String.valueOf(numOfItems));
            greedyProfitDataset.addValue(greedyProfit, "Greedy", String.valueOf(numOfItems));
            overallProfitDataset.addValue(bruteForceProfit, "Brute Force", String.valueOf(numOfItems));
            overallProfitDataset.addValue(greedyProfit, "Greedy", String.valueOf(numOfItems));

            // Print final results
            System.out.println("Best Value for Brute Force: " + bruteForceProfit);
            System.out.println("Best Value for Greedy Algorithm: " + greedyProfit);
            System.out.println("=======================================================\n");
        }

        // Generate individual algorithm runtime charts as Scatter Plots
        ChartGenerator.exportScatterChart("Fractional Execution Time - Brute Force", "Number of Items", "Time (ms)", bruteForceTimeSeries, "fractional_brute_force_time.png");
        ChartGenerator.exportScatterChart("Fractional Execution Time - Greedy Algorithm", "Number of Items", "Time (ms)", greedyTimeSeries, "fractional_greedy_time.png");

        overallTimeDataset.addSeries(bruteForceTimeSeries);
        overallTimeDataset.addSeries(greedyTimeSeries);
        ChartGenerator.exportScatterChart("Fractional Execution Time - All Algorithms", "Number of Items", "Time (ms)", overallTimeDataset, "fractional_all_algorithms_time.png");

        // Generate Profit Charts as Bar Charts
        ChartGenerator.exportBarChart("Fractional Profit - Brute Force", "Number of Items", "Profit", bruteForceProfitDataset, "fractional_brute_force_profit.png");
        ChartGenerator.exportBarChart("Fractional Profit - Greedy Algorithm", "Number of Items", "Profit", greedyProfitDataset, "fractional_greedy_profit.png");
        ChartGenerator.exportBarChart("Fractional Profit - All Algorithms", "Number of Items", "Profit", overallProfitDataset, "fractional_all_algorithms_profit.png");
    }
}