package com.practice;

public class SumOfUniqueElements {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 2};
		System.out.println(sumOfUnique(nums));
	}
	
	public static int sumOfUnique(int[] nums) {
		int[] hash = new int[101];
        int sum = 0;
        for (int i = 0; i< nums.length; i++){
            hash[nums[i]] = hash[nums[i]]+1;
        }
        for (int i = 0; i < hash.length; i++){
            if (hash[i] == 1) sum+=i;
        }
        return sum;
    }
	
}
