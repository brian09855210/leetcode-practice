package com.practice;

public class Decipher {

    public static String decipher(String encryptText, String knownWord) {
        String[] words = encryptText.toLowerCase().split(" ");

        int shift = -1;
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-z]", " ");
            if (cleanWord.length() == knownWord.length()) {
                shift = cleanWord.charAt(0) - knownWord.charAt(0);

                // 遍歷每個文字，確認偏移量是否都一樣
                boolean isMatch = true;
                for (int i = 0; i < cleanWord.length(); i++) {
                    int expectedChar = knownWord.charAt(i) + shift;
                    if (expectedChar != cleanWord.charAt(i)) {
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) {
                    break;
                }
            }
        }

        if (shift == -1) {
            return "Invalid";
        }

        StringBuilder sb = new StringBuilder();
        for (char c : encryptText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int decrypted = (c - base - shift + 26) % 26;
                sb.append((char)(base + decrypted));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decipher("Eqfkpi vguvu ctg hwp!", "tests")); // Coding tests are fun!
        System.out.println(decipher("cdeb nqxg", "love")); // abcz love
    }
}
