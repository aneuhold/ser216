package assignment1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Takes user input from the console as a series of values to determine
 * if the values make a magic square matrix. This program will print
 * "true" if it is a magic square and "false" if it is not.
 * 
 * @author Anton G Neuhold Jr
 * @date 2018-03-17
 */
public class MagicSquare {
  private final static int INITIAL_ARRAY_SIZE = 10;

  /**
   * This is the main method and provides the user interaction point
   * for the program.
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean run = true;
    
    while (run == true) {
      int[][] intMatrix;
      int[] intArray = new int[INITIAL_ARRAY_SIZE];
      int intCount = 0;
      
      // Printing the description of the magic square for the user
      System.out.println("A n x n matrix filled with whole numbers 1,2,3,"
          + "...n^2 is a magic square if the sum of elements in each row, "
          + "column, and in the two diagonals are the same value.");
      System.out.println("An example magic square where n = 3 is: \n"
          + "8 1 6 \n"
          + "3 5 7 \n"
          + "4 9 2");
      System.out.println("An example input to get the previous magic square "
          + "would be: \n"
          + "8 1 6 3 5 7 4 9 2");
      
      // The user is asked for a series of numbers to form the matrix
      boolean validInput = false;
      while (validInput == false) {
        intArray = new int[INITIAL_ARRAY_SIZE];
        intCount = 0;
        
        System.out.println("Please enter a series of non-repeating numbers "
            + "that will form a n x n matrix: ");
        
        Scanner nextLine = new Scanner(in.nextLine());
        while (nextLine.hasNextInt()) {
          if (intCount == intArray.length) {
            intArray = Arrays.copyOf(intArray, intArray.length * 2);
          }
          intArray[intCount] = nextLine.nextInt();
          intCount++;
        }
        nextLine.close();
        
        // Checks if values were entered
        if (intCount == 0) {
          validInput = false;
        } 
        // Checks if the values form a complete matrix
        else if (Math.sqrt(intCount) - Math.floor(Math.sqrt(intCount)) != 0) {
          System.out.println("The series of values did not form a complete "
              + "matrix.");
          validInput = false;
        }
        // Checks if all values are unique
        else if (!valuesAreUnique(intCount, intArray)) {
          System.out.println("At least one of the values was not unique.");
          validInput = false;
        } else {
          validInput = true;
        }
      }
      
      // Converting the array to a matrix
      intMatrix = convertArrayToMatrix(intCount, intArray);
      
      // Testing and returning if the array is a magic square
      System.out.println(testForMagicSquare(intMatrix));
      
      // Asking the user if they would like to enter another square
      System.out.println("Would you like to enter another potential magic "
          + "square? (y/n)");
      if (!in.next().equalsIgnoreCase("y"))
        run = false;
    }
    
    in.close();
  }

  /**
   * Tests a provided n x n matrix and tests to see if it is a magic square
   * which means that all row's sum are the same, all column's sums are the
   * same, and that both diagonal's sums are the same. 
   * 
   * @param intMatrix
   * @return true if the matrix is a magic square.
   */
  private static boolean testForMagicSquare(int[][] intMatrix) {
    int previousRowSum = 0;
    int previousColSum = 0;
    int diagonalOneSum = 0;
    int diagonalTwoSum = 0;
    
    // Testing rows
    for (int row = 0; row < intMatrix.length; row++) {
      int sum = 0;
      for (int i = 0; i < intMatrix[row].length; i++) {
        sum += intMatrix[row][i];
      }
      if (row != 0 && previousRowSum != sum)
        return false;
      previousRowSum = sum;
    }
    
    // Testing columns
    for (int col = 0; col < intMatrix[0].length; col++) {
      int sum = 0;
      for (int i = 0; i < intMatrix.length; i++) {
        sum += intMatrix[i][col];
      }
      if (col != 0 && previousColSum != sum)
        return false;
      previousColSum = sum;
    }
    
    // Testing Diagonals
    int countDown = intMatrix.length - 1;
    for (int i = 0; i < intMatrix.length; i++) {
      diagonalOneSum += intMatrix[i][i];
      diagonalTwoSum += intMatrix[countDown][i];
      countDown--;
    }
    if (diagonalOneSum != diagonalTwoSum)
      return false;
    
    // If all tests pass and the code makes it to this point, returns true.
    return true;
  }

  /**
   * Creates a matrix from the provided array up to the index intCount - 1.
   * The matrix column length and row length are the square root of intCount.
   * 
   * @param intCount
   * @param intArray
   * @return the completed matrix as a 2D int array
   */
  private static int[][] convertArrayToMatrix(int intCount, int[] intArray) {
    int matrixSize = ((Double) Math.floor(Math.sqrt(intCount))).intValue();
    int[][] intMatrix = new int[matrixSize][matrixSize];
    int currentArrayIndex = 0;
    for (int col = 0; col < matrixSize; col++) {
      for (int row = 0; row < matrixSize; row++) {
        intMatrix[row][col] = intArray[currentArrayIndex];
        currentArrayIndex++;
      }
    }
    return intMatrix;
  }

  /**
   * Searches the provided array up to the index calculated by numValues - 1
   * for repeating values. If a repeating value is found, this method returns
   * false.
   * 
   * This method is O(n)
   * 
   * @param numValues
   * @param array
   * @return true when all values up to the numValues - 1 index are unique
   */
  private static boolean valuesAreUnique(int numValues, int[] array) {
    Set<Integer> intSet = new HashSet<Integer>();
    for (int i = 0; i < numValues; i++) {
      if (intSet.contains(array[i])) {
        return false;
      }
      intSet.add(array[i]);
    }
    return true;
  }

}
