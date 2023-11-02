import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Semaphore[] forks = new Semaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            Philosopher philosopher = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            philosopher.start();
        }
    }
}
