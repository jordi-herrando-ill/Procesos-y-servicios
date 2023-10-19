
import java.util.concurrent.atomic.AtomicInteger;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static class ContadorConAtomicInteger {
        private static AtomicInteger contador = new AtomicInteger(0);

        public static void main(String[] args) {
            Thread[] threads = new Thread[4];

            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 500; j++) {
                        contador.getAndIncrement();
                    }
                });
                threads[i].start();
            }

            for (int i = 0; i < 4; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Resultado con AtomicInteger: " + contador);
        }
    }
}