package org.example.palindrome.duplicates;

import java.util.Arrays;
import java.util.HashSet;

public class Demo {

    public static void main(String[] args) {
        int [] intArray = {2, 34, 4,3, 23, 65, 654, 43455, 3324, 2, 3};
        System.out.println(containsDuplicate(intArray));
        System.out.println(containsDuplicateHash(intArray));
        System.out.println(containsDuplicateSort(intArray));
    }

    private static boolean containsDuplicate(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray.length; j++) {
                if ( i != j && intArray[i] == intArray[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containsDuplicateHash(int[] intArray) {
        HashSet<Integer> ints = new HashSet<>();
        for (int i : intArray) {
            ints.add(i);
        }
        return intArray.length != ints.size();
    }

    private static boolean containsDuplicateSort(int[] intArray) {
        Arrays.sort(intArray);
        for (int i = 0; i < intArray.length - 1; i++) {
            if (intArray[i] == intArray[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
