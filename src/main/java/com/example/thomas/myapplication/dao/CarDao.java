package com.example.thomas.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.thomas.myapplication.dao.entity.Car;

import java.util.List;

/**
 * Created by thomas.hildesheim on 12/28/17.
 */

@Dao
public interface CarDao {
    @Query("SELECT * FROM user")
    List<Car> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<Car> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    Car findByName(String first, String last);

    @Insert
    void insertAll(Car... cars);

    @Delete
    void delete(Car car);
}
