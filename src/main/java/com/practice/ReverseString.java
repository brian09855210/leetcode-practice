package com.practice;

public class ReverseString {

	public static void main(String[] args) {
		char[] s = {'h','e','l','l','o'};
	}
	
	public static void reverseString(char[] s) {
		int start = 0, end = s.length -1;
        char t;
        while(start < end){
            t = s[start];
            s[start] = s[end];
            s[end] = t;
            start++;
            end--;
        }
    }
	
}
