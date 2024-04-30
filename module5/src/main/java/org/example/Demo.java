package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Demo {
    public static List<Integer> factorialSubResults = new ArrayList<>();

    static {
        factorialSubResults.add(1);
        factorialSubResults.add(1);
    }

    public static void main(String[] args) {
        System.out.println(getFactorialValue(10));
        System.out.println(getFactorialValueRecursive(10));
        System.out.println(getFactorialValueDynamicProgramming(10));
        System.out.println(getFactorialValueDynamicProgramming(12));
    }

    public static int getFactorialValue(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int getFactorialValueRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * getFactorialValueRecursive(n - 1);
    }

    public static int getFactorialValueDynamicProgramming(int n) {
        if (factorialSubResults.size() >= n) {
            return factorialSubResults.get(n);
        }
        while (factorialSubResults.size() <= n) {
            int lastComputedIndex = factorialSubResults.size() - 1;
            int currentFactorial = factorialSubResults.get(lastComputedIndex) * factorialSubResults.size();
            factorialSubResults.add(currentFactorial);
        }
        return factorialSubResults.get(n);
    }

    void removeElements(List<DbData> input, List<String> ids) {
        // input size == 10000, ids == 1000
        HashSet<String> idSet = new HashSet<>(ids);
        input.stream()
                .filter(data -> idSet.contains(data.id()))
                .peek(dbData -> System.out.println("delete from db"))
                .toList();

    }


}

record DbData(String id, String value) {

}
