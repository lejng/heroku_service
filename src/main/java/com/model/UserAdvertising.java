package com.model;

import javax.persistence.*;

@Entity
@Table(name = "service.users_advertising")
public class UserAdvertising {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="service.users_advertising_id_seq")
    @SequenceGenerator(name="service.users_advertising_id_seq", sequenceName="service.users_advertising_id_seq", allocationSize=1)
    private Integer id;

    @Column(name = "id_user")
    private Integer userId;

    @Column(name = "id_advertising")
    private Integer advertisingId;

    @Column(name = "viewed")
    private boolean isViewed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(Integer advertisingId) {
        this.advertisingId = advertisingId;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    @Override
    public String toString() {
        return "UserAdvertising{" +
                "id=" + id +
                ", userId=" + userId +
                ", advertisingId=" + advertisingId +
                ", isViewed=" + isViewed +
                '}';
    }
}
