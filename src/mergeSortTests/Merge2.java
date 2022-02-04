package mergeSortTests;
/*
 * 
 * 
 * DEPRECATED
 * 
 * 
 */


import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Merge2 {
    private final ForkJoinPool pool;

    @SuppressWarnings("serial")
	private static class MergeSortTask extends RecursiveAction {
        private final int[] array;
        private final int low;
        private final int high;
        private static final int THRESHOLD = 8;

        protected MergeSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Arrays.sort(array, low, high);
            } else {
                int middle = low + ((high - low) >> 1);

                invokeAll(new MergeSortTask(array, low, middle), new MergeSortTask(array, middle, high));

                merge(middle);
            }
        }

        private void merge(int middle) {
            if (array[middle - 1] < array[middle]) {
                return;
            }
            int[] copy = new int[high - low];
            System.arraycopy(array, low, copy, 0, copy.length);
            int copyLow = 0;
            int copyHigh = high - low;
            int copyMiddle = middle - low;

            for (int i = low, p = copyLow, q = copyMiddle; i < high; i++) {
                if (q >= copyHigh || (p < copyMiddle && copy[p] < copy[q]) ) {
                    array[i] = copy[p++];
                } else {
                    array[i] = copy[q++];
                }
            }
        }
    }

    public Merge2(int parallelism) {
        pool = new ForkJoinPool(parallelism);
    }

    public void sort(int[] array) {
        ForkJoinTask<Void> job = pool.submit(new MergeSortTask(array, 0, array.length));
        job.join();
    }
}