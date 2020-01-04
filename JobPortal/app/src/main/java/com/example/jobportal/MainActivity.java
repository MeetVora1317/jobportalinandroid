package com.example.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private  EditText password;
    private Button btnLogin;
    private Button btnRegistration;

    // firebase Auth;
    private FirebaseAuth mAuth;

    //progress Dialog
    private ProgressDialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        mdialog=new ProgressDialog(this);
        LoginFunction();
    }
    private void  LoginFunction(){
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        btnLogin=findViewById(R.id.btn_login);
        btnRegistration=findViewById(R.id.btn_registration);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = email.getText().toString().trim();
                String mpass = password.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Requried Field ");
                    return;
                }
                if(TextUtils.isEmpty(mpass)){
                    password.setError("Requried Field");
                    return;
                }
                mdialog.setMessage("Processing...");

                mdialog.show();
                mAuth.signInWithEmailAndPassword(mEmail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mdialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Sucessfully Login",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        }else {
                            mdialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });
    }
}
