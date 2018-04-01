package com.service.advertising;

import com.model.Advertising;
import java.util.List;

public interface AdvertisingService {
    List<Advertising> getAvailableAdvertisingForCurrentUser();
}
