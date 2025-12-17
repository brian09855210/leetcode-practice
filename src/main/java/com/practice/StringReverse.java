package com.practice;

public class StringReverse {

	public static void main(String[] args) {
		String s = "a good   example";
		System.out.println(reverseSentence(s));
	}
	
	public static String reverseSentence(String s) {
		String[] words = s.trim().split(" ");
		StringBuffer sentence = new StringBuffer();
		for(int i = words.length - 1; i >= 0; i--) {
			if(words[i].length() > 0) {
				sentence.append(words[i]);
				if(i > 0) {
					sentence.append(" ");
				}
			}
		}
		return sentence.toString();
	}
	
}
