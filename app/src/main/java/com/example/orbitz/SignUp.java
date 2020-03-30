package com.example.orbitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button signupbtn,btnBack;
    EditText txtUN,txtEmail,txtPass,txtConPass;
    CheckBox terms;
    TextView usernameLbl,usernameLbl2;

    DatabaseReference dbRef;




    User user;





//    databaseHelper helper = new databaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupbtn = findViewById(R.id.btn_sign_up2);
        txtUN = findViewById(R.id.txt_first_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPass = findViewById(R.id.txt_pass);
        txtConPass = findViewById(R.id.txt_con_pass);
        btnBack = findViewById(R.id.btn_Back2);
        terms = findViewById(R.id.check_box_terms);
        usernameLbl = findViewById(R.id.lbl_username);
        usernameLbl2= findViewById(R.id.lbl_username2);



        user = new User();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent back = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);

            }
        });



        signupbtn.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                //terms and conditions check


                if (terms.isChecked() == true) {


                    dbRef = FirebaseDatabase.getInstance().getReference("Sign up");


                    user.setUserName(txtUN.getText().toString().trim());
                    user.setEmail(txtEmail.getText().toString().trim());
                    user.setPassword(txtPass.getText().toString().trim());
                    user.setConPassword(txtConPass.getText().toString().trim());


                    String usernmae = txtUN.getText().toString().trim();
                    String email = txtEmail.getText().toString().trim();
                    String password = txtPass.getText().toString().trim();
                    String conpassword = txtConPass.getText().toString().trim();


                    usernameLbl.setText(null);
                    usernameLbl2.setText(null);


                    if (!usernmae.matches("[0-9a-zA-Z]+")) {

                        usernameLbl.setText("Invalid UserName");


                        Toast.makeText(getApplicationContext(), "please Enter The UserName again", Toast.LENGTH_SHORT).show();


                        return;


                    }

                    if (!email.matches("[0-9@a-zA-Z.]+")) {

                        usernameLbl2.setText("Invalid Email");

                        Toast.makeText(getApplicationContext(), "please Enter Email again", Toast.LENGTH_SHORT).show();


                        return;

                    }



                    if (!password.isEmpty()) {

                        if (password.equals(conpassword)) {

                            Toast.makeText(getApplicationContext(), "sign up successfully ", Toast.LENGTH_SHORT).show();


                            dbRef.child(usernmae).setValue(user);


                            txtUN.setText(null);
                            txtEmail.setText(null);
                            txtPass.setText(null);
                            txtConPass.setText(null);

                        } else {

                            Toast.makeText(getApplicationContext(), "re-Enter the password ", Toast.LENGTH_SHORT).show();
                            txtPass.setText(null);
                            txtConPass.setText(null);

                        }

                    } else {

                        Toast.makeText(getApplicationContext(), "Enter the password ", Toast.LENGTH_SHORT).show();
                        txtPass.setText(null);
                        txtConPass.setText(null);

                    }



                }
                else{

                    Toast.makeText(getApplicationContext(), "Accept the Terms and Conditions ", Toast.LENGTH_SHORT).show();

                }
            }


        });






    }
}
