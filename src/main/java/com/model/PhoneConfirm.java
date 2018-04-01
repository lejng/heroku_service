package com.model;

import javax.persistence.*;

@Entity
@Table(name = "service.phone_confirm")
public class PhoneConfirm {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="service.phone_confirm_id_seq")
    @SequenceGenerator(name="service.phone_confirm_id_seq", sequenceName="service.phone_confirm_id_seq", allocationSize=1)
    private Integer id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "confirm_code")
    private String confirmCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    @Override
    public String toString() {
        return "PhoneConfirm{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", confirmCode='" + confirmCode + '\'' +
                '}';
    }
}
