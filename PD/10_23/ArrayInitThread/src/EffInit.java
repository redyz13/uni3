public class EffInit extends Thread {
    private final int start;
    private final int dim;
    public static int[] data;
    public static final int SIZE = 10000000;
    public static final int MAX_THR = 8;

    public EffInit(int start, int size) {
        this.start = start;
        this.dim = size;
    }

    @Override
    public void run() {
        int j;
        for (int i = 0; i < dim; i++)
            for (j = 0; j < 10000; j++)
                data[start + i] = i;
    }

    public static void main(String[] args) {
        data = new int[SIZE];
        long begin, end;
        int start, j;
        EffInit[] threads;

        for (int numThread = 1; numThread <= MAX_THR; numThread++) {
            begin = System.currentTimeMillis();
            start = 0;
            threads = new EffInit[numThread];

            for (j = 0; j < numThread; j++) {
                threads[j] = new EffInit(start, SIZE / numThread);
                threads[j].start();
                start += SIZE / numThread;
            }

            for (j = 0; j < numThread; j++) {
                try {
                    threads[j].join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            end = System.currentTimeMillis();
            System.out.println(numThread + " Thread(s): " + (end - begin) + "ms");
        }
    }
}
