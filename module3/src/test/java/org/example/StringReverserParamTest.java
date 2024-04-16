package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringReverserParamTest {

    private StringReverser stringReverser;

    @BeforeEach
    void setUp() {
        //given
        stringReverser = new StringReverser();
    }

    @AfterEach
    void cleanUp() {
        stringReverser = null;
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("getValidInputParams")
    void reverseStringSuccessfullyTest(String name, String input, String expected) {
        //given


        //when
        String result = stringReverser.reverse(input);

        //then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("getInvalidInputParams")
    void reverseStringWithInvalidInputTest(String input) {
        //given

        //then
        assertThrows(IllegalArgumentException.class,
                () -> stringReverser.reverse(input));
    }

    private static Stream<Arguments> getValidInputParams() {
        return Stream.of(
                Arguments.of("Valid sentence with whitespaces",
                        "Hello World",
                        "olleH dlroW"),
                Arguments.of("Input with whitespaces only",
                        "     ",
                        ""));
    }

    private static Stream<Arguments> getInvalidInputParams() {
        return Stream.of(Arguments.of(""), Arguments.of((Object) null));
    }


//    void giveAString_reverse_reversedString() {
//
//    }


}