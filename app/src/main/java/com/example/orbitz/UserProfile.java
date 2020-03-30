package com.example.orbitz;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    Button saveb,editb;
    EditText editid,editname,editmail,editno,editmodel;
    DatabaseReference dbref;
    DriverDb driverdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        saveb = findViewById(R.id.btnadd);
        editb = findViewById(R.id.btnedit);
        editid = findViewById(R.id.txtid);
        editname = findViewById(R.id.txtname);
        editmail = findViewById(R.id.txtmail);
        editno = findViewById(R.id.txtno);
        editmodel = findViewById(R.id.txtmodel);

        driverdb = new DriverDb();

        AddData();
       // EditData();
    }

    public void AddData(){
        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbref = FirebaseDatabase.getInstance().getReference().child("DriverDb");

                if (TextUtils.isEmpty(editid.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter your ID", Toast.LENGTH_LONG).show();
                else if (TextUtils.isEmpty(editname.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter your Name", Toast.LENGTH_LONG).show();
                else if (TextUtils.isEmpty(editmail.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a email address", Toast.LENGTH_LONG).show();
                else if (TextUtils.isEmpty(editno.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a vehicle number", Toast.LENGTH_LONG).show();
                else if (TextUtils.isEmpty(editmodel.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please a model name", Toast.LENGTH_LONG).show();

                else {

                    driverdb.setId(editid.getText().toString().trim());

                    driverdb.setName(editname.getText().toString().trim());

                    driverdb.setMail(editmail.getText().toString().trim());

                    if (!editmail.getText().toString().trim().matches("[0-9@a-zA-Z.]+")) {

                        editmail.setText(null);

                        Toast.makeText(getApplicationContext(), "please Enter Email again", Toast.LENGTH_SHORT).show();


                        return;

                    }

                    driverdb.setVehiNo(editno.getText().toString().trim());

                    driverdb.setModel(editmodel.getText().toString().trim());

                    dbref.push().setValue(driverdb);

                    dbref.child("driverdb1").setValue(driverdb);

                    Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_LONG).show();


                    editid.setText(null);

                    editname.setText(null);

                    editmail.setText(null);

                    editno.setText(null);

                    editmodel.setText(null);

                }
            }
        });

    }

   /* private void EditData() {
        editb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.UpdateData(editid.getText().toString(),
                        editname.getText().toString(),
                        editmail.getText().toString(),
                        editno.getText().toString(),
                        editmodel.getText().toString());
                if(isUpdate = true)
                    Toast.makeText(UserProfile.this,"Data Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UserProfile.this,"Data not Updated",Toast.LENGTH_SHORT).show();


            }
        });
    }*/
}
