package threadSort;

import java.util.ArrayList;
import java.util.List;

public class FakeSort {

	List<Integer> l;

	public FakeSort(final List<Integer> l) {
		this.l = l;
		mergeSort();
	}

	public void umkopieren() {
		Generate.list = this.l;
		CanvasComponent.listModel.removeAllElements();
		CanvasComponent.listModel.addAll(l);
		System.out.println("funktioniert");
	}

	public void mergeSort() {
		divide(0, this.l.size() - 1, l);
		umkopieren();
	}

	private static List<Integer> divide(int startIndex, int endIndex, List<Integer> list) {
		if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
			int mid = (endIndex + startIndex) / 2;
			divide(startIndex, mid, list);
			divide(mid + 1, endIndex, list);

			merger(startIndex, mid, endIndex, list);
		}
		return list;
	}

	private static void merger(final int startIndex, final int midIndex, final int endIndex, final List<Integer> list) {
		List<Integer> mergedSortedArray = new ArrayList<>();

		int leftIndex = startIndex;
		int rightIndex = midIndex + 1;

		while (leftIndex <= midIndex && rightIndex <= endIndex) {
//			if ((list.get(leftIndex)).compareTo(list.get(rightIndex)) <= 0) { // if (list.get(leftIndex) <= list.get(rightIndex)) {
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
