package org.example.mock;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> userMap;

    public UserRepository() {
        userMap = new HashMap<>();
        userMap.put("1", new User("1", "Mykola"));
        userMap.put("2", new User("2", "Olena"));
        userMap.put("3", new User("3", "Oleg"));
    }

    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    public User findById(String id) {
        return userMap.get(id);
    }

    public void deleteById(String id) {
        userMap.remove(id);
    }
}
