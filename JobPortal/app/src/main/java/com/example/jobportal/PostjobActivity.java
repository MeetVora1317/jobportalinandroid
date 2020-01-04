package com.example.jobportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jobportal.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PostjobActivity extends AppCompatActivity {

    private FloatingActionButton fabbtn;

    //recycler view
    private RecyclerView recyclerView;
Toolbar toolbar;

    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference JobPostDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjob);

        toolbar = findViewById(R.id.postjobtoolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Post A Job");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fabbtn = findViewById(R.id.fab_add);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String Uid=mUser.getUid();

        JobPostDatabase= FirebaseDatabase.getInstance().getReference().child("Job Post").child(Uid);

        recyclerView=findViewById(R.id.recycler_job_post_id);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);



        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Data,Myviewholder>adapter=new FirebaseRecyclerAdapter<Data, Myviewholder>(
                Data.class,
                R.layout.job_post_item_data,
                Myviewholder.class,
                JobPostDatabase
        ) {
            @Override
            protected void populateViewHolder(Myviewholder myviewholder, Data data, int i) {
                myviewholder.setjobtitle(data.getTitle());
                myviewholder.setdate(data.getDate());
                myviewholder.setjobdescription(data.getDescription());
                myviewholder.setjobskills(data.getSkills());
                myviewholder.setjobsalary(data.getSalary());
//                myviewholder.setemail(data.getEmail());
                myviewholder.setemail(data.getEmail());
                myviewholder.setCompanyname(data.getCompanyname());
            }
        };
        recyclerView.setAdapter(adapter);

    }
    public static class Myviewholder extends RecyclerView.ViewHolder{


        View myview;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setjobtitle(String Title){
            TextView mtitle=myview.findViewById(R.id.job_Title);
            mtitle.setText(Title);

        }
        public void setdate(String date){
            TextView  mdate=myview.findViewById(R.id.job_date);
            mdate.setText(date);
        }
        public void setjobdescription(String description){
            TextView mdesc=myview.findViewById(R.id.job_Description);
            mdesc.setText(description);
        }
        public void setjobskills(String skills){
            TextView mskills=myview.findViewById(R.id.job_skillss);
            mskills.setText(skills);
        }
        public void setjobsalary(String salalry){
            TextView msalary=myview.findViewById(R.id.job_salaryy);
            msalary.setText(salalry);
        }

        public void setemail(String email){
            TextView memail=myview.findViewById(R.id.job_email);
            memail.setText(email);
    }
    public  void setCompanyname(String companyname){
            TextView mcompanyname=myview.findViewById(R.id.job_companyy);
            mcompanyname.setText(companyname);
    }
    }
}
