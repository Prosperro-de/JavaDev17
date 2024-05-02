package org.example.palindrome;

public class Demo {


    public static void main(String[] args) {

        System.out.println(isPalindrome("abba"));
        System.out.println(isPalindrome("abfba"));
        System.out.println(isPalindrome("abafba"));

        System.out.println(isPalindromeTwoPointers("abba"));
        System.out.println(isPalindromeTwoPointers("abfba"));
        System.out.println(isPalindromeTwoPointers("abafba"));

    }

    public static boolean isPalindrome(String input) {
        String lowerCaseInput = input.toLowerCase();
        char[] charArray = lowerCaseInput.toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            if (charArray[i] != charArray[charArray.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeTwoPointers(String input) {
        String lowerCaseInput = input.toLowerCase();
        int left = 0;
        int right = lowerCaseInput.length() - 1;

        while (left < right) {
            if (lowerCaseInput.charAt(left) != lowerCaseInput.charAt(right)) {
               return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
