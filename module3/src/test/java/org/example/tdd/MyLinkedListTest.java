package org.example.tdd;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyLinkedListTest {
    private MyLinkedList<String> myLinkedList;

    @Test
    void addElementToListIncreaseSizeTest() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Hello");

        assertEquals(1, myLinkedList.size());
    }

    @Test
    void getElementForFirstIndexReturnsElementTest() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Hello");

        String expectedResult = "Hello";

        String result = myLinkedList.get(0);

        assertEquals(expectedResult, result);
    }

    @Test
    void getElementForLastIndexReturnsElementTest() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Hello");
        myLinkedList.add("World");

        String expectedResult = "World";

        String result = myLinkedList.get(myLinkedList.size() - 1);

        assertEquals(expectedResult, result);
        assertEquals(2, myLinkedList.size());
    }

    @Test
    void getElementForMiddleIndexReturnsElementTest() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Hello");
        myLinkedList.add("World");
        myLinkedList.add("!");

        String expectedResult = "World";

        String result = myLinkedList.get(myLinkedList.size() / 2);

        assertEquals(expectedResult, result);
        assertEquals(3, myLinkedList.size());
    }

    @Test
    void getElementForNegativeIndexFailsTest() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Hello");

        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.get(-1));
    }

    @Test
    void getElementForIndexGreaterThanSizeFailsTest() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Hello");

        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.get(6));
    }
}
