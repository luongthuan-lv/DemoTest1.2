package com.luongthuan.demotest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
TextView tvUserId,tvId,tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvId=findViewById(R.id.tvId);
        tvTitle=findViewById(R.id.tvTitle);
        tvUserId=findViewById(R.id.tvUserId);
       Bundle bundle=getIntent().getExtras();
       String id=bundle.getString("id");
       String uerid=bundle.getString("userid");
       String title=bundle.getString("title");
       tvId.setText(id);
       tvUserId.setText(uerid);
       tvTitle.setText(title);

    }
}