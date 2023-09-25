import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class FactorialTask implements Callable {
    private final String name;
    private final int number;
    public FactorialTask(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public List<Integer> call() throws Exception {
        return Main.factorial(this.number);
    }
}

public class Main {
    static List<Integer> factorial(int n)
    {
        int[] res = new int[500];

        // Initialize result
        res[0] = 1;
        int res_size = 1;

        // Apply simple factorial formula
        // n! = 1 * 2 * 3 * 4...*n
        for (int x = 2; x <= n; x++)
            res_size = multiply(x, res, res_size);

        List<Integer> resList = new ArrayList<>();
        for (int i = res_size - 1; i >= 0; i--)
            resList.add(res[i]);
        return resList;
    }
    static int multiply(int x, int[] res, int res_size)
    {
        int carry = 0; // Initialize carry

        // One by one multiply n with individual
        // digits of res[]
        for (int i = 0; i < res_size; i++) {
            int prod = res[i] * x + carry;
            res[i] = prod % 10; // Store last digit of
            // 'prod' in res[]
            carry = prod / 10; // Put rest in carry
        }

        // Put carry in res and increase result size
        while (carry != 0) {
            res[res_size] = carry % 10;
            carry = carry / 10;
            res_size+=1;
        }
        //System.out.println("res_size: " + res_size);
        return res_size;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("----- factorial calculation -----");
        int number = 100;
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<List<Integer>> future = executorService.submit(new FactorialTask("Long Task", number));
        System.out.println("Computing factorial ...");
        List<Integer> result = future.get();
        System.out.println("Factorial of given number is ");
        for (int i: result)
            System.out.print(i);
    }
}

