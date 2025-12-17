package com.practice;

public class ReverseInteger {

	public static int reverse(int x) {
		
		if(x > ((int)(Math.pow(2, 31) - 1)) || x < ((int)(Math.pow(-2, 31)))) {
			System.out.println(1);
			return 0;
		}
		
		String str = String.valueOf(x);
		String reverseStr = "";
		
		if(str.contains("-")) {
			String num = str.split("-")[1];
			reverseStr = "-" + (new StringBuffer(String.valueOf(num)).reverse()).toString();
		}else {
			reverseStr = (new StringBuffer(String.valueOf(x)).reverse()).toString();
		}
		
		if(Long.valueOf(reverseStr) > ((int)(Math.pow(2, 31) - 1)) || Long.valueOf(reverseStr) < ((int)(Math.pow(-2, 31)))) {
			return 0;
		}
		
		return Integer.parseInt(reverseStr);
    }
	
	public static void main(String[] args) {
		System.out.println(reverse(120));
	}
	
}
