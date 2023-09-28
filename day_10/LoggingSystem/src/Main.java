// program simulating a logging system

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

class Logger {
    DateTimeFormatter dateTimeFormatter;
    private final File filePath;
    private static volatile Logger logger;
    private static List<String> logList;
    private Logger() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        logList = new CopyOnWriteArrayList<>();
        this.filePath = new File("logger.txt");
        try {
            boolean result = filePath.createNewFile();
        } catch(IOException e) {
            System.err.println("An error occurred while writing to the file:" +
                    "\nFile Path: " + filePath.getAbsolutePath() + "\nError Details: " + e.getMessage());
        }
    }

    public static Logger getInstance() {
        if(logger == null) {
            synchronized (Logger.class) {
                if(logger == null) {
                    logger = new Logger();
                }
            }
        }

        return logger;
    }
    public void log(String message) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(4000) + 1000);
        } catch(InterruptedException e) {
            System.err.println("Thread got interrupted while running: " + e.getMessage());
        }
        LocalDateTime timeNow = LocalDateTime.now();
        logList.add("[" + dateTimeFormatter.format(timeNow) + "] " + Thread.currentThread().getName() + ": " + message);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){

            writer.write( "[" + dateTimeFormatter.format(timeNow) + "] " + message + "\n");
        } catch(IOException e) {
            System.err.println("An error occurred while writing to the file:" +
                    "\nFile Path: " + filePath.getAbsolutePath() + "\nError Details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // view last n logs
    public static synchronized void viewLog(int lastNLogs) {
        if(logList.isEmpty()) {
            System.out.println("No logs added");
            return;
        } else if(lastNLogs <= 0) {
            System.out.println("enter a positive number");
        }
        System.out.println("last " + lastNLogs + " logs:");
        for(int i = logList.size() - Math.min(lastNLogs, logList.size()); i < logList.size(); i++) {
            System.out.println(logList.get(i));
        }
    }
}

// represents a thread responsible for writing log messages
class LogWriterThread extends Thread {

    Logger logger;
    private final String message;

    public LogWriterThread(String threadName, String message) {
        super(threadName);
        this.message = message;
        this.logger = Logger.getInstance();
    }

    @Override
    public void run() {
        logger.log(message);
    }
}
public class Main {
    public static void main(String[] args) {
        // creating threads
        LogWriterThread thread1 = new LogWriterThread("Thread1", "Thread 1 created.");
        LogWriterThread thread2 = new LogWriterThread("Thread2", "Thread 2 created.");
        LogWriterThread thread3 = new LogWriterThread("Thread3", "Thread 3 created.");
        LogWriterThread thread4 = new LogWriterThread("Thread4", "Thread 4 created.");
        LogWriterThread thread5 = new LogWriterThread("Thread5", "Thread 5 created.");
        LogWriterThread thread6 = new LogWriterThread("Thread6", "Thread 6 created.");
        LogWriterThread thread7 = new LogWriterThread("Thread7", "Thread 7 created.");
        LogWriterThread thread8 = new LogWriterThread("Thread8", "Thread 8 created.");
        LogWriterThread thread9 = new LogWriterThread("Thread9", "Thread 9 created.");
        LogWriterThread thread10 = new LogWriterThread("Thread10", "Thread 10 created.");
        LogWriterThread thread11 = new LogWriterThread("Thread11", "Thread 11 created.");
        LogWriterThread thread12 = new LogWriterThread("Thread12", "Thread 12 created.");
        LogWriterThread thread13 = new LogWriterThread("Thread13", "Thread 13 created.");
        LogWriterThread thread14 = new LogWriterThread("Thread14", "Thread 14 created.");
        LogWriterThread thread15 = new LogWriterThread("Thread15", "Thread 15 created.");
        LogWriterThread thread16 = new LogWriterThread("Thread16", "Thread 16 created.");

        // start all the threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread11.start();
        thread12.start();
        thread13.start();
        thread14.start();
        thread15.start();
        thread16.start();

        // notice changes in last log
        for(int i = 0; i < 27; i++) {
            try {
                Thread.sleep(300);
            } catch(InterruptedException e) {
                System.err.println("Thread got interrupted while running: " + e.getMessage());
            }
            Logger.viewLog(1);
        }



    }
}