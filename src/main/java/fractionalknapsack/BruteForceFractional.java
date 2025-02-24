package fractionalknapsack;

import core.AbstractKnapsackSolver;
import core.Knapsack;
import utils.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceFractional extends AbstractKnapsackSolver {
    private final Map<String, Double> memo = new HashMap<>(); //Memoization for subproblems

    /**
     * Constructs an AbstractKnapsackSolver with the given Knapsack.
     *
     * @param knapsack The input knapsack instance.
     */
    public BruteForceFractional(Knapsack knapsack) {
        super(knapsack);
        selectedItems = new HashMap<>();
        selectedTotalValue = 0;
    }

    /**
     * Solves the knapsack problem.
     * Subclasses must implement this method.
     */
    @Override
    public void solve() {
        Map<Item, Double> bestSelection = new HashMap<>();
        double bestValue = bruteForceFractional(knapsack.getItems().size(), knapsack.getKnapsackCapacity(), bestSelection);
        selectedItems.putAll(bestSelection);
        setSelectedTotalValue(bestValue);
    }

    /**
     * Recursive brute force function that finds the best value by trying every combination
     * breaking the final item into a fractional value to fully use capacity.
     *
     * @param n             The number of items being considered.
     * @param capacity      The remaining capacity of the knapsack.
     * @param bestSelection A map to store the best selection of items.
     * @return The maximum value.
     */
    private double bruteForceFractional(int n, int capacity, Map<Item, Double> bestSelection) {
        // Ensure there are items and capacity
        if (n == 0 || capacity == 0) {
            return 0;
        }
        String key = n + "-" + capacity;
        if (memo.containsKey(key)) return memo.get(key);

        Item currentItem = knapsack.getItems().get(n - 1); // Save the current item

        // If the current item is to heavy skip it
        if (currentItem.getWeight() > capacity) {
            double fraction = (double) capacity / currentItem.getWeight();
            bestSelection.put(currentItem, fraction);
            double result = fraction * currentItem.getValue();
            memo.put(key, result);
            return result;
        }

        // Create copies of bestSelection to track item inclusion and exclusion
        Map<Item, Double> includeSelection = new HashMap<>(bestSelection);
        Map<Item, Double> excludeSelection = new HashMap<>(bestSelection);

        // Save the value if the item is included or excluded
        double include = currentItem.getValue() + bruteForceFractional(n - 1, capacity - currentItem.getWeight(), includeSelection);
        double exclude = bruteForceFractional(n - 1, capacity, excludeSelection);

        // Select the better option
        double result;
        if (include > exclude) {
            includeSelection.put(currentItem, 1.0);
            bestSelection.clear();
            bestSelection.putAll(includeSelection);
            result = include;
        } else {
            bestSelection.clear();
            bestSelection.putAll(excludeSelection);
            result = exclude;
        }
        memo.put(key, result);
        return result;
    }


    /**
     * Helper method to get the best fractional value of the current items
     *
     * @param n             The number of items being considered.
     * @param capacity      The remaining capacity of the knapsack.
     * @param bestSelection A map to store the best selection of items.
     * @return The maximum value.
     */
    private double fractionalHelper(int n, double capacity, Map<Item, Double> bestSelection) {
        List<Item> sortedItems = knapsack.getItems().subList(0, n);
        sortedItems.sort((a, b) -> Double.compare((double)b.getValue() / (double)b.getWeight(), (double)a.getValue() / (double)a.getWeight()));
        double totalValue = 0.0;

        for (Item item : sortedItems) {
            if (capacity == 0) break;  // Stop when knapsack is full
            double weightToTake = Math.min(item.getWeight(), capacity);
            double fraction = weightToTake / item.getWeight();
            bestSelection.put(item, fraction);
            totalValue += fraction * item.getValue();
            capacity -= weightToTake;
        }
        return totalValue;
    }
}