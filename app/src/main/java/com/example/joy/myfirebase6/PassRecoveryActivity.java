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
import com.google.firebase.auth.FirebaseAuth;

public class PassRecoveryActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button button;
    private FirebaseAuth auth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_recovery);

        //----initialize-------------
        etEmail=findViewById(R.id.edittext_email);
        button=findViewById(R.id.btn_recovery);


        //---dialog------------
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);


        //---auth---------------------
        auth=FirebaseAuth.getInstance();


        //---onClick-------------------
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etEmail.getText().toString().isEmpty()){
                    Toast.makeText(PassRecoveryActivity.this,"please enter email",Toast.LENGTH_LONG).show();

                }
                else {

                    dialog.show();
                    auth.sendPasswordResetEmail(etEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            dialog.cancel();
                            if (task.isSuccessful()){
                                Toast.makeText(PassRecoveryActivity.this,"we have send you a email ! check it",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(PassRecoveryActivity.this,"we can't send you email right now ! try later ",Toast.LENGTH_LONG).show();
                            }


                        }
                    });


                }


            }
        });




    }
}
