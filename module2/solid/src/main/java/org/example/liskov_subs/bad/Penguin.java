package org.example.liskov_subs.bad;

public class Penguin extends Bird{
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}
