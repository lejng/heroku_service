package com.service.user;

import com.model.Advertising;
import com.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User getCurrentUser();
    ResponseEntity create(User user);
    List<Advertising> getAvailableAdvertisingForCurrentUser();
}
