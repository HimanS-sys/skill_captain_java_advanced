

public class Main {
    static class AddNumThread extends Thread {
        private String name;
        private int size;
        private boolean isEven;
        public AddNumThread(String name, boolean isEven, int size) {
            this.name = name;
            this.isEven = isEven;
            this.size = size;
        }

        @Override
        public void run() {
            // code to be executed
            int i; // loop counter
            if(this.isEven) {
                for(i = 2; i <= this.size; i+=2) {
                    System.out.println(this.name + ": " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                for (i = 1; i <=this.size;i+=2) {
                    System.out.println(this.name + ": " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public static void main(String[] args) {
        System.out.println("Thread execution example:");
        //create two threads
        Thread thread1 = new AddNumThread("Thread 1", false, 10);
        Thread thread2 = new AddNumThread("Thread 2", true, 10);
        // start the threads
        thread1.start();
        thread2.start();
    }
}