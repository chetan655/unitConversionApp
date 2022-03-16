package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageButton myImageButton1;
    ImageButton myImageButton2;
    ImageButton myImageButton3;
    EditText userInputValue;
    TextView field1;
    TextView field2;
    TextView field3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        field3 = findViewById(R.id.field3);
        userInputValue = findViewById(R.id.userInputValue);
        myImageButton1 = findViewById(R.id.myImageButton1);
        myImageButton2 = findViewById(R.id.myImageButton2);
        myImageButton3 = findViewById(R.id.myImageButton3);

        Spinner spinner = findViewById(R.id.mySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.toBeConvertedUnits, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        myImageButton1.setOnClickListener(view1 -> {
            if (adapterView.getItemAtPosition(i).equals("Meter") && !(userInputValue.getText().toString().matches(""))) {
                field1.setText(MessageFormat.format("{0} Cm", meterToCm(Double.parseDouble(String.valueOf(userInputValue.getText())))));
                field2.setText(MessageFormat.format("{0} Feet", meterToFoot(Double.parseDouble(String.valueOf(userInputValue.getText())))));
                field3.setText(MessageFormat.format("{0} Inches", meterToInch(Double.parseDouble(String.valueOf(userInputValue.getText())))));
            }
            else { Toast.makeText(MainActivity.this, "Enter Some Value", Toast.LENGTH_SHORT).show(); }
        });

        myImageButton2.setOnClickListener(view12 -> {
            if (adapterView.getItemAtPosition(i).equals("Celsius") && !(userInputValue.getText().toString().matches(""))) {
                field1.setText(MessageFormat.format("{0} Kelvin", celsiusToK(Double.parseDouble(String.valueOf(userInputValue.getText())))));
                field2.setText(MessageFormat.format("{0} Fahrenheit", celsiusToF(Double.parseDouble(String.valueOf(userInputValue.getText())))));
            }
            else { Toast.makeText(MainActivity.this, "Enter Some Value", Toast.LENGTH_SHORT).show(); }
        });

        myImageButton3.setOnClickListener(view13 -> {
            if (adapterView.getItemAtPosition(i).equals("Kilogram") && !(userInputValue.getText().toString().matches(""))) {
                field1.setText(MessageFormat.format("{0} Grams", kgToGram(Double.parseDouble(String.valueOf(userInputValue.getText())))));
                field2.setText(MessageFormat.format("{0} Ounces", kgToOunce(Double.parseDouble(String.valueOf(userInputValue.getText())))));
                field3.setText(MessageFormat.format("{0} Pounds", kgToPound(Double.parseDouble(String.valueOf(userInputValue.getText())))));
            }
            else { Toast.makeText(MainActivity.this, "Enter Some Value", Toast.LENGTH_SHORT).show(); }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //method to format the calc value to a 3 decimal place limit
    public double decimalFormatter(double passedDoubleValue){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(passedDoubleValue));
    }

    //3 methods to handle the length conversions
    public double meterToCm(double val) { return decimalFormatter(val * 100); }
    public double meterToFoot(double val) { return  decimalFormatter(val * 3.281); }
    public double meterToInch(double val) { return  decimalFormatter(val * 39.36); }

    //3 methods to handle the weight conversions
    public double kgToGram(double val) { return  decimalFormatter(val * 1000); }
    public double kgToOunce(double val) { return  decimalFormatter(val * 35.27345); }
    public double kgToPound(double val) { return  decimalFormatter(val * 2.2); }

    //2 methods to handle the temp conversions
    public double celsiusToK(double val) { return  decimalFormatter(val * 273.15); }
    public double celsiusToF(double val) { return  decimalFormatter((val * 1.8) + 32); }
}