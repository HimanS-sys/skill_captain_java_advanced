// System that checks users bank balance

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// represents a single transaction operation on the bank account
class Transaction extends Thread{
    private final BankAccount bankAccount;
    private final String type;
    private double amount;

    public Transaction(String name, BankAccount bankAccount, String type, double amount) {
        super(name);
        this.bankAccount = bankAccount;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {
        if(this.type.equals("deposit")) {
            this.bankAccount.deposit(amount);
        } else if(this.type.equals("withdraw")) {
            this.bankAccount.withdraw(amount);
        } else {
            System.out.println(Thread.currentThread().getName() + ": enter either 'deposit'/'withdraw' to perform.");
        }
    }
}

// holds balance details and deposit/withdraw methods
class BankAccount {

    Lock lock = new ReentrantLock();
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }
    // Deposit money into the account, ensuring thread safety with a lock
    public void deposit(double amount) {
        lock.lock();
        try {
            if (amount <= 0) {
                System.out.println(Thread.currentThread().getName() + ": amount should be greater than 0");
                return;
            }
            this.balance += amount;
            System.out.println(Thread.currentThread().getName() + ": Deposited $" + amount);
        } finally {
            lock.unlock();
        }
    }
    // Withdraw money from the account, ensuring thread safety with a lock
    public void withdraw(double amount) {
        lock.lock();
        try {
            if (amount > this.balance) {
                System.out.println(Thread.currentThread().getName() + ": enter an amount less than or equal to current balance");
                return;
            } else if (amount <= 0) {
                System.out.println(Thread.currentThread().getName() + ": amount should be greater than 0");
            }
            this.balance -= amount;
            System.out.println(Thread.currentThread().getName() + ": Withdrawn $" + amount);
        } finally {
            lock.unlock();
        }
    }
    // Get the current balance of the account
    public double getBalance() {
        return this.balance;
    }
}


public class Main {
    public static void main(String[] args) {
        // Create a BankAccount instance with an initial balance of $1000
        BankAccount bankAccount = new BankAccount(1000);
        // Print the initial balance of the bank account.
        System.out.println("Initial balance:" + bankAccount.getBalance());

        // Create Transaction instances representing different threads
        Transaction thread1 = new Transaction("Thread 1", bankAccount, "withdraw", 50.50);
        Transaction thread2 = new Transaction("Thread 2", bankAccount, "deposit", 87.63);
        Transaction thread3 = new Transaction("Thread 3", bankAccount, "withdraw", 105.00);
        Transaction thread4 = new Transaction("Thread 4", bankAccount, "withdraw", 352.34);
        Transaction thread5 = new Transaction("Thread 5", bankAccount, "deposit", 81.00);

        // start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        // wait for all the threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch(InterruptedException e) {
            System.err.println("Thread interrupted while waiting: " + e.getMessage());
        }

        // Print the final balance of the bank account.
        System.out.printf("Final balance: $%.2f", bankAccount.getBalance());
    }
}