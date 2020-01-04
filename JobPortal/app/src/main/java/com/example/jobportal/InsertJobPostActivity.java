package com.example.jobportal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobportal.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InsertJobPostActivity extends AppCompatActivity {

    //private Toolbar toolbar;
    Toolbar toolbar;
private  EditText Edcompanyname;
    private EditText job_title;
     private EditText       job_description;
     private EditText job_Skills;
     private EditText job_sallary;
     private EditText job_email;
    private Button btn_post_job;
//Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mjobpost;
    private DatabaseReference mPublicdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);
        toolbar = findViewById(R.id.insert_job_toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Insert job");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uID = mUser.getUid();

        mjobpost=FirebaseDatabase.getInstance().getReference().child("Job Post").child(uID);
        mPublicdatabase=FirebaseDatabase.getInstance().getReference().child("Public Databse");

        insertjob();
    }

    private void insertjob(){
        job_title = findViewById(R.id.job_Title);
        job_description = findViewById(R.id.job_Description);
        job_Skills = findViewById(R.id.job_skills);
        job_sallary = findViewById(R.id.job_salary);
        job_email =findViewById(R.id.job_email);
        Edcompanyname=findViewById(R.id.job_Company);
        btn_post_job =findViewById(R.id.btn_Job_post);

        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=job_title.getText().toString().trim();
                String description = job_description.getText().toString().trim();
                String skills=job_Skills.getText().toString().trim();
                String salary=job_sallary.getText().toString().trim();
                String email=job_email.getText().toString().trim();
                String  comapny =Edcompanyname.getText().toString().trim();


                if(TextUtils.isEmpty(title)){
                    job_title.setError("Please enter the job title");
                    return;
                } if(TextUtils.isEmpty(description)){
                    job_description.setError("Please enter the job description");
                    return;
                }if(TextUtils.isEmpty(skills)){
                    job_title.setError("Please enter the job skills requried");
                    return;
                } if(TextUtils.isEmpty(salary)){
                    job_title.setError("Please enter the salary for the job ");
                    return;
                }if (TextUtils.isEmpty(email)){
                    job_email.setError("please enter to contact you");
                }
                if (TextUtils.isEmpty(comapny)) {
                    Edcompanyname.setError("please enter your company name");
                }


                String id=mjobpost.push().getKey();

                String date=DateFormat.getDateInstance().format(new Date());

     //           (String title, String description, String skills, String salary, String email, String id, String date, String companyname)
                // Data data=new Data(title,description,skills,salary,id,date,email);
                Data data = new Data(title,description,skills,salary,email,id,date,comapny);
//String title, String description, String skills, String salary, String id, String date,String email
                mjobpost.child(id).setValue(data);

                mPublicdatabase.child(id).setValue(data);

                             Toast.makeText(getApplicationContext(),"Sucessfully done",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),PostjobActivity.class));
            }
        });
    }
}
