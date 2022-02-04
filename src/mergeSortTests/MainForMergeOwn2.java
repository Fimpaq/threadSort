package mergeSortTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import threadSort.AnotherSort;

public class MainForMergeOwn2 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			list.add(random.nextInt(100));			
		}
		
		AnotherSort sort = new AnotherSort(list, 2);
		

		for (int num : list) {
			System.out.println(num);
		}

	}
}
