package com.example.thomas.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thomas.myapplication.R;
import com.example.thomas.myapplication.service.CarService;

public class AddCarActivity extends Activity {

    private CarService carService = CarService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
    }

    /**
     * Prompts user to add image
     * @param view
     */
    public void addImage(View view) {
        Log.d("Develop", "Put add image code here.");
    }

    /**
     * Saves the car to the database
     * @param view
     */
    public void saveCar(View view) {
        // Gets the data repository in write mode
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        EditText etn = (EditText)findViewById(R.id.editTextName);
        String carName = etn.getText().toString();
        EditText etm = (EditText)findViewById(R.id.editTextMake);
        String carMake = etm.getText().toString();
        EditText etmo = (EditText)findViewById(R.id.editTextModel);
        String carModel = etmo.getText().toString();
        EditText ety = (EditText)findViewById(R.id.editTextYear);
        Integer carYear = Integer.getInteger(ety.getText().toString());

        Button addVehicle = (Button)findViewById(R.id.buttonAdd);
        addVehicle.setEnabled(false);
        try {
            carService.createCar(carName, carMake, carModel, carYear);
            alertUser("Success", "Vehicle " + carName + " created successfully.", AddCarActivity.this);
        } catch (android.database.sqlite.SQLiteConstraintException e){
            Log.d("Caught Query e", e.toString());
            alertUser("Error", e.toString(), AddCarActivity.this);
        }
        addVehicle.setEnabled(true);

    }

    //TODO move this to a central location

    private void alertUser (String title, String msg, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
