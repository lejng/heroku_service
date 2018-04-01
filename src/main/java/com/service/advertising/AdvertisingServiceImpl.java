package com.service.advertising;

import com.dao.AdvertisingDao;
import com.model.Advertising;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdvertisingServiceImpl implements AdvertisingService {
    @Autowired
    private AdvertisingDao advertisingDao;

    @Autowired
    private UserService userService;

    @Override
    public List<Advertising> getAvailableAdvertisingForCurrentUser() {
        Integer userId = userService.getCurrentUser().getId();
        return advertisingDao.getAvailableAdvertising(userId);
    }
}
