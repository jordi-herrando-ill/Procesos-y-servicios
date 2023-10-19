import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static class ProductorConsumidor {
        public static void main(String[] args) {
            BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(10);

            Thread productor = new Thread(() -> {
                try {
                    for (int i = 1; i <= 10; i++) {
                        buffer.put(i);
                        System.out.println("Productor produjo: " + i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread consumidor = new Thread(() -> {
                try {
                    for (int i = 1; i <= 10; i++) {
                        int elemento = buffer.take();
                        System.out.println("Consumidor consumió: " + elemento);
                        Thread.sleep(1500);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            productor.start();
            consumidor.start();
        }
    }
}
