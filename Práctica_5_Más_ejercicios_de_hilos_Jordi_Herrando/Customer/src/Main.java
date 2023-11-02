import java.util.concurrent.Semaphore;
public class Main {
    public static void main(String[] args) {
        int numCustomers = 200;
        Semaphore entrance = new Semaphore(1);

        Thread[] customers = new Thread[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            customers[i] = new Customer(i, entrance);
            customers[i].start();
        }
    }
}