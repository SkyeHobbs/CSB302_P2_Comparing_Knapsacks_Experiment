package knapsack01;

import core.Knapsack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Runner01Test {

    private Knapsack testKnapsack;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));

        testKnapsack = new Knapsack(1, 50, Arrays.asList(
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        ));
    }

    @Test
    void testGreedy01Solver() {
        Greedy01 greedySolver = new Greedy01(testKnapsack);
        greedySolver.solve();

        double totalValue = greedySolver.getSelectedTotalValue();
        assertTrue(totalValue > 0, "Greedy algorithm should select items with value");
    }

    @Test
    void testBruteForce01Solver() {
        BruteForce01 bruteForceSolver = new BruteForce01(testKnapsack);
        bruteForceSolver.solve();

        double totalValue = bruteForceSolver.getSelectedTotalValue();
        assertTrue(totalValue > 0, "Brute force should select the best value items");
    }

    @Test
    void testDynamicProgramming01Solver() {
        DynamicProgramming01 dynamicSolver = new DynamicProgramming01(testKnapsack);
        dynamicSolver.solve();

        double totalValue = dynamicSolver.getSelectedTotalValue();
        assertTrue(totalValue > 0, "Dynamic programming should select items with value");
    }

    @Test
    void testGreedyVsBruteForceVsDynamicProgrammingResult() {
        Greedy01 greedySolver = new Greedy01(testKnapsack);
        BruteForce01 bruteForceSolver = new BruteForce01(testKnapsack);
        DynamicProgramming01 dynamicSolver = new DynamicProgramming01(testKnapsack);

        greedySolver.solve();
        bruteForceSolver.solve();
        dynamicSolver.solve();

        double greedyValue = greedySolver.getSelectedTotalValue();
        double bruteForceValue = bruteForceSolver.getSelectedTotalValue();
        double dynamicValue = dynamicSolver.getSelectedTotalValue();

        assertTrue(bruteForceValue >= greedyValue, "Brute force should find an equal or better solution than greedy");
        assertTrue(dynamicValue == bruteForceValue, "Dynamic programming should match brute force solution");
    }

    @Test
    void testKnapsackCapacityConstraint() {
        Knapsack smallKnapsack = new Knapsack(2, 5, Arrays.asList(
                new Item(10, 50)
        ));

        Greedy01 greedySolver = new Greedy01(smallKnapsack);
        greedySolver.solve();

        assertEquals(0, greedySolver.getSelectedTotalValue(), "Should not select items that exceed capacity");
    }

    @Test
    void testRunKnapsack01() {
        Runner01.runKnapsack01();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Running 0/1 Knapsack"), "Program should execute the 0/1 knapsack process");
        assertTrue(output.contains("Best Value for Brute Force"), "Program should calculate brute force best value");
        assertTrue(output.contains("Best Value for Dynamic Programming"), "Program should calculate dynamic programming best value");
        assertTrue(output.contains("Best Value for Greedy Algorithm"), "Program should calculate greedy best value");
    }
}