package org.book.study.caching;

public class Caching {

    private final int ARR_SIZE = 2 * 1024 * 1024;
    private final int[] testData = new int[ARR_SIZE];

    private void run() {
        System.out.println("Start: " + System.currentTimeMillis());

        for(int i=0; i < 15_000; i++) {
            touchEveryLine();
            touchEveryItem();
        }

        System.err.println("Warmup Finished : " + System.currentTimeMillis());
        System.err.println("Item Line");
        for(int i=0; i < 100; i++) {
            long t0 = System.nanoTime();
            touchEveryLine();
            long t1 = System.nanoTime();
            touchEveryItem();
            long t2 = System.nanoTime();
            long elItem = t2 - t1;
            long elLine = t1 - t0;
            double diff = elItem - elLine;
            System.err.println(elItem + " "  + elLine + " " + (100 * diff / elLine));
        }
    }

    private void touchEveryItem() {
        for (int i=0; i < testData.length; i++) {
            testData[i]++;
        }
    }

    private void touchEveryLine() {
        for (int i=0; i < testData.length; i += 16) {
            testData[i]++;
        }
    }

    public static void main(String[] args) {
        Caching c = new Caching();
        c.run();
    }
}
