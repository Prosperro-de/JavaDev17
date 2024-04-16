package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private static UserService userService;

    @BeforeAll
    static  void setUp() {
        userService = new UserService();
    }

    @Test
    @Order(1)
    void testAddUser() {
        User user = new User("4", "Olga");

        userService.addUser(user);

        User userFromDb = userService.getUser(user.getId());

        assertEquals(user, userFromDb);
    }

    @Test
    @Order(2)
    void testDeleteUser() {
        userService.deleteUser("1");
        assertThrows(IllegalArgumentException.class, () -> userService.getUser("1"));
    }

    @Test
    @Order(3)
    void testGetUser() {
        User user = new User("1", "Mykola");

        //fails because of using a common resource
        User userFromDb = userService.getUser(user.getId());

        assertEquals(user, userFromDb);
    }
}