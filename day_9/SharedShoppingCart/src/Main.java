// program to create a shared shopping cart

// import packages
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// creates and manages the shared shopping cart
class ShoppingCart {
    private static CopyOnWriteArrayList cart;

    public ShoppingCart() {
        this.cart = new CopyOnWriteArrayList();
    }

    public void addItem(String item) {
        if(this.cart.contains(item)) {
            System.out.println(Thread.currentThread().getName() + ": item '" + item + "' already in the cart");
            return;
        }
        this.cart.add(item);
    }

    public void removeItem(String item) {
        if(!this.cart.contains(item)){
            System.out.println(Thread.currentThread().getName() + ": item '" + item + "' not present in the cart");
            return;
        }
        this.cart.remove(item);
    }

    public List<String> getItems() {
        return this.cart;
    }
}

// Manages thread run and item streaming
class CustomerThread extends Thread {
    ShoppingCart cart;
    ArrayList<String> itemList;

    public CustomerThread(String name, ShoppingCart cart, ArrayList<String> itemList) {

        super(name);
        this.cart = cart;
        this.itemList = itemList;
    }

    @Override
    public void run() {
        String customerName = Thread.currentThread().getName();
        // add items to shopping cart
        int i;
        for(i = 0; i < this.itemList.size(); i++) {
            try{
                Thread.sleep(400);
            } catch(InterruptedException e) {
                System.err.println("Thread got interrupted while running: " + e.getMessage());
            }
            cart.addItem(this.itemList.get(i));
        }
        // remove items from shopping cart
        if(i!=1){
            cart.removeItem(itemList.get(i/2));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // create an instance of ShoppingCart
        ShoppingCart cart = new ShoppingCart();

        // create threads
        ArrayList list1 = new ArrayList<>(Arrays.asList("milk", "rice", "mango"));
        CustomerThread user1 = new CustomerThread("himanshu", cart, list1);
        ArrayList list2 = new ArrayList<>(Arrays.asList("bread", "egg", "tomato", "biscuit", "noodles"));
        CustomerThread user2 = new CustomerThread("yogesh", cart, list2);
        ArrayList list3 = new ArrayList<>(Arrays.asList("yogurt"));
        CustomerThread user3 = new CustomerThread("sandhya", cart, list3);
        ArrayList list4 = new ArrayList<>(Arrays.asList("book", "blue pen", "sketch book"));
        CustomerThread user4 = new CustomerThread("prajwal", cart, list4);
        ArrayList list5 = new ArrayList<>(Arrays.asList("milk", "flour", "banana", "backing powder", "red chilli", "salt", "cabbage"));
        CustomerThread user5 = new CustomerThread("sagar", cart, list5);
        ArrayList list6 = new ArrayList<>(Arrays.asList("cucumber", "butter", "cheese"));
        CustomerThread user6 = new CustomerThread("vivek", cart, list6);

        // start the threads
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
        user6.start();

        // Check the threads
        int count = 20;
        while(count > 0) {
            try{
                Thread.sleep(150);
            } catch(InterruptedException e) {
                System.err.println("Thread got interrupted while running: " + e.getMessage());
            }
            System.out.println(cart.getItems());
            count -= 1;
        }
    }
}