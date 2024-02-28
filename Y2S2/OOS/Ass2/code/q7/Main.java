/*
 * Write a Java program using threading technology
 * print the thread index and location where the element has been found.
*/

package q7;

import q6.Search;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[100];
        int target = 12;

        for(int i=0; i<arr.length; i++) {
            arr[i] = i + 1;
        }

        int[] answer = Search.searchElementUsingThreads(arr, target);

        if(answer[0] == -1) {
            System.err.println("Element not found!");
        } else {
            System.out.println("Element " + target + " found at:");
            System.out.println("Thread Index: " + answer[0] + " out of " + arrayLength/10 + " Threads");
            System.out.println("Location: " + answer[1] + " element out of " + arrayLength + " Elements");
        }
    }
}
