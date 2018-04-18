package assignment5;

public class Calculation {

  public static int oddOrPos(int[] x) {
    //Effects: if x==null throw NullPointerException
    // else return the number of elements in x that
    // are either odd or positive (or both)
    int count = 0;
    for (int i = 0; i < x.length; i++) {
      if (x[i] % 2 == 1 ||
            x[i] % 2 == -1 ||
            x[i] > 0) {
        count++;
      } 
    }
    return count;
  }

}
