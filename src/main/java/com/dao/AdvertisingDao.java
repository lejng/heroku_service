package com.dao;

import com.model.Advertising;
import org.springframework.stereotype.Service;

@Service
public class AdvertisingDao extends AbstractDao<Advertising> {
    public AdvertisingDao() {
        super(Advertising.class);
    }
}
