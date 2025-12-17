package com.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	public static void main(String[] args) {
		int[] numbers = {-1,0};
		int[] result = twoSum(numbers, -1);
		Arrays.stream(result).forEach(System.out::println);
	}
	
//	public static int[] twoSum(int[] numbers, int target) {
//		int[] result = new int[2];
//		for(int i = 0; i < numbers.length; i++) {
//			for(int j = i + 1; j < numbers.length; j++) {
//				if(target == numbers[i] + numbers[j]) {
//					result[0] = i + 1;
//					result[1] = j + 1;
//				}
//			}
//		}
//		return result;
//    }

	public static int[] twoSum(int[] numbers, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < numbers.length; i++) {
			int complement = target - numbers[i];

			if (map.containsKey(complement)) {
				return new int[]{map.get(complement), i};
			}

			map.put(numbers[i], i);
		}

		throw new IllegalArgumentException("");
	}
	
}
