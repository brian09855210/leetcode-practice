package com.practice;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("");
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("M", 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            String word = String.valueOf(s.charAt(i));
            String nextWord = "";
            if ((i+1) < s.length()) {
                nextWord = word + s.charAt(i+1);
            }

            if (map.containsKey(word)) {
                if (map.containsKey(nextWord)) {
                    result += map.get(nextWord);
                    i++;
                    continue;
                }
                result += map.get(word);
            }
        }

        return result;
    }

//    public static int char2num(char a) {
//        switch (a) {
//            case 'I': return 1;
//            case 'V': return 5;
//            case 'X': return 10;
//            case 'L': return 50;
//            case 'C': return 100;
//            case 'D': return 500;
//            case 'M': return 1000;
//            default: return 0;
//        }
//    }
//
//    public static int romanToInt(String s) {
//        int result = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (i + 1 < s.length() && char2num(s.charAt(i)) < char2num(s.charAt(i + 1))) {
//                result -= char2num(s.charAt(i));
//            } else {
//                result += char2num(s.charAt(i));
//            }
//        }
//        return result;
//    }
}
