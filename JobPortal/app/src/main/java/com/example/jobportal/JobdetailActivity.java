package com.example.jobportal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class JobdetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private TextView date;
    private TextView skills;
    private TextView salaray;
    private TextView email , commpanyname;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);
       toolbar = findViewById(R.id.job_detail);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Job detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        title=findViewById(R.id.job_detailtitle);
        date=findViewById(R.id.job_detaildate);
        description=findViewById(R.id.job_detaildescription);
        skills=findViewById(R.id.job_detailskills);
        salaray=findViewById(R.id.job_detailtsalary);
        email=findViewById(R.id.job_detailemail);
        commpanyname=findViewById(R.id.job_detailcompanyname);
        //Receive data from all job
        Intent intent=getIntent();

        String mtitle=intent.getStringExtra("title");
        String mdate=intent.getStringExtra("date");
        String mdescription=intent.getStringExtra("description");
        String mskills=intent.getStringExtra("skills");
        String msalary=intent.getStringExtra("salary");
        String  memail= intent.getStringExtra("email");
        String company = intent.getStringExtra("companyname");



        title.setText(mtitle);
        date.setText(mdate);
        description.setText(mdescription);
        skills.setText(mskills);
        salaray.setText(msalary);
        email.setText(memail);
        commpanyname.setText(company);



    }
}
