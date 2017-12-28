package com.example.thomas.myapplication.service;

import com.example.thomas.myapplication.dao.AppDatabase;
import com.example.thomas.myapplication.dao.entity.Car;

import java.util.Collections;
import java.util.List;

/**
 * Created by thomas.hildesheim on 12/28/17.
 */


public class CarService {
    private static CarService instance;

    public static CarService getInstance() {
        if (instance == null) {
            instance = new CarService();
        }
        return instance;
    }
    //TODO: how to get context?
//    private AppDatabase appDatabase = AppDatabase.getAppDatabase(this);

    public Car getFavoriteCar(Integer userId) {
        //TODO: implement
        return new Car();
    }

    public List<Car> getCars(Integer userId) {
        //TODO: implement
        return Collections.emptyList();
    }

    public Car createCar(String carName, String carMake, String carModel, Integer carYear) {
        Car newCar = new Car();
        newCar.setName(carName);
        newCar.setMake(carMake);
        newCar.setModel(carModel);
        newCar.setYear(carYear);
        Car savedCar = create(newCar);
        return savedCar;
    }

    public Car create(Car car) {
        Car savedCar = instance.create(car);
        return savedCar;
    }
}
