package com.example.thomas.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thomas.myapplication.R;
import com.example.thomas.myapplication.dao.entity.Car;
import com.example.thomas.myapplication.service.CarService;

import java.util.List;

public class CarSelectionActivity extends Activity {

    private CarService carService = CarService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selection);

        this.setUpCarsTable();
    }


    private void setUpCarsTable() {

        TableLayout allCarsTable = (TableLayout)findViewById(R.id.allCarsTable);

        List<Car> cars = carService.getCars(1);
        cars.forEach(car -> {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            row.setPadding(50, 100, 50, 100);
            row.setOnClickListener(carClicked);

            TextView text = new TextView(this);
            text.setText(car.getName());
            text.setTextSize(24);

            row.addView(text);
            allCarsTable.addView(row);
        });
    }

    View.OnClickListener carClicked = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button
        }
    };

    /**
     * When the user clicks the add vehicle button.
     * @param {View} view
     */
    public void addCar(View view) {
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }

}
