package threadSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultiSort extends RecursiveAction{

	private List<Integer> list;
	private int startIndex;
	private int endIndex;
	
	public MultiSort(List<Integer> list, int start, int end) {
		this.list = list;
		this.startIndex = start;
		this.endIndex = end;
	}
	
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<>();
		l.add(5);
		l.add(55);
		l.add(100);
		l.add(8);
		l.add(12);
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();;
		forkJoinPool.invoke(new MultiSort(l, 0, l.size() - 1));
		
		for(int num : l) {
			System.out.println(num);
		}
	}

	@Override
	protected void compute() {
		if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
			int mid = (endIndex + startIndex) / 2;

			invokeAll(new MultiSort(list, startIndex, mid), new MultiSort(list, mid + 1, endIndex));
			merger(startIndex, mid, endIndex, list);
		}
	}

	private void merger(final int startIndex, final int midIndex, final int endIndex, final List<Integer> list) {
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
