package com.example.orbitz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class SignUp extends AppCompatActivity {

    Button signupbtn,btnBack;
    EditText txtUN,txtEmail,txtPass,txtConPass;
    CheckBox terms;
    TextView usernameLbl,usernameLbl2;

    LoginDatabaseHelper loginDatabaseHelper;





//    databaseHelper helper = new databaseHelper(this);


    
        txtUN = findViewById(R.id.txt_first_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPass = findViewById(R.id.txt_pass);
        txtConPass = findViewById(R.id.txt_con_pass);
        btnBack = findViewById(R.id.btn_Back2);
        terms = findViewById(R.id.check_box_terms);
        usernameLbl = findViewById(R.id.lbl_username);







        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent back = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);

            }
        });



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUN.getText().toString();
                String password = txtPass.getText().toString();
                String confirm_password = txtConPass.getText().toString();

                if(username.equals("") || password.equals("") || confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm_password)){
                        Boolean checkusername = loginDatabaseHelper.CheckUsername(username);
                        if(checkusername == true){
                            Boolean insert = loginDatabaseHelper.Insert(username, password);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                txtUN.setText("");
                                txtPass.setText("");
                                txtConPass.setText("");
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });






    }
}
