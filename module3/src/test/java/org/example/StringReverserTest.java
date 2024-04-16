package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringReverserTest {

    private StringReverser stringReverser;

    @Test
    void reverseStringSuccessfullyTest() {
        //given
        stringReverser = new StringReverser();
        String input = "Hello World";
        String expected = "olleH dlroW";

        //when
        String result = stringReverser.reverse(input);

        //then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void reverseStringWithOnlyTest() {
        //given
        stringReverser = new StringReverser();
        String input = "   ab ";
        String expected = "ba";

        //when
        String result = stringReverser.reverse(input);

        //then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void reverseStringWithEmptyInputTest() {
        //given
        stringReverser = new StringReverser();
        String input = "";

        //then
        assertThrows(IllegalArgumentException.class,
                () -> stringReverser.reverse(input));
    }

    @Test
    void reverseStringWithNullInputTest() {
        //given
        stringReverser = new StringReverser();

        //then
        assertThrows(IllegalArgumentException.class,
                () -> stringReverser.reverse(null));
    }


//    void giveAString_reverse_reversedString() {
//
//    }


}