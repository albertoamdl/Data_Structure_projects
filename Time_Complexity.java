import java.lang.Math; 
import java.util.*;

/*
 To begin the lab I first created a simple java signatures that best imply the functions I will be implementing in each method. 
 The first method is a constant, which means it constantly executes the same.
 MethodB Executes in (log(n)), which means that the output gradually decreases in complexity, this is simply done by multiplying i * 2 which will quickly satisisfy the condition.
 the third method (MethodB) was by far the hardest, with help of a Computer Science proffessor Diego Aguirre,
 I was able to better understand this problem. Mr. Aguirre mentioned a "shrinks by the square root" algorithm, which 
 basically he explains that in the same way that O(log(n)) shrinks the complexity by manipulating a constant, i could also be done by getting the
 square root of each folowing exponents consequentially, by doing such s/he noticed a pattern. The writer then says to recieve any number n and write 
 it as n = 2^k. Each time the Sqrt of n is taken, half of the exponent in this equation will be taken; " So, there can be only O(log k) square roots applied
before k drops to 1 or lower (in which case n drops to 2 or lower). Since n = 2k, this means that k = log2 n, and therefore the number of square roots taken is O(log k) = O(log log n)." 
The next method multiplies log(n) by itself 3 times, to do so, i simply just repaeted the same for loop exactly 3 times.
MethodE, for n log(n) simply multiplies n to log(n). To do this, I added two for loops, the first is O(n) and the nested loop 
is O(log n).
for the next method, i added a simple for loop that contains a constant condition, thus creating a inear algorithm.
Methods g, h, and j are quite similar, I simply added the power of the variables needed to the condition, this it will repeat until the condition 
is false. There's other ways to do this, but "the outcome is the same as long as it is satisfied". '
Method I is finding factorial of n, for this, I used recursion that multiplies by itself then subtracts the index by one.

 */

class Main {

    // TODO: one method per class
    /* For each method, in a comment, write:
     *    - which class it belongs to,
     *    - why it belongs to that class, and
     *    - how it behaves as the size of the input increases.
     */
    public static void methodA(int[] arr) { //Constant Output in regards to input n
        System.out.print("Oh! Hello there!");
    }

    public static void methodB(int [] a){ // Output gradually decreases in complexity
    for (int i = 1; i < a.length; i = i * 2){
        System.out.println("Oh! Hello There" + i);
      }   
    }

    public static void methodC(int[] arr) {  //This method executed the log(log(log(n))) 
      int n = (int) Math.pow(2, arr.length);
      int count = 0;
      for (int i = n ; i> 1; i = (int) Math.sqrt(Math.sqrt(i))){ // Credit to: Professor Diego Aguire UTEP
          count ++;                                                 
          System.out.println(i);
      }   
      System.out.println("count : " + count); // used for dubugging
    }

    public static void methodD(int[] arr) { //log(n) is multiplied by itslef 3 times, to do this, I added 3 for-loops.
        int n = arr.length;
        for (int x = 1; x < arr.length; x = x * 2){
            for (int i = 1; i < arr.length; i = i * 2) {
                for (int j = 1; x < arr.length; j = j * 2){
                        System.out.println("Oh! Hello There" + i);
                }   
            }
        }
    }
    public static void methodE(int[] arr) {  //multiplies n times log(n), simply done by nesting a log(n) loop under a linear loop.
        for (int i = 0; i < arr.length; i++) {
            for (int x = 1; x < arr.length; x = x * 2) {
                System.out.println("Oh! Hello There" + i + " , " + x);
            }
        }
    }
    public static void methodF(int[] arr) { //Linear loop, output complexity is equal to input complexity; to do this, I simply added a for loop with a constant condition.
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Oh! hello there!" + i);
        }
    }
    public static void methodG(int[] arr) {  //loop is controlled by how fast n^5 will reach condition. Therefore, O(n^5) is seen.
        int n = arr.length;
        for (int i = 1; i <= Math.pow(n, 5); i++){
            System.out.println("Oh! Hello there" + i);
        }
    }
    public static void methodH(int[] arr) {  // loop is controlled by how fast 4^n will reach condition. Therefore, O(4^n) is seen.
        int n = arr.length;
        for (int i = 1; i <= Math.pow(4, n); i++){
            System.out.println("Oh! Hello there" + i);
        }
    }
    public static int methodI(int[] arr, int index) { // factorial of array size, n if found reccursivly by multipling by itself then subtracting one.
        if (index == 0){
            return 1;
        }
        return index * methodI(arr, index - 1);
        
    }
    public static void methodJ(int[] arr) {  //loop is controlled by how fast n^n will reach condition. Therefore, O(n^n) is seen.
        int n = arr.length;
        for (int i = 1; i <= Math.pow(n, n); i++){
            System.out.println("Oh! Hello there" + i);
        }
    }
    public static void main(String[] args) {
        long startTime;
        long endTime;
Scanner scnr = new Scanner(System.in);
        // TODO: Test your methods!
        
        // Below is a template you can copy+paste 
        // to measure the execution time
        // of your methods. 
        System.out.print("What size array would you like to create? ");
        int size = scnr.nextInt();
        int [] a = new int [size];
        System.out.println("Method Name here.");
        startTime = System.currentTimeMillis();
        System.out.println(methodI(a, size));
        // TODO: Run the method here
        endTime = System.currentTimeMillis();
        System.out.printf("Execution time: %d ms%n", 
            (endTime - startTime));
    }
}