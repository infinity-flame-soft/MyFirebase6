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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBActivity extends AppCompatActivity {

    private EditText etName,etEmail,etContact;
    private Button btnADD;
    private FirebaseDatabase myDB;
    private DatabaseReference myRef;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //----init----------------------------
        etContact=findViewById(R.id.edittext_contact);
        etName=findViewById(R.id.edittext_name);
        etEmail=findViewById(R.id.edittext_email);
        btnADD=findViewById(R.id.btn_add_info);


        //----dialog-----------------
        dialog=new ProgressDialog(this);
        dialog.setMessage("please wait..");
        dialog.setCancelable(false);

        //---firebase db init--------------------
        myDB=FirebaseDatabase.getInstance();
        myRef=myDB.getReference("users");


        //------onClick---------------------------------------------
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

                UserModel model=new UserModel(etName.getText().toString(),etEmail.getText().toString(),etContact.getText().toString());

                String id=myRef.push().getKey();


                myRef.child(id).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(DBActivity.this,"DATA ADDED",Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DBActivity.this,"DATA ADDED FAILED !",Toast.LENGTH_LONG).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.cancel();
                        Toast.makeText(DBActivity.this,"Task Complete !",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });


    }
}
