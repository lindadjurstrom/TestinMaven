package com.djurstrom;

import javax.persistence.*;
import java.sql.Timestamp;

    @Entity
    @Table(name = "city")
    public class City {
        @Id
        @Column(name = "city_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int city_id;

        @Column(name = "city")
        private String city;

        @Column(name = "country_id")
        private int country_id;

        @Column(name = "last_update")
        private Timestamp last_update;


        public City() {

        }

        public City(String city, int country_id, Timestamp last_update) {
            this.city = city;
            this.country_id = country_id;
            this.last_update = last_update;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public Timestamp getLast_update() {
            return last_update;
        }

        public void setLast_update(Timestamp last_update) {
            this.last_update = last_update;
        }
    }

