package com.djurstrom;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;

    @Column(name = "country")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String country;

    @Column(name = "last_update")
    private Timestamp last_update;

    public Country() {

    }

    public Country(String country, Timestamp last_update) {
        this.country = country;
        this.last_update = last_update;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}

