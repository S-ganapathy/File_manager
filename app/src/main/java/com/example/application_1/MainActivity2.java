package com.example.application_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.File;
public class MainActivity2 extends AppCompatActivity {
    RecyclerView Recycleview;
    String Path;
    File root;
    File[] fileandfolder;
    TextView txt1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Recycleview=findViewById(R.id.recycleview);
        Path=getIntent().getStringExtra("path");
        root=new File(Path);
        fileandfolder=root.listFiles();
        Recycleview.setLayoutManager(new LinearLayoutManager(this));
        Recycleview.setAdapter(new
                com.example.application_1.MyAdapter(getApplicationContext(),fileandfolder));
        txt1=findViewById(R.id.nofile);
        if(fileandfolder==null||fileandfolder.length==0){
            txt1.setVisibility(View.VISIBLE);
            return;
        }
        txt1.setVisibility(View.INVISIBLE);
    }
}
