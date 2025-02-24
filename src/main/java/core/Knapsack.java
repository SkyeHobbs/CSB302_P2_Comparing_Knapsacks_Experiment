package core;

import utils.Item;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a knapsack with a fixed capacity and a list of items.
 */
public class Knapsack {
    protected final int knapsackNumber; // The ID for the knapsack instance
    protected final int capacity; // The maximum weight capacity of the knapsack.

    protected final List<Item> items; // The list of all items available for the knapsack.

    /**
     * Constructs a Knapsack object.
     *
     * @param knapsackNumber The ID number of the knapsack.
     * @param capacity       The maximum weight capacity of the knapsack.
     * @param items          The list of items available in the knapsack.
     */
    public Knapsack(int knapsackNumber, int capacity, List<Item> items) {
        this.knapsackNumber = knapsackNumber;
        this.capacity = capacity;
        this.items = items;
    }

    /**
     * Gets the knapsack's unique identifier.
     *
     * @return The knapsack number.
     */
    public int getKnapsackNumber() {
        return knapsackNumber;
    }

    /**
     * Gets the maximum weight capacity of the knapsack.
     *
     * @return The capacity of the knapsack.
     */
    public int getKnapsackCapacity() {
        return capacity;
    }

    /**
     * Gets the list of items in the knapsack.
     *
     * @return A list of items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Calculates the total value of items in the knapsack.
     *
     * @return The total value of all items in the knapsack.
     */
    public int getKnapsackValue() {
        int totalValue = 0;
        for (Item item : items) {
            totalValue += item.getValue();
        }
        return totalValue;
    }

    /**
     * Prints the knapsack details, including its number, capacity, values, and weights.
     */
    public void printKnapsack() {
        List<Integer> values = items.stream().map(Item::getValue).collect(Collectors.toList());
        List<Integer> weights = items.stream().map(Item::getWeight).collect(Collectors.toList());

        System.out.println("Knapsack #: " + knapsackNumber);
        System.out.println("Knapsack Capacity: " + capacity);
        System.out.println("Knapsack Values: " + values);
        System.out.println("Knapsack Weights: " + weights);
    }

}
