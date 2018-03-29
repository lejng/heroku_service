package com.service.user;

import com.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User getCurrentUser();
    ResponseEntity create(User user);
}
