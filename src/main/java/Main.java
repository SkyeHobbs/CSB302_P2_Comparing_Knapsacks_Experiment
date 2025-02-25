import fractionalknapsack.RunnerFractional;
import knapsackzeroone.Runner01;

/** Main class that runs all knapsack algorithms. */
public class Main {
  /**
   * Main method that runs algorithms from both knapsack packages.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    RunnerFractional runnerFractional = new RunnerFractional();
    runnerFractional.runFractionalKnapsack();

    Runner01 runner01 = new Runner01();
    runner01.runKnapsack01();
  }
}
