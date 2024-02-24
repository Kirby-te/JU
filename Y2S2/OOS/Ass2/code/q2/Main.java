/*
Create two threads EvenThread and OddThread
EvenThread will print number as 2 4 6 8 10... and
Odd Thread will print number as 1 3 5....
Now, synchronize these two threads to get the output as: 1 2 3 4 5...                                   1 2 3 4 5 6 7 8
*/

package q2;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        Object lock = new Object();

        Thread evenThread = new Thread() {
            public void run() {
                synchronized (lock) {
                    for(int i=0; i<n; i++) {
                        System.out.print(2*i+2 + " ");
                        lock.notify();
                        try{
                            if(i < n - 1)
                                lock.wait();
                        }
                        catch(Exception e) {}
                    }
                }
            }
        };

        Thread oddThread = new Thread() {
            public void run() {
                synchronized (lock) {
                    for(int i=0; i<n; i++) {
                        System.out.print(2*i+1 + " ");
                        lock.notify();
                        try{
                            if(i < n - 1)
                                lock.wait();
                        }
                        catch(Exception e) {}
                    }
                }
            }
        };

        oddThread.start();
        evenThread.start();
    }
}