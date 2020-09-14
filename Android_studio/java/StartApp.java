package com.example.electronic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartApp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        Button button_show = (Button)findViewById(R.id.button_start_show);
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartApp.this,ShowChart.class);
                startActivity(intent);
            }
        });

        Button button_check = (Button)findViewById(R.id.button_start_check);
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartApp.this,CheckPreserved.class);
                startActivity(intent);
            }
        });


    }


}
