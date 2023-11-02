import java.util.Random;
public class Buffer {
        private int[] data;
        private int size;
        private int in;
        private int out;

        public Buffer(int size) {
            this.size = size;
            data = new int[size];
            in = 0;
            out = 0;
        }

        public synchronized void produce(int item) throws InterruptedException {
            while ((in + 1) % size == out) {
                System.out.println("Buffer lleno, el productor espera...");
                wait();
            }
            data[in] = item;
            in = (in + 1) % size;
            System.out.println("Productor produce: " + item);
            notify();
        }

        public synchronized int consume() throws InterruptedException {
            while (in == out) {
                System.out.println("Buffer vacío, el consumidor espera...");
                wait();
            }
            int item = data[out];
            out = (out + 1) % size;
            System.out.println("Consumidor consume: " + item);
            notify();
            return item;
        }
    }

    class Producer implements Runnable {
        private Buffer buffer;

        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 1; i <= 30; i++) {
                try {
                    buffer.produce(i);
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    class Consumer implements Runnable {
        private Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 1; i <= 15; i++) {
                try {
                    int item = buffer.consume();
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }