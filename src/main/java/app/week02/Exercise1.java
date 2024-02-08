package app.week02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String str = String.valueOf(letter);
            str = str + str + str;
            executorService.submit(new PrintTask(str));
        }

        executorService.shutdown();
    }

    static class PrintTask implements Runnable {
        private final String str;

        PrintTask(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println(str);
        }
    }
}
