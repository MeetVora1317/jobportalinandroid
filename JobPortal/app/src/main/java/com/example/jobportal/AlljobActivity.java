package com.example.jobportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jobportal.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlljobActivity extends AppCompatActivity {
//recycler
    private RecyclerView recyclerView;
Toolbar toolbar;

    //Firebase

    private DatabaseReference mAlljobpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alljob);


        //Database
     mAlljobpost= FirebaseDatabase.getInstance().getReference().child("Public Databse");
     mAlljobpost.keepSynced(true);

        toolbar = findViewById(R.id.alljobtoo);
            recyclerView=findViewById(R.id.recycler_alljob);
        setSupportActionBar(toolbar);
        toolbar.setTitle("All job ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,AlljobPostView>adapter=new FirebaseRecyclerAdapter<Data, AlljobPostView>(
                Data.class,
                R.layout.alljobpostactivity,
                AlljobPostView.class,
                mAlljobpost

        )
        {
            @Override
            protected void populateViewHolder(AlljobPostView alljobPostView, final Data data, int i) {
            alljobPostView.setjobtitle(data.getTitle());
            alljobPostView.setdate(data.getDate());
            alljobPostView.setjobdescription(data.getDescription());
            alljobPostView.setjobskills(data.getSkills());
            alljobPostView.setjobsalary(data.getSalary());
            alljobPostView.setemail(data.getEmail());
            alljobPostView.setcompany(data.getCompanyname());
            alljobPostView.myview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(),JobdetailActivity.class);
                    intent.putExtra("title",data.getTitle());
                    intent.putExtra("date",data.getDate());
                    intent.putExtra("description",data.getDescription());
                    intent.putExtra("skills",data.getSkills());
                    intent.putExtra("salary",data.getSalary());
                 //   intent.putExtra("email"),data.getEmail
                   intent.putExtra("email",data.getEmail());
                   intent.putExtra("companyname",data.getCompanyname());
                    startActivity(intent);
                }
            });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public static class AlljobPostView extends RecyclerView.ViewHolder{
        View myview;

        public AlljobPostView(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setjobtitle(String title){
            TextView mtitle=myview.findViewById(R.id.alljobposttitle);
            mtitle.setText(title);

        }
        public void setdate(String date){
            TextView mdate=myview.findViewById(R.id.alljobpostdate);
            mdate.setText(date);
        }
        public void setjobdescription(String description){
            TextView mdesc=myview.findViewById(R.id.alljobpostdescription);
            mdesc.setText(description);
        }
        public void setjobskills(String skills){
            TextView mskills=myview.findViewById(R.id.alljobpostskills);
            mskills.setText(skills);
        }
        public void setjobsalary(String salalry){
            TextView msalary=myview.findViewById(R.id.alljobpostsalary);
            msalary.setText(salalry);
        }
        public void setemail(String email){
            TextView memail=myview.findViewById(R.id.job_email);
            memail.setText(email);

        }
        public  void setcompany(String comp){
            TextView mcompany = myview.findViewById(R.id.alljobpostcompanyname);
            mcompany.setText(comp);
        }
    }
}
