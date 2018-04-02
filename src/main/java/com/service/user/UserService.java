package com.service.user;

import com.model.User;
import java.util.Map;

public interface UserService {
    User getCurrentUser();
    Map<String, Object> create(User user);
}
