import java.util.Random;
import java.util.concurrent.Semaphore;

class Customer extends Thread {
    private int id;
    private Semaphore entrance;
    private static int remainingUnits = 20;

    public Customer(int id, Semaphore entrance) {
        this.id = id;
        this.entrance = entrance;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                entrance.acquire();
                if (remainingUnits > 0) {
                    System.out.println("Cliente " + id + " entra y compra una PlayStation 5. Quedan " + (--remainingUnits));
                } else {
                    System.out.println("Cliente " + id + " entra pero no hay PlayStation 5 disponibles.");
                    break;
                }
                entrance.release();
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}