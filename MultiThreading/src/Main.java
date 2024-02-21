import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int count = 0;
        while (++count < 100) {
            new ExeutorThread(getRandomNumber()).start();
        }
    }

    static class ExeutorThread extends Thread {
        private long num = 0L;
        public ExeutorThread(long num) {
            this.num = num;
        }

        @Override
        public void run() {
            MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
            minMaxMetrics.addSample(num);
            System.out.println("Get max value : " + minMaxMetrics.getMax());
            System.out.println("Get min value : " + minMaxMetrics.getMin());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static long getRandomNumber() {
        Random random = new Random();

        long min = 1000L;
        long max = 10000000L;

        // Calculate the range size
        long range = max - min;

        // Generate a random number within the range and add the minimum value to it
        long randomNum = min + (long) (random.nextDouble() * range);

        System.out.println("Random Number: " + randomNum);
        return randomNum;
    }
}