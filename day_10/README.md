# Assignments

## Task
Implement a multi-threaded Java program that simulates a logging system. The program should use the Singleton pattern to ensure that only one instance of the logger exists and can be accessed by multiple threads.

## Requirements
1. Create a Logger class that represents a logging system. It should have the following methods:
- getInstance(): Returns the single instance of the logger.
- log(String message): Logs the given message.
2. Implement a LogWriterThread class that represents a thread responsible for writing log messages. Each LogWriterThread object should retrieve the singleton Logger instance using the getInstance() method and log a unique message.
3. Create multiple instances of the LogWriterThread class and start them concurrently.
4. Ensure that the Logger class follows the Singleton pattern, allowing only one instance of the logger to exist, and that the logging operations are thread-safe.
5. Test your program and observe the log messages being written by different threads.
