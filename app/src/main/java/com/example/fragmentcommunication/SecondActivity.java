package com.example.fragmentcommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void gotoMainActivity(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}