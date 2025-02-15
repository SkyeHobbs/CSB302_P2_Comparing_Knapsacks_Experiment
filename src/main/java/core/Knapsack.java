package core;

import utils.Item;

import java.util.List;

public class Knapsack {
  protected final int knapsackNumber; // The ID for the knapsack instance
  protected final int capacity; // The maximum weight capacity of the knapsack.

  protected final List<Item> items; // The list of all items available for the knapsack.

  public Knapsack(int knapsackNumber, int capacity, List<Item> items){
    this.knapsackNumber = knapsackNumber;
    this.capacity = capacity;
    this.items = items;
  }

  public int getKnapsackNumber(){
    return knapsackNumber;
  }

  public int getKnapsackCapacity(){
    return capacity;
  }

  public Item getItems(){
    return (Item) items;
  }

}
