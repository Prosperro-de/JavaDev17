package org.example.mock;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
//        userRepository = Mockito.mock(UserRepository.class);
//        userService = new UserService(userRepository);
    }

    @Test
    void saveUserSuccessfulTest() {
        User expectedUser = User.builder()
                .id("1")
                .name("Oleksandr")
                .build();

        assertDoesNotThrow(() -> userService.addUser(expectedUser));
        verify(userRepository).save(expectedUser);

        when(userRepository.findById("1")).thenReturn(expectedUser);
//        when(userRepository.findById("1")).thenCallRealMethod();
//
        User userFromDb = userService.getUser("1");
//
        assertEquals(expectedUser, userFromDb);

    }

    @Test
    void saveNullObjectFailsTest() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(null));
    }

}