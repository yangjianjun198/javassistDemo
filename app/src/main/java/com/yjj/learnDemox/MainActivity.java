package com.yjj.learnDemox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yjj.mylibrary.LibActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.jump_act).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubClassActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.jump_library).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LibActivity.class);
                startActivity(intent);
            }
        });
    }
}
