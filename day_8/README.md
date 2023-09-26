# Assignments 8

## Task
* Write a Java program to create a ThreadLocal object to store a user's login session data.
* The program should create multiple threads and each thread should set and get its own login session data using the ThreadLocal object.
* Finally, the program should print the login session data for each thread to verify that the ThreadLocal object is working as expected.

## Hints
- Use a HashMap to store the login session data for each thread.
- Use the ThreadLocal class to create a separate HashMap for each thread.
- Use the Thread class to create and run multiple threads.
- Use the get() and set() methods of the ThreadLocal class to get and set the login session data for each thread.
