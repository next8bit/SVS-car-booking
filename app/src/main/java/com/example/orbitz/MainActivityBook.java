package com.example.orbitz;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

//import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivityBook extends AppCompatActivity {

    Button nextButton1;
    Button calculate;

    private static final String TAG = "MainActivityBook";
    private TextView mDisplayDate;
    private  EditText EdtLocation,EdtBrand,EdtModel;

    TextView TxtAmount;
    TextView mTimeTextView;

    Button mPickTimeButton;
    Context mContext = this;
    DatabaseReference myRef;
    BookingDB bookingDB;
    EditText EnterDays;


 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainbook);

        EdtLocation = findViewById(R.id.enterLocation);


           EdtBrand = findViewById(R.id.EnterBrand);
           EdtModel = findViewById(R.id.editText3);
           nextButton1 =(Button) findViewById(R.id.btnMain);
           calculate = (Button) findViewById(R.id.calculate);
           EnterDays = findViewById(R.id.EnterDays);
           TxtAmount = findViewById(R.id.TxtAmount);

           bookingDB = new BookingDB();

      //init();

        mTimeTextView =  findViewById(R.id.time_text_view) ;
        Calendar calendar = Calendar.getInstance();
        final  int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final  int minute = calendar.get(Calendar.MINUTE);
        mPickTimeButton = findViewById(R.id.pick_time_button) ;

        mPickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker View, int hourOfDay, int minute) {
                        mTimeTextView.setText(hourOfDay + ":" + minute );
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();
            }
        });


        nextButton1 = findViewById(R.id.btnMain);
        nextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(EdtLocation.getText().toString())|| TextUtils.isEmpty(EdtBrand.getText().toString()) || TextUtils.isEmpty(EdtModel.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Please Fill All the fields",Toast.LENGTH_SHORT).show();
                }

                else {

                    Log.d("nav", "test");
                    Intent intent1 = new Intent(MainActivityBook.this, confirm_car.class);
                    startActivity(intent1);

                    myRef = FirebaseDatabase.getInstance().getReference().child("Booking");

                    bookingDB.setLocation(EdtLocation.getText().toString().trim());
                    bookingDB.setDate(mDisplayDate.getText().toString().trim());
                    bookingDB.setTime(mTimeTextView.getText().toString().trim());
                    bookingDB.setBrand(EdtBrand.getText().toString().trim());
                    bookingDB.setModel(EdtModel.getText().toString().trim());
                    bookingDB.setDays(Integer.parseInt(EnterDays.getText().toString().trim()));
                    bookingDB.setAmount(Integer.parseInt(TxtAmount.getText().toString().trim()));


                    myRef.child("book1").setValue(bookingDB);

                    Toast.makeText(getApplicationContext(), "Booking Success", Toast.LENGTH_LONG).show();
                    clearData();

//                switch (view.getId()){}https://github.com/thivi97/Orbitz.githttps://github.com/thivi97/Orbitz.githttps://github.com/thivi97/Orbitz.git
                }}
            });

        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivityBook.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy:" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };


        calculate = (Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer  Day = Integer.parseInt(EnterDays.getText().toString());
                Integer  cal =  Day * 500;

                TxtAmount.setText(Integer.toString(cal));

            }
        });

    }

//    private void saveData(BookingDB bookingDB) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Booking");
//       // myRef.push().setValue(bookingDB);
//        myRef.child("book1").setValue(bookingDB);
//    }
//
//    private void init() {
//
//           EdtLocation = findViewById(R.id.enterLocation);
//           mDisplayDate = (TextView) findViewById(R.id.tvDate);
//           mTimeTextView = (TextView) findViewById(R.id.time_text_view) ;
//           EdtBrand = findViewById(R.id.EnterBrand);
//           EdtModel = findViewById(R.id.editText3);
//           nextButton1 =(Button) findViewById(R.id.btnMain);
//    }


}
