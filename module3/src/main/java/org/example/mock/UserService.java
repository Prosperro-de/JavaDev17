package org.example.mock;


public class UserService {
    private UserRepository userRepository;

    public UserService() {
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        userRepository.save(user);
    }

    public User getUser(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return userRepository.findById(id);
    }

    public void deleteUser(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        userRepository.deleteById(id);
    }
}
