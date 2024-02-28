/*
 * following parallel binary search algorithm for series a1, a2...an sorted in increasing order such that n mod 10 = 0
 * Element to be searched is e
 *  a) Create n/10 threads t1, t2,..,tn/10
 *  b) Distribute the numbers among threads such that ti will have numbers (ai, ai+1, ....a2i-1) -> a((i-1)*10+1)...a((i-1)*10+10)
 *  c) Distribute the element e to all threads
 *  d) Each thread searches the element e in its sub-array using binary search algorithm
*/

package q6;

public class Search {
    private static int n;
    private static int numberOfThread;
    private static Thread[] threads;
    private static int threadIndexOfElement = -1;
    private static int arrayIndexOfTarget = -1;
    private static int currentThreadIndex;

    private static int binarySearch(int[] arr, int startIndex, int endIndex, int target) {
        while(startIndex <= endIndex) {
            int midIndex = startIndex + (endIndex - startIndex) / 2;
            if(target < arr[midIndex]) {
                endIndex = midIndex - 1;
            } else if(target > arr[midIndex]) {
                startIndex = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }

    public static int[] searchElementUsingThreads(int[] arr, int target) {
        n = arr.length;
        numberOfThread = (n%10 == 0) ? n/10 : n/10 + 1;
        threads = new Thread[numberOfThread];
        currentThreadIndex = 0;

        while(currentThreadIndex < numberOfThread) {
            int startIndex = (currentThreadIndex)*10;
            int endIndex = Math.min((currentThreadIndex+1)*10 - 1, n - 1);
            threads[currentThreadIndex] = new Thread() {
                public void run() {
                    int index = binarySearch(arr, startIndex, endIndex, target);
                    if(index != -1) {
                        threadIndexOfElement = currentThreadIndex + 1;
                        arrayIndexOfTarget = currentThreadIndex * 10 + index + 1;
                        index = -1;
                    }
                }
            };
            currentThreadIndex++;
        }

        for(int i=0; i<numberOfThread; i++) {
            threads[i].run();
        }

        try {
            for(int i=0; i<numberOfThread; i++) {
                threads[i].join();
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }

        return new int[] {threadIndexOfElement, arrayIndexOfTarget};// if not found: {-1, -1} 
    }
}
