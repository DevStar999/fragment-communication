package com.example.fragmentcommunication.datafromactivitytofragmentwithfactorymethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.fragmenttoactivity.SecondActivity;
import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

/**
 * The classes ThirdActivity and CustomBlankFragment are associated with each other for the purpose
 * of learning how send data to a new fragment with a factory method from it's parent activity. Note
 * that here we are seeing UI elements peeking through the activity into the fragment, but let's
 * ignore that as we have learnt the topic
 */
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void loadFragment(View view) {
        CustomBlankFragment customBlankFragment =
                CustomBlankFragment.newInstance("Example Text\n", 123);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.custom_blank_fragment_container, customBlankFragment)
                .commit();
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