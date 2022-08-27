package com.example.fragmentcommunication.datafromactivitytofragmentwithfactorymethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.fragmenttoactivity.SecondActivity;
import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void gotoMainActivity(View view) {
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoSecondActivity(View view) {
        Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}