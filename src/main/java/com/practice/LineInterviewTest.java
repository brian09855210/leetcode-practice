package com.practice;

import java.util.Arrays;

public class LineInterviewTest {

    public static int[] solution(int[] numbers, int target) {

//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = i + 1; j < numbers.length; j++) {
//                if (target == numbers[i] + numbers[j]) {
//                    return new int[] { numbers[i], numbers[j] };
//                }
//            }
//        }
//
//        return new int[0];

        Arrays.sort(numbers);
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] { numbers[left], numbers[right] };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] numbers = {1,3,5,6,9};
        int[] result = solution(numbers, 8);
        for (int data : result) {
            System.out.println(data);
        }
    }
}
