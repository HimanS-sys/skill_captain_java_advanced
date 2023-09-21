import java.util.Random;

class Runner implements Runnable{
    private String divLeft;

    private String divRight;
    public static boolean exit = false;
    private String name;
    private int totalRan;
    private int raceDistance;

    public Runner(String name, String divLeft, String divRight) {
        this.name = name;
        this.totalRan = 0;
        this.raceDistance = 1000;
        this.divLeft = divLeft;
        this.divRight = divRight;
    }

    public int getTotalRan() {
        return totalRan;
    }

    public void setTotalRan(int totalRan) {
        this.totalRan = totalRan;
    }

    public int getRaceDistance() {
        return raceDistance;
    }

    public void setRaceDistance(int raceDistance) {
        this.raceDistance = raceDistance;
    }

    @Override
    public void run() {
        while(exit == false) {
            Random rand = new Random();
            this.totalRan = this.totalRan + rand.nextInt(150,200);
            if(this.totalRan >= this.raceDistance) {
                exit = true;
                System.out.println("The winner is " + this.name + ".");
                break;

            } else {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.divLeft + " " + this.name + ": " + this.totalRan + " meter " + this.divRight);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // main function
        System.out.println("--- 1000 meter race ---");
        System.out.println("--- race begins! ---");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // improving output readability
        String amarDiv = "---------------";
        String akbarDiv = "----------------";
        String anthonyDiv = "------------------";


        // creating runner instances
        Runner runner1 = new Runner("Amar", "", akbarDiv + anthonyDiv);
        Runner runner2 = new Runner("Akbar", amarDiv, anthonyDiv);
        Runner runner3 = new Runner("Anthony", amarDiv + anthonyDiv, "");

        // create threads and pass Runner instances
        Thread thread1 = new Thread(runner1);
        Thread thread2 = new Thread(runner2);
        Thread thread3 = new Thread(runner3);

        // start the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}