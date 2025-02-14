package knapsack01;

import core.AbstractKnapsackSolver;
import utils.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DynamicProgramming01 extends AbstractKnapsackSolver {

  /**
   * Constructs an AbstractKnapsackSolver with the given items, capacity, and knapsack number.
   *
   * @param items          The list of items available for selection.
   * @param capacity       The maximum weight capacity of the knapsack.
   * @param knapsackNumber The identifier for the knapsack instance.
   */
  public DynamicProgramming01(List<Item> items, int capacity, int knapsackNumber) {
    super(items, capacity, knapsackNumber);
    selectedItems = new ArrayList<>();
  }

  /**
   * Solves the knapsack problem.
   * Subclasses must implement this method.
   */
  @Override
  public void solve() {
    //Ensure knapsack has capacity and items exist.
    if (capacity == 0 || items.isEmpty()) {
      totalValue = 0;
    } else {
      //Create 2d array where each cell represents the solution to sub problems.
      int itemCount = items.size();
      double[][] arr = new double[itemCount + 1][capacity + 1];
      //Iterate through the table and fill in the values.
      //The cells in the first row and column should be 0, so they are skipped.
      for (int i = 0; i <= itemCount; i++) {
        for (int j = 0; j <= capacity; j++) {
          //If looking at a cell on the edge fill in 0.
          if (i == 0 || j == 0) {
            arr[i][j] = 0;
          } else if (j < items.get(i - 1).getWeight()) {
            //If the column is less than the weight of the current item,
            //fill the current cell with the value from the one above.
            arr[i][j] = arr[i - 1][j];
          } else {
            //If the column is at least the weight of the current item
            //fill the cell with the higher value between the one in the cell above or
            //the current item's value plus the value of the cell in the row above in
            //the column minus the current item's weight. Assumes the weight is an integer
            //because this is an 01 knapsack.
            arr[i][j] = Math.max(arr[i - 1][j], items.get(i - 1).getValue()
                    + arr[i - 1][j - (int) items.get(i - 1).getWeight()]);
          }
        }
      }
      //Item in the final cell has the total value.
      totalValue = arr[itemCount][capacity];

      //Add the selected items to arraylist.
      //Start by looking at the final cell.
      int row = itemCount;
      int column = capacity;
      //While loop ends when looking at a cell on the edge of the array.
      while (row != 0 && column != 0) {
        //If the cell above has a different value
        if (arr[row][column] != arr[row - 1][column]) {
          //Add the current row's item to the arraylist.
          selectedItems.add(items.get(row - 1));
          //Subtract the selected item's weight from the columns.
          //Assumes integer weight because this is an 01 knapsack.
          column -= (int) items.get(row - 1).getWeight();
        }
        //Move up a row.
        row--;
      }
    }
  }
}
