
/*
ALBERTO ANTONIO MIRANDA 80636136 

 * What i plan to do is, depending on user's choice, read the file and input it to a 2D array of characters or let the user populate the 2D array her/himself.
 * Then i will check to if it is a latinsquare by creating two other methods that check if the character don't repeat in either rows or columns, if they dont, 
 * I return true.
 * readFile: In order to read the file provided by the user, I set up the method to recieve the filename and size of the Latin square, I start by initializing
 * the scanner to read the file. Next, i created a while.HasNext loop that basically iterates until the end of the file is reached; inside the while loop 
 * I sort out the characters in the array from firsts, seconds, thirds and so on. still inside the while loop, I populate a new array with the material that
 * the Rscnr just read from the fike.
 * noRepeatsInRow: This is a quite simple method, I basically check that there are no repaeting letters per Row by comparing each letter to the previous position.
 * noRepeatsinColumn: This is another simple method, alike to norepeatsInRow, it checks each letter with the previous one, the only diffrence is that it checks columns instead.
 * isLatinSquare: this methos combines both noReapeatsin both rows and columns and adds a isSquare, if all are true, it will return true.
 * Finally, in main I ask the user if they'd like to populate the array or input a file, after user input, it will check if it is a Latin Square or not, After it
 * will simply print "Is Latin Square!" of "Is not Latin Square"
 */
import java.io.*;
import java.util.Scanner;

public class Lab1 {

  public static char[][] readFile(String filename, int size) {

    File test = new File(filename);

    // inializing scanner to read the file:
    try {
      Scanner Rscnr = new Scanner(test);
      Scanner scnr = new Scanner(System.in);

      int numChar = 0;
                                                //This while loop was made for debugging purposes, it does not interfere nor effect the outcome
      while (Rscnr.hasNextLine()) {
        numChar++;
        Rscnr.nextLine();
      }

      // Pecan[] pecanArray = new Pecan[numChar];

      Rscnr = new Scanner(test);
      int i = 0;
      String[] a;
      char[][] arrray = new char[size][size];
      while (Rscnr.hasNextLine()) {

        String line = Rscnr.nextLine();
        a = line.split(" ");

        String firsts = a[0];
        String secondS = a[1];
        String thirdS = a[2];                                     //sets the first characters a initializes them with names
        String fourthS = a[3];
        String fifthS = "";

        arrray[i][0] = firsts.charAt(0);
        arrray[i][1] = secondS.charAt(0);                           //populates the array on line at a line with given names
        arrray[i][2] = thirdS.charAt(0);
        arrray[i][3] = fourthS.charAt(0);

        i++;                      //each time i updates, a new set of rows will be populated

      }
      return arrray;

    } catch (FileNotFoundException ex) {

      System.out.println("try again");
    }

    return null;
  }

  public static boolean noRepeatsInRow(char[][] s) {   //Check =s to see that no rows repeat, self explanatory
    for (int i = 0; i < s.length; i++) {
      for (int j = 1; j < s.length; j++) {
        if (s[i][j] != s[i][j - 1]) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean noRepeatsInColumn(char[][] s) {    //Checks to see that no columns repeat, self explanatory
    for (int i = 1; i < s.length - 1; i++) {
      for (int j = 0; j < s.length; j++) {
        if (s[i][j] != s[i - 1][j]) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isLatinSquare(char[][] square) {  //Checks to see if it is a latin square
    boolean isIt = false;
    if (noRepeatsInColumn(square) && noRepeatsInRow(square) && isSquare(square)) {
      isIt = true;
    }

    return isIt;
  }

  public static boolean isSquare(char[][] a) {   //Checks to see if input is a square
    if (a[0].length == a.length) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    // Scanner Rscnr = new Scanner(input_false);
    Scanner scnr = new Scanner(System.in);

    System.out.println("Would you like to: ");
    System.out.println("1) create an array");
    System.out.println("2) input a file?");
    int response = scnr.nextInt();
    scnr.nextLine();

    switch (response) {

    case 1:

      System.out.println("What size of Latin Square would you like?");
      int size = scnr.nextInt();
      char Latin[][] = new char[size][size];
      scnr.nextLine();
      System.out.println("Would you like to popuate the array? Y/N ");
      String response2 = scnr.nextLine();

      if (response2.equals("Y")) {
        for (int i = 0; i < Latin.length; i++) {
          for (int j = 0; j < Latin[0].length; j++) {

            System.out.println("Please fill in for area [" + i + "," + j + "] : (Must be Characters)");
            String input = scnr.nextLine();
            char h = input.charAt(0);

            Latin[i][j] = h;

          }
        }
        System.out.println("Done populating the matrix ! ");

        // isLatinSquare(Latin);

        if (isLatinSquare(Latin)) {
          System.out.println("It is a latin square!");
        }
        else{
          System.out.println("Not a latin square");
        }
      }

      break;

    case 2:

      System.out.println("Please input file directory (name) : ");
      String filename = scnr.nextLine().trim();
      scnr.nextLine();
      // File LatinSquare = new File(filename);
      System.out.println("What is the size of the array? ");
      int Size = scnr.nextInt();
      char[][] x = readFile(filename, Size);

      if (isLatinSquare(x)) {
        System.out.println("It's a latin square!");
      } else {
        System.out.println("Not a Latin square");
      }
    }
  }
}
