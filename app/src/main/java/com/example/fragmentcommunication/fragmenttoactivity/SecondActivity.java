package com.example.fragmentcommunication.fragmenttoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.datafromactivitytofragmentwithfactorymethod.ThirdActivity;
import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

import java.util.Objects;

/**
 * The classes SecondActivity and BlankFragment are associated with each other for the purpose of
 * learning how to open a fragment with animation and how to communicate between the fragment and
 * it's parent activity.
 */
public class SecondActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {
    private FrameLayout fragmentContainer;
    private AppCompatEditText infoEditText;
    private AppCompatButton sendInfoButton;

    private void initialise() {
        fragmentContainer = findViewById(R.id.fragment_container);
        infoEditText = findViewById(R.id.info_edit_text);
        sendInfoButton = findViewById(R.id.send_info_button_second_activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialise();

        sendInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = Objects.requireNonNull(infoEditText.getText()).toString();
                openFragment(text);
            }
        });
    }

    public void openFragment(String text) {
        // This is where we send data from the Parent Activity to the Fragment
        BlankFragment fragment = BlankFragment.newInstance(text);
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
        transaction.add(R.id.fragment_container, fragment, "BLANK_FRAGMENT").commit();
    }

    public void gotoMainActivity(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoThirdActivity(View view) {
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        // In the String 'sendBackText' we got the info back from the Fragment
        infoEditText.setText(sendBackText);
        /* This is so that our Fragment gets automatically closed for the button click for the button
        in the fragment
        */
        onBackPressed();
    }
}