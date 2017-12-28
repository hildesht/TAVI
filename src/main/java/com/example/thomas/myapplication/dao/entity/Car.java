package com.example.thomas.myapplication.dao.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by thomas.hildesheim on 12/28/17.
 */

@Entity
public class Car {
    @PrimaryKey
    private Integer id;

    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
    private User user;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "make")
    private String make;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "year")
    private Integer year;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
