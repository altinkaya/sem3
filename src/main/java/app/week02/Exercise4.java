package app.week02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise4 {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        for (int i = 0; i < cores; i++) {
            executorService.submit(new CpuIntensiveTask());
        }

        executorService.shutdown();
    }

    static class CpuIntensiveTask implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
