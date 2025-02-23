package fractionalknapsack;

import core.Knapsack;
import utils.CSVReader;
import utils.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

/**
 * Runner for Fractional Knapsack algorithms.
 * Executes Brute Force, Greedy algorithms, and times them.
 * Generates execution time and profit comparison charts.
 */
public class Runner {
    // List of input file paths
    private static final String[] INPUT_FILES = {
            "data/inputs.csv",
            "data/inputs2.csv",
            "data/inputs3.csv"
    };
    public static void runFractionalKnapsack() {
        DefaultCategoryDataset timeDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset profitDataset = new DefaultCategoryDataset();

        CSVReader csvReader = new CSVReader();

        for (String filePath : INPUT_FILES) {
            Knapsack knapsack = csvReader.readCSV(filePath);

            int capacity = knapsack.getKnapsackCapacity();
            double knapsackValue = knapsack.getKnapsackValue();

            Timer timer = new Timer();
            BruteForceFractional bruteForce = new BruteForceFractional(knapsack);
            GreedyFractional greedy = new GreedyFractional(knapsack);

            // Run Brute Force
            timer.start();
            bruteForce.solve();
            timer.stop();
            long bruteForceTime = timer.getTime();

            // Run Greedy Algorithm
            timer.start();
            greedy.solve();
            timer.stop();
            long greedyTime = timer.getTime();

            // Store results for charting
            String label = filePath + " (Capacity: " + capacity + ")";
            timeDataset.addValue(bruteForceTime, "Brute Force", label);
            timeDataset.addValue(greedyTime, "Greedy", label);

            profitDataset.addValue(knapsackValue, "Brute Force", label);
            profitDataset.addValue(knapsackValue, "Greedy", label);
        }

        // Generate Charts
        showChart("Fractional Knapsack - Execution Time", "Data File & Items", "Time (ms)", timeDataset);
        showChart("Fractional Knapsack - Profit Comparison", "Data File & Items", "Profit", profitDataset);
    }

    /**
     * Displays a JFreeChart with the given dataset.
     */
    private static void showChart(String title, String xLabel, String yLabel, DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(title, xLabel, yLabel, dataset);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}