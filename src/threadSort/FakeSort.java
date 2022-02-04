package threadSort;

/*
 * legacy, deprecated, unused
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FakeSort {

	private List<Integer> l;
	@SuppressWarnings("unused")
	private final ExecutorService mergeExec;	
	
	private long start;
	private long time;
	
	public FakeSort(final List<Integer> l, final int threadAmount) {
		this.l = l;
		mergeExec = Executors.newFixedThreadPool(threadAmount); // test
		StatusPanel.pb.setMaximum(l.size()*16);
		StatusPanel.pb.setValue(0);

		
		Runnable runn = new Runnable() {
			@Override
			public void run() {
				start = System.currentTimeMillis();
				StatusPanel.setStatus(mergeSort());
			}			
		};		
		Main.executor.execute(runn);
		
	}
	
	private String timeToReadableString(final long time) {
		if(time > 1000) {
			return (double)time / 1000 + "s";
		} else {
			return time + "ms";
		}
	}

	
	private String mergeSort() {
		divide(0, this.l.size() - 1, l);
		umkopieren();
		return "Finished Sorting";
	}

	private void umkopieren() {
		Work.list = this.l;
		CanvasComponent.listModel.removeAllElements();
		CanvasComponent.listModel.addAll(l);
		System.err.println("funktioniert");
	}

	private List<Integer> divide(int startIndex, int endIndex, List<Integer> list) {		
		if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
			int mid = (endIndex + startIndex) / 2;

					divide(startIndex, mid, list);

					divide(mid + 1, endIndex, list);				

					merger(startIndex, mid, endIndex, list);

		}
		return list;
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
			time = System.currentTimeMillis() - start;
			StatusPanel.setTime(timeToReadableString(time));
			StatusPanel.pb.setValue(StatusPanel.pb.getValue() + 1);
		}
	}

}
