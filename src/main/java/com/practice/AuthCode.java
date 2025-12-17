package com.practice;

public class AuthCode {
	
	public void getAuthCode() {
		
		int[] x = new int[8];
		System.out.println("本次隨機產生驗證碼為：　");
		
		for (int i = 0; i < x.length; i++) {
			while (true) {
				int num = (int) (Math.random() * 75 + 48); // char 48-57 數字 65-90 大寫字母 97-122 小寫字母
				if ((num > 47 && num < 58) || (num > 64 && num < 91) || (num > 96 && num < 123)) {
					x[i] = num;
					System.out.print((char) x[i]);
					break;
				}
			}
		}
		
	}

	public static void main(String[] args) {
		AuthCode hw = new AuthCode();
		hw.getAuthCode();
	}
}
