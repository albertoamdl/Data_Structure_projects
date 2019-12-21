import java.util.Scanner;
import java.util.*;

public class Discrete2{

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void test(int n){
        int[] a = new int[n];

        for (int i = 1; i <= n; i++){
            a[i-1] = i;
        }

        int[][] aa = getAA(a);

        System.out.println("A X A:");

        for (int i=0; i < aa.length; i++){
            System.out.println("(" + aa[i][0] + " , " + aa[i][1] + ")");
        }

        System.out.println("Subsets of A X A:");
        subsets(aa, 0, new HashSet<Pair>());


    }

    public static int[][] getAA(int[] A){
        int[][] AA = new int[A.length * A.length][2];

        int count = 0;

        for (int i=0; i < A.length; i++){
            for (int j = 0; j < A.length; j++){
                AA[count][0] = A[i];
                AA[count][1] = A[j];
                count ++;
            }
        }

        return AA;
    }

    public static void subsets (int[][] AA, int index, HashSet<Pair> set){
        if (index >= AA.length)
        {
            System.out.print("{ ");

            for (Pair p : set){
                System.out.print("( " + p.x + " , " + p.y + ") ");
            }

            System.out.println("} ");

            return;
        }

        //Include AA[index] in subset
        HashSet<Pair> set1 = new HashSet<Pair>(set);
        set1.add(new Pair( AA[index][0] , AA[index][1])) ;  
        subsets(AA, index + 1, set1);

        // Do not include it
        HashSet<Pair> set2 = new HashSet<Pair>(set);

        subsets(AA, index + 1, set2);

        
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("input: ");
        int n = scnr.nextInt();
        test(n);
    }
}