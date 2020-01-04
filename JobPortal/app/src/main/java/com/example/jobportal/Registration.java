package com.example.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText emailr;
    private EditText passr;
    private Button btnlr;
    private Button btnrr;

    // firebase auth

    private FirebaseAuth mAuth;

    // progress dialog
    private ProgressDialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();
        mdialog=new ProgressDialog(this);
        register();
    }

    private void register(){
        emailr=findViewById(R.id.email_registration);
        passr=findViewById(R.id.password_registration);
        btnlr = findViewById(R.id.btn_loginr);
        btnrr = findViewById(R.id.btn_registrationr);

        btnlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
        btnrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=emailr.getText().toString().trim();
                String passw=passr.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    emailr.setError("Requried field");
                    return;
                }

                if(TextUtils.isEmpty(passw)){
                 passr.setError("Requried field");
                return;
                }

                mdialog.setMessage("processing ...");
                mdialog.show();


                mAuth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            mdialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Sucessfully Register",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        }else {
                            mdialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
