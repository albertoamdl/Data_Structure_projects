/*
 * PUT YOUR SOLUTION DESCRIPTION OVER HERE.
 */

import java.util.Scanner;

public class Main {
    
    public static int JosephusCircle(Node list, int n) { 
        while(list.next != list) 
        { 
          Node temp = list; 
             
            int count = 1; 
            while(count != n) 
            { 
                
                list = list.next; 
                count++; 
            } 
              
            /* Remove the node */
            temp.next = list.next; 
            list = temp.next; 
        } 
        return list.item; //last one standing
    }

    public static void printCircularLList(Node list) { //same as printing a regular list, but it stops at the beginning

       Node temp = list;
  
    // If linked list is not empty 
    if (list != null)  
    { 
        // Keep printing nodes till we reach the first node again 
        while (temp != list)
        { 
            System.out.println(temp.item + " "); 
            temp = temp.next; 
        } 
        
    }
        // TODO: Complete
    }

    public static Node createCircularLList(int n) {
         // Create a circular linked list of 
        // size N. 
        Node start = new Node(1); 
        Node curr = start; 

        for(int i = 2; i <= n; i++) 
        { 
            curr.next = new Node(i); 
            curr = curr.next; 
        } 
          
        // Connect last node to first 
        curr.next = start; 
          
        /* while only one node is left in the 
        linked list*/
        //Node temp = start; 

        return start; 
    }

    public static void main(String[] args) {
      		Scanner scnr = new Scanner(System.in);	

        // TODO: Complete according to the instructions
        // 1. Ask the user for the Number of people in the circle
        // 2. Create circular Linked List
        // 3. Ask the user for the elimination number n
        // 4. Do the elimination until there are 
        //    less than n people
        // 5. Print the remaining people
        // Node temp = createCircularLList(NumberOfPoeple);

        //         printCircularLList(temp);

        System.out.println("Please input number of people in the circle");
        int NumberOfPoeple = scnr.nextInt();
        Node temp = createCircularLList(NumberOfPoeple);

        printCircularLList(temp);

        System.out.println("What is the elimination number?");
        int Elimination = scnr.nextInt();



        System.out.println("Remaining People: " + JosephusCircle(createCircularLList(NumberOfPoeple),Elimination));

        
        //createCircularLList(NumberOfPoeple);

        

    }
}