package com.example.joy.myfirebase6;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private EditText email,pass;
    private Button signUP,signIN;
    private ProgressDialog dialog;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----initialize-------------------------
        email=findViewById(R.id.edittext_email);
        pass=findViewById(R.id.edittext_pass);
        signUP=findViewById(R.id.btn_signup);
        signIN=findViewById(R.id.btn_signin);



        //---dialog------------------
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        //----auth---------------
        auth=FirebaseAuth.getInstance();

        //---onClick---------------------------
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (pass.getText().toString().length()<6){

                    Toast.makeText(MainActivity.this,"Password's minimum length is 6 .",Toast.LENGTH_LONG).show();

                }
                else if (email.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"please fill up your email address..",Toast.LENGTH_LONG).show();
                }
                else {
                    dialog.show();
                    auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            dialog.cancel();

                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Succesfully registered !",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this,"SignUp failed !!",Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }

            }
        });


        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.show();
                auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.cancel();

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Succesfully Login !",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Login failed !!",Toast.LENGTH_LONG).show();
                        }


                    }
                });

            }
        });


    }
}
