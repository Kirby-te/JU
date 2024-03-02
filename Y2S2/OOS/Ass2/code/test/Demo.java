// package test;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
 
 
class Producer extends Thread{
        private static boolean check;
        private static int data;
        Producer(){
                data = 0;
                check = false;
        }
        static int get_data() {return data;}
        static boolean get_status(){return check;}
 
        public void run(){
                try {
                        File Obj = new File("file1.txt");
                        Scanner Reader = new Scanner(Obj);
                        while (Reader.hasNextLine()) {
                                synchronized(Main.lock){
                                        data = Reader.nextInt();
                                        System.out.println(data);
                                        Main.lock.notify();
                                        try{
                                                Main.lock.wait();
                                        }
                                        catch(InterruptedException e){
                                                e.printStackTrace();
                                        }
                                }
                        }
                        check = true;
                        Reader.close();
                        Main.lock.notify();
                }
                catch (FileNotFoundException e) {
                        System.out.println("An error has occurred.");
                        e.printStackTrace();
                }
        }
}
 
class Consumer extends Thread{
        private static int sum;
        Consumer(){
                sum = 0;
        }
 
        static int get_sum(){
                return sum;
        }
 
        public void run(){
                while(!Producer.get_status()){
                        System.out.println(Producer.get_status());
                        synchronized(Main.lock){
                                sum += Producer.get_data();
                                //System.out.println("Sum till now :"+sum);
                                Main.lock.notify();
                                try{
                                        if(!Producer.get_status())
                                        Main.lock.wait();
                                        else break;
                                }
                                catch(InterruptedException e){
                                        e.printStackTrace();
                                }
                        }
                }
        }
 
}
 
 
 
 
public class Demo{
 
        public static final Object lock = new Object();
 
        public static void main(String[] args) {
                Producer p = new Producer();
                Consumer c = new Consumer();
 
 
                p.start();
                c.start();
 
                while(p.isAlive() && c.isAlive()){
                        continue;
                }
 
                System.out.println("Total sum is :"+ Consumer.get_sum());
        }
 
}