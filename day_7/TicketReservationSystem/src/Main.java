// program simulating ticket booking system

// imports
import java.util.HashMap;


// runs the threads for each user concurrently
class User extends Thread{
    private final Theater theater;
    private final String userID;
    private int seatNumber;
    public User(String name, Theater theater, String userID, int seatNumber) {
        super(name + "@" + userID);
        this.theater = theater;
        this.userID = userID;
        this.seatNumber = seatNumber;
    }

    @Override
    public void run() {
        theater.bookSeat(this.seatNumber);
    }
}

// class containing information about theater seats and seat booking logic
class Theater {
    private HashMap<Integer, String> bookingSeats;

    public Theater() {
        this.bookingSeats = new HashMap<>();
        for(int i = 1; i <= 30; i++) {
            this.bookingSeats.put(i, "available");
        }
    }

    public synchronized void bookSeat(int seatNumber) {
        if(!this.bookingSeats.containsKey(seatNumber)) {
            System.out.println(Thread.currentThread().getName() + ": seat number should be between 1 and 30");
            return;
        } else if(this.bookingSeats.get(seatNumber).equals("available")) {
            System.out.println(Thread.currentThread().getName() + ": seat no. " + seatNumber + " booked successfully!");
            this.bookingSeats.put(seatNumber, "unavailable");
            return;
        } else if(this.bookingSeats.get(seatNumber).equals("unavailable")) {
            System.out.println(Thread.currentThread().getName() + ": seat no. " + seatNumber + " unavailable, choose another seat");
            return;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("---------- Ticket Booking System ----------");

        Theater theater = new Theater();

        User task1 = new User("Himanshu", theater, "1000", 1);
        User task2 = new User("Sandeep", theater, "1002", 5);
        User task3 = new User("Gaurav", theater, "1003", 18);
        User task4 = new User("Rishabh", theater, "1004", 35);
        User task5 = new User("Arnab", theater, "1005", 7);
        User task6 = new User("Deesha", theater, "1006", 13);
        User task7 = new User("Vaibhav", theater, "1007", 5);
        User task8 = new User("Siddhi", theater, "1008", 14);


        task1.start();
        task2.start();
        task3.start();
        task4.start();
        task5.start();
        task6.start();
        task7.start();
        task8.start();
        try{
            task1.join();
            task2.join();
            task3.join();
            task4.join();
            task5.join();
            task6.join();
            task7.join();
            task8.join();
        } catch(InterruptedException e) {
            System.err.println("Thread interrupted while waiting: " + e.getMessage());
        }
    }
}