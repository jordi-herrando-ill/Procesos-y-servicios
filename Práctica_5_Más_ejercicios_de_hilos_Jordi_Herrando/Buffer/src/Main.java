public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(4);

        Thread[] producers = new Thread[3];
        Thread[] consumers = new Thread[2];

        for (int i = 0; i < 3; i++) {
            producers[i] = new Thread(new Producer(buffer));
            producers[i].start();
        }

        for (int i = 0; i < 2; i++) {
            consumers[i] = new Thread(new Consumer(buffer));
            consumers[i].start();
        }
    }
}


