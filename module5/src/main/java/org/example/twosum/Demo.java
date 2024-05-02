package org.example.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 18;
        int[] result = getTwoSum(numbers, target);
        System.out.println(Arrays.toString(result));
        int[] secondResult = getTwoSumHashTable(numbers, target);
        System.out.println(Arrays.toString(secondResult));
    }

    private static int[] getTwoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] + numbers[j] == target) {
                    result[0] = j;
                    result[1] = i;
                }
            }
        }
        return result;
    }

    private static int[] getTwoSumHashTable(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int diff = target - numbers[i];
            if (map.containsKey(diff)) {
                Integer secondIndex = map.get(diff);
                result[0] = i;
                result[1] = secondIndex;
                return result;
             }
        }
        return result;
    }
}
