package mergeSortTests;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

// outer class
public class MergeOwn2 {
    private final ForkJoinPool pool;
    public List<Integer> list;
    
    public MergeOwn2(int parallelism, List<Integer> list) {
    	this.pool = new ForkJoinPool(parallelism);
    	this.list = list;
    	sort();
    }
    
    public void sort() {
    	ForkJoinTask<Void> forkJoinTask = pool.submit(new MergeSortTask(this.list, 0, this.list.size()));
    	forkJoinTask.join();
    }
    
    // static inner class
    @SuppressWarnings("serial")
	private static class MergeSortTask extends RecursiveAction {
        private final List<Integer> list;
        private final int startIdx;
        private final int endIdx;

        protected MergeSortTask(List<Integer> list, int startIdx, int endIdx) {
            this.list = list;
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

        @Override
        protected void compute() {
            if (startIdx < endIdx && (endIdx - startIdx) >= 1) {

                int mid = (startIdx + endIdx) / 2;

                invokeAll(new MergeSortTask(list, startIdx, mid), new MergeSortTask(list, mid + 1, endIdx));

                merger(startIdx, mid, endIdx, list);
            }
        }

        private static void merger(final int startIndex, final int midIndex, final int endIndex, final List<Integer> list) {
    		List<Integer> mergedSortedArray = new ArrayList<>();

    		int leftIndex = startIndex;
    		int rightIndex = midIndex + 1;

    		while (leftIndex <= midIndex && rightIndex <= endIndex) {
    			if (list.get(leftIndex) <= list.get(rightIndex)) {
    				mergedSortedArray.add(list.get(leftIndex));
    				leftIndex++;
    			} else {
    				mergedSortedArray.add(list.get(rightIndex));
    				rightIndex++;
    			}
    		}

    		while (leftIndex <= midIndex) {
    			mergedSortedArray.add(list.get(leftIndex));
    			leftIndex++;

    		}

    		while (rightIndex <= endIndex) {
    			mergedSortedArray.add(list.get(rightIndex));
    			rightIndex++;

    		}

    		int i = 0;
    		int j = startIndex;

    		while (i < mergedSortedArray.size()) {
    			list.set(j, mergedSortedArray.get(i++));
    			j++;
    		}
    	}
    }
}