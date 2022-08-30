package com.example.fragmentcommunication.fragmenttofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fragmentcommunication.FragmentLarge;
import com.example.fragmentcommunication.FragmentSmall;
import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.datafromactivitytofragmentwithfactorymethod.ThirdActivity;
import com.example.fragmentcommunication.fragmenttoactivity.BlankFragment;
import com.example.fragmentcommunication.fragmenttoactivity.SecondActivity;

/**
 * The classes MainActivity, FragmentA & FragmentB are associated with each other for the purpose of
 * learning how to communicate between 2 fragments via their parent activity. In here, we also learned
 * how to communicate between the fragment and it's parent activity.
 */
public class MainActivity extends AppCompatActivity
        implements FragmentA.FragmentAListener, FragmentB.FragmentBListener,
        FragmentSmall.FragmentSmallListener, FragmentLarge.FragmentLargeListener {
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentSmall fragmentSmall;

    private void initialise() {
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentSmall = new FragmentSmall();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .replace(R.id.fragment_container_small_stays_always, fragmentSmall)
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
//        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//        startActivity(intent);
//        finish();
        FragmentLarge fragment = new FragmentLarge();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /* Here, we add the 2 anim file arguments again so that our back button can have the same
        animations. If we only pass the first 2 arguments the animations won't work when we click our
        back button
        */
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        /* If we don't add the following statement, then if we click our back button when the
        fragment is open, it will just close the activity completely. But, we only want our fragment
        to close, thus we add the following statement
        */
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container_large_comes_on_runtime, fragment, "LARGE_FRAGMENT").commit();
    }

    public void gotoThirdActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }
}