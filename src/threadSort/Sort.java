package threadSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Sort {

	private List<Integer> list;
	private long start;
	private long time;
	
	public Sort(final List<Integer> list) {
		start = System.currentTimeMillis();
		StatusPanel.setStatus("Sorting List ...");
		StatusPanel.setTime(timeToReadableString(0));
		
		StatusPanel.pb.setMaximum(list.size());
		StatusPanel.pb.setValue(0);
		StatusPanel.pb.setIndeterminate(true);
		
		this.list = list;
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(SortPanel.threadAmount);
		forkJoinPool.invoke(new MultiSort(this.list, 0, this.list.size() - 1));

		umkopieren();
		
		time = System.currentTimeMillis() - start;
		StatusPanel.setTime(timeToReadableString(time));
		StatusPanel.setStatus("Sorting done");
		StatusPanel.pb.setIndeterminate(false);
		StatusPanel.pb.setValue(StatusPanel.pb.getMaximum());
	}

	private void umkopieren() {
		Work.list = this.list;
		CanvasComponent.listModel.removeAllElements();
		CanvasComponent.listModel.addAll(this.list);
	}
	
	private String timeToReadableString(final long time) {
		if(time > 1000) {
			return (double)time / 1000 + "s";
		} else {
			return time + "ms";
		}
	}

	@SuppressWarnings("serial")
	private static class MultiSort extends RecursiveAction {

		private List<Integer> list;
		private int startIndex;
		private int endIndex;		

		public MultiSort(final List<Integer> list, final int start, final int end) {
			this.list = list;
			this.startIndex = start;
			this.endIndex = end;
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
}
