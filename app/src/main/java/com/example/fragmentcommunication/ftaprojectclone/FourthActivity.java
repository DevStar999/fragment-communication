package com.example.fragmentcommunication.ftaprojectclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

public class FourthActivity extends AppCompatActivity
        implements FeedingFragment.OnFeedingFragmentInteractionListener,
        ShopFragment.OnShopFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            // Back button was pressed from fragment
            getSupportFragmentManager().popBackStack();
        }
    }

    public void gotoMainActivity(View view) {
        Intent intent = new Intent(FourthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openFeedingFragment(View view) {
        FeedingFragment fragment = new FeedingFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.full_screen_fragment_container_fourth_activity,
                fragment, "FEEDING_FRAGMENT").commit();
    }

    public void openShopFragment(View view) {
        ShopFragment fragment = new ShopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.full_screen_fragment_container_fourth_activity,
                fragment, "SHOP_FRAGMENT").commit();
    }

    @Override
    public void onFeedingFragmentInteractionBackClicked() {
        onBackPressed();
    }

    @Override
    public void onShopFragmentInteractionBackClicked() {
        onBackPressed();
    }
}