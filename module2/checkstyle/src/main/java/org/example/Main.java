package org.example;

public class Main {

    private final int magic_number = 5;

    public void doSomething() {

        if (magic_number > 10)
            System.out.println("Magic number is greater than 10");

        for(int i = 0; i < 7; i++) {
            System.out.println("Loop index: " + i);
        }
    }

    public void BadMethodName(){
        System.out.println("Bad method name.");
    }
}