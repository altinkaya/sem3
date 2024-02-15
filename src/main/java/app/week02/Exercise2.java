package app.week02;

public class Exercise2 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread[] threads = new Thread[2];

        for (int i = 0; i < 2; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.increment();
                    System.out.println(counter.getCount());

                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

    }

    private static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
