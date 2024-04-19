package org.example.mock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private static UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void testAddUser() {
        User user = new User("4", "Olga");

        userRepository.save(user);

        User userFromDb = userRepository.findById(user.getId());

        assertEquals(user, userFromDb);
    }

    @Test
    void testDeleteUser() {
        userRepository.deleteById("1");
        assertNull(userRepository.findById("1"));
    }

    @Test
    void testGetUser() {
        User user = new User("1", "Mykola");

        User userFromDb = userRepository.findById(user.getId());

        assertEquals(user, userFromDb);
    }
}