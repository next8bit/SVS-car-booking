package com.example.orbitz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import android.support.v7.app.AppCompatActivity;

public class CarDetails extends AppCompatActivity {

    Button savebt,typeb,nextb;
    EditText editman,editmod,editreg;
    DatabaseReference dbref;
    CarDb cardb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        savebt = findViewById(R.id.btnadd1);
        typeb = findViewById(R.id.btntype);
        nextb = findViewById(R.id.btnnext);
        editman = findViewById(R.id.txtman);
        editmod = findViewById(R.id.txtmod);
        editreg = findViewById(R.id.txtreg);

        cardb = new CarDb();

        AddData();
        UserProf();
        Next();

    }

    private void Next() {
        nextb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarDetails.this, PersonalDetails.class));
            }
        });
    }

    private void UserProf() {
        typeb.setOnClickListener(new View.OnClickListener() {
            /*Intent intent = new Intent(this,PersonalDetails.class);
            startActivity(intent);*/
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarDetails.this, UserProfile.class));
            }
        });
    }

    



            }
        });

    }
}
