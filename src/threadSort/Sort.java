//package threadSort;
//
//import list.MyArrayList;
//import list.MyList;
//
//public class Sort {
//
//	public static <T extends Comparable<T>> void mergeSort(final MyList<T> list) {
//		divide(0, list.getSize() - 1, list);
//	}
//
//	private static <T extends Comparable<T>> MyList<T> divide(final int startIndex, final int endIndex, final MyList<T> list) {
//		if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
//			int mid = (endIndex + startIndex) / 2;
//			divide(startIndex, mid, list);
//			divide(mid + 1, endIndex, list);
//
//			merger(startIndex, mid, endIndex, list);
//		}
//		return list;
//	}
//
//	private static <T extends Comparable<T>> void merger(final int startIndex, final int midIndex, final int endIndex, final MyList<T> list) {
//		MyList<T> mergedSortedArray = new MyArrayList<>();
//
//		int leftIndex = startIndex;
//		int rightIndex = midIndex + 1;
//
//		while (leftIndex <= midIndex && rightIndex <= endIndex) {
////			if ((list.get(leftIndex)).compareTo(list.get(rightIndex)) <= 0) { // if (list.get(leftIndex) <= list.get(rightIndex)) {
//			if (doCompare(list.get(leftIndex), list.get(rightIndex), null) <= 0) {
//				mergedSortedArray.add(list.get(leftIndex));
//				leftIndex++;
//			} else {
//				mergedSortedArray.add(list.get(rightIndex));
//				rightIndex++;
//			}
//		}
//
//		while (leftIndex <= midIndex) {
//			mergedSortedArray.add(list.get(leftIndex));
//			leftIndex++;
//		}
//
//		while (rightIndex <= endIndex) {
//			mergedSortedArray.add(list.get(rightIndex));
//			rightIndex++;
//		}
//
//		int i = 0;
//		int j = startIndex;
//
//		while (i < mergedSortedArray.getSize()) {
//			list.set(mergedSortedArray.get(i++), j);
//			j++;
//		}
//	}
//	
//}
