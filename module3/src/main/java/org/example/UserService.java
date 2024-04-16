package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap;

    public UserService() {
        userMap = new HashMap<>();
        userMap.put("1", new User("1", "Mykola"));
        userMap.put("2", new User("2", "Olena"));
        userMap.put("3", new User("3", "Oleg"));
    }

    public void addUser(User user) {
        if (user == null || userMap.containsKey(user.getId())) {
            throw new IllegalArgumentException();
        }
        userMap.put(user.getId(), user);
    }

    public User getUser(String id) {
        if (!userMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return userMap.get(id);
    }

    public void deleteUser(String id) {
        if (!userMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        userMap.remove(id);
    }
}
