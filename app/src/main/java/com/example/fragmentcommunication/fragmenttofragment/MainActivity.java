package com.example.fragmentcommunication.fragmenttofragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.fragmenttoactivity.SecondActivity;

/**
 * The classes MainActivity, FragmentA & FragmentB are associated with each other for the purpose of
 * learning how to communicate between 2 fragments via their parent activity. In here, we also learned
 * how to communicate between the fragment and it's parent activity.
 */
public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    private void initialise() {
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();
    }

    @Override
    public void onInputASent(CharSequence input) {
        // Data from FragmentA is being sent to FragmentB
        fragmentB.updateEditText(input);
    }

    @Override
    public void onInputBSent(CharSequence input) {
        // Data from FragmentA is being sent to FragmentB
        fragmentA.updateEditText(input);
    }

    public void gotoSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}