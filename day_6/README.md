# Assignment 6

## Task
You are tasked with implementing a program to update the balance of a bank account using multithreading and locks. The program should ensure that concurrent updates to the account balance are synchronized and executed correctly.


## Approach
- Create a BankAccount class with a balance variable and methods to perform deposit and withdrawal transactions.
- Implement a Transaction class that represents a single transaction operation on the bank account. It should have a type (deposit or withdrawal) and an amount.
- Create multiple threads that perform transactions on the bank account concurrently.
- Use locks to synchronize the critical section of code that updates the account balance.
- Ensure that simultaneous transactions do not result in race conditions or inconsistent balance updates.
- Your task is to implement the necessary classes and synchronization mechanisms to ensure correct and thread-safe execution of the bank account transactions.
