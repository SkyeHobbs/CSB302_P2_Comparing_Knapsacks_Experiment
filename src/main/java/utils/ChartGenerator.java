package utils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/** ChartGenerator handles the creation of separate charts for different metrics. */
public class ChartGenerator {

  /**
   * Generates a scatter plot (dot plot) with larger dots for execution time.
   *
   * @param title Chart title
   * @param xLabel X-axis label
   * @param yLabel Y-axis label
   * @param dataset Dataset (XYSeriesCollection)
   * @param fileName Output filename
   */
  public static void exportScatterChart(
      String title, String xLabel, String yLabel, XYSeries dataset, String fileName) {
    XYSeriesCollection collection = new XYSeriesCollection();
    collection.addSeries(dataset);

    JFreeChart chart = ChartFactory.createScatterPlot(title, xLabel, yLabel, collection);
    XYPlot plot = chart.getXYPlot();

    // Use XYLineAndShapeRenderer for larger dots
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
    renderer.setSeriesShape(0, new Ellipse2D.Double(-5, -5, 10, 10)); // Larger dot size
    renderer.setSeriesPaint(0, Color.BLUE);
    plot.setRenderer(renderer);

    saveChart(chart, fileName);
  }

  /**
   * Generates a scatter plot for multiple series with increased dot size.
   *
   * @param title Chart title
   * @param xLabel X-axis label
   * @param yLabel Y-axis label
   * @param dataset XYSeriesCollection
   * @param fileName Output filename
   */
  public static void exportScatterChart(
      String title, String xLabel, String yLabel, XYSeriesCollection dataset, String fileName) {
    JFreeChart chart = ChartFactory.createScatterPlot(title, xLabel, yLabel, dataset);
    XYPlot plot = chart.getXYPlot();

    // Use XYLineAndShapeRenderer for larger dots
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
    for (int i = 0; i < dataset.getSeriesCount(); i++) {
      renderer.setSeriesShape(i, new Ellipse2D.Double(-5, -5, 10, 10)); // Larger dots
      renderer.setSeriesPaint(i, i == 0 ? Color.BLUE : Color.RED);
    }
    plot.setRenderer(renderer);

    saveChart(chart, fileName);
  }

  /**
   * Generates a bar chart for profit values.
   *
   * @param title Chart title
   * @param xLabel X-axis label
   * @param yLabel Y-axis label
   * @param dataset Dataset (DefaultCategoryDataset)
   * @param fileName Output filename
   */
  public static void exportBarChart(
      String title, String xLabel, String yLabel, DefaultCategoryDataset dataset, String fileName) {
    JFreeChart chart = ChartFactory.createBarChart(title, xLabel, yLabel, dataset);
    saveChart(chart, fileName);
  }

  /** Saves the generated chart to a file. */
  private static void saveChart(JFreeChart chart, String fileName) {
    File chartFile = new File("experiment-results/charts/" + fileName);
    try {
      ChartUtilities.saveChartAsPNG(chartFile, chart, 800, 600);
      System.out.println("Chart saved to: " + chartFile.getAbsolutePath());
    } catch (IOException e) {
      System.err.println("Error saving chart: " + e.getMessage());
    }
  }
}
