# Assignment 5

## Task
Implement a multi-threaded Java program that simulates a shared shopping cart in an e-commerce application. The program should utilize a thread-safe data structure to manage the items added to the cart by multiple customers.

## Requirements:
1. Create a `ShoppingCart` class that represents a shopping cart. It should have the following methods:
- `addItem(String item)`: Adds an item to the cart.
- `removeItem(String item)`: Removes an item from the cart.
- `getItems()`: Retrieves a list of items currently in the cart.

2. Implement a `CustomerThread` class that represents a customer. Each `CustomerThread` object should add and remove items from the shared shopping cart using the `addItem()` and `removeItem()` methods.
3. Create an instance of the `ShoppingCart` class and pass it to multiple `CustomerThread` objects.
4. Ensure that the `ShoppingCart` operations are thread-safe, allowing multiple customers to add and remove items from the cart concurrently without conflicts or data inconsistencies.
5. Test your program by creating multiple customer threads and observing the changes in the shopping cart contents by calling the `getItems()` method.
