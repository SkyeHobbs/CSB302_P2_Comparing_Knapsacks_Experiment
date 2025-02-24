package fractionalknapsack;

import core.Knapsack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerFractionalTest {

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
    void testGreedyFractionalSolver() {
        GreedyFractional greedySolver = new GreedyFractional(testKnapsack);
        greedySolver.solve();

        double totalValue = greedySolver.getSelectedTotalValue();
        assertTrue(totalValue > 0, "Greedy algorithm should select items with value");
    }

    @Test
    void testBruteForceFractionalSolver() {
        BruteForceFractional bruteForceSolver = new BruteForceFractional(testKnapsack);
        bruteForceSolver.solve();

        double totalValue = bruteForceSolver.getSelectedTotalValue();
        assertTrue(totalValue > 0, "Brute force should select the best value items");
    }

    @Test
    void testGreedyVsBruteForceResult() {
        GreedyFractional greedySolver = new GreedyFractional(testKnapsack);
        BruteForceFractional bruteForceSolver = new BruteForceFractional(testKnapsack);

        greedySolver.solve();
        bruteForceSolver.solve();

        double greedyValue = greedySolver.getSelectedTotalValue();
        double bruteForceValue = bruteForceSolver.getSelectedTotalValue();

        assertTrue(bruteForceValue >= greedyValue, "Brute force should find an equal or better solution than greedy");
    }

    @Test
    void testKnapsackCapacityConstraint() {
        Knapsack smallKnapsack = new Knapsack(2, 5, Arrays.asList(
                new Item(10, 50)
        ));

        GreedyFractional greedySolver = new GreedyFractional(smallKnapsack);
        greedySolver.solve();

        assertEquals(25, greedySolver.getSelectedTotalValue(), "Should not select items that exceed capacity");
    }

    @Test
    void testRunFractionalKnapsack() {
        RunnerFractional.runFractionalKnapsack();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Running fractional knapsack"), "Program should execute the fractional knapsack process");
        assertTrue(output.contains("Best Value for Brute Force"), "Program should calculate brute force best value");
        assertTrue(output.contains("Best Value for Greedy Algorithm"), "Program should calculate greedy best value");
    }
}