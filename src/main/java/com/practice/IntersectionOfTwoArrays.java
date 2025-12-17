package com.practice;

import java.util.Arrays;

public class IntersectionOfTwoArrays {

	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1}, nums2 = {2,2};
		int[] result = intersect(nums1, nums2);
		for(int data : result) {
			System.out.println(data);
		}
	}
	
	public static int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		int i = 0, j = 0, index = 0;
		while(i < nums1.length && j < nums2.length){
			if(nums1[i] == nums2[j]){
				nums1[index++] = nums2[j];
				i++;
				j++;
			} else if(nums1[i] < nums2[j]){
				i++;
			} else{
				j++;
			}
		}
		return Arrays.copyOf(nums1, index);
	}
	
}
