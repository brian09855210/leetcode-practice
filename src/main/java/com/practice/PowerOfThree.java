package com.practice;

public class PowerOfThree {

	public static void main(String[] args) {
		System.out.println(isPowerOfThree(27));
	}
	
	public static boolean isPowerOfThree(int n) {
		for(int i = 0; i < 31; i++){
            if(n == Math.pow(3, i)){
                return true;
            }
        }
		return false;
	}
	
}
