package com.example.ycc.btsalbumcardview;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public TextView title,count;
    public ImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title=findViewById(R.id.albumNameNext);
        count=findViewById(R.id.songs);
        thumbnail=findViewById(R.id.fillPhoto);

        String name=getIntent().getExtras().getString("name");
        String date=getIntent().getExtras().getString("songs");
        int photo=getIntent().getExtras().getInt("photo");
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        title.setText(name);
        count.setText(date);
        thumbnail.setImageResource(photo);
    }
}
