import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private int id;
    private Semaphore leftFork;
    private Semaphore rightFork;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((long) (Math.random() * 4000) + 1000);
    }

    private void eat() throws InterruptedException {
        leftFork.acquire();
        rightFork.acquire();

        System.out.println("Filósofo " + id + " está comiendo.");

        Thread.sleep((long) (Math.random() * 1000) + 1000);

        leftFork.release();
        rightFork.release();
    }
}
