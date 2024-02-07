package q1;

class Sample extends Thread {
    public void run() {
        System.out.println("In main thread..");
    }    
}

class SampleChild extends Sample {
    public void run() {
        System.out.println("In child thread..");
    }
}

public class Main {
    public static void main(String[] args) {
        Sample s1 = new Sample();
        s1.start();
		
		SampleChild sc1 = new SampleChild();
		sc1.start();
    }
}
