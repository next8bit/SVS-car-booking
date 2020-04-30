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


    private DatePickerDialog.OnDateSetListener mDateSetListener;


    public void clearData(){

        EdtLocation.setText("");
        EdtModel.setText("");
        EdtBrand.setText("");
        mDisplayDate.setText("");
        mTimeTextView.setText("");
        TxtAmount.setText("");
        EnterDays.setText("");



    }

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
