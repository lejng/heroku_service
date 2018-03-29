package com.model;

import javax.persistence.*;

@Entity
@Table(name = "service.advertising")
public class Advertising {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="service.advertising_id_seq")
    @SequenceGenerator(name="service.advertising_id_seq", sequenceName="service.advertising_id_seq", allocationSize=1)
    private Integer id;

    @Column(name = "link")
    private String link;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "timer")
    private Integer timer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "Advertising{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", cost=" + cost +
                ", timer=" + timer +
                '}';
    }
}
