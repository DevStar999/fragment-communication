package com.example.fragmentcommunication.ftaprojectclone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity
        implements FeedingFragment.OnFeedingFragmentInteractionListener,
        ShopFragment.OnShopFragmentInteractionListener {
    private SharedPreferences sharedPreferences;
    private int stockLeft;
    private AppCompatTextView stockLeftTextView;
    private int itemPrice;
    private AppCompatTextView itemPriceTextView;

    public void initialise() {
        sharedPreferences = getSharedPreferences("com.example.fragmentcommunication", Context.MODE_PRIVATE);
        stockLeft = sharedPreferences.getInt("stockLeft", 20);
        stockLeftTextView = findViewById(R.id.stock_left_value_text_view_fourth_activity);
        stockLeftTextView.setText(String.valueOf(stockLeft));
        itemPrice = sharedPreferences.getInt("itemPrice", 100);
        itemPriceTextView = findViewById(R.id.item_price_value_text_view_fourth_activity);
        itemPriceTextView.setText(String.valueOf(itemPrice));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        initialise();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            // Back button was pressed from fragment
            getSupportFragmentManager().popBackStack();
        }
        Log.i("Custom Debugging", "Inside method @Override onBackPressed() " +
                "after onBackPressed() processing is done");
        List<Fragment> fragments = new ArrayList<>(getSupportFragmentManager().getFragments());
        for (int index = 0; index < fragments.size(); index++) {
            Fragment currentFragment = fragments.get(index);
            if (currentFragment != null) {
                Log.i("Custom Debugging", "fragment at index = " + index + " is NOT null");
                Log.i("Custom Debugging", "tag of current fragment = " + currentFragment.getTag());
            } else {
                Log.i("Custom Debugging", "fragment at index = " + index + " is null");
            }
        }
    }

    /** onClick listeners for the views of this activity's layout **/
    public void gotoMainActivity(View view) {
        Intent intent = new Intent(FourthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleUpdateInStock(int updatedStock) {
        stockLeft = updatedStock;
        sharedPreferences.edit().putInt("stockLeft", stockLeft).apply();
        stockLeftTextView.setText(String.valueOf(stockLeft));
        List<Fragment> fragments = new ArrayList<>(getSupportFragmentManager().getFragments());
        for (int index = 0; index < fragments.size(); index++) {
            Fragment currentFragment = fragments.get(index);
            if (currentFragment != null) {
                if (currentFragment.getTag().equals("FEEDING_FRAGMENT")) {
                    ((FeedingFragment) currentFragment).updateStockFeedingFragment(stockLeft);
                } else if (currentFragment.getTag().equals("SHOP_FRAGMENT")) {
                    ((ShopFragment) currentFragment).updateStockShopFragment(stockLeft);
                }
            }
        }
    }

    public void increaseStock(View view) {
        stockLeft += 1;
        handleUpdateInStock(stockLeft);
    }

    public void decreaseStock(View view) {
        if (stockLeft >= 1) {
            stockLeft -= 1;
            handleUpdateInStock(stockLeft);
        } else {
            callShopFragment();
        }
    }

    private void handleUpdateInPrice(int updatedPrice) {
        itemPrice = updatedPrice;
        sharedPreferences.edit().putInt("itemPrice", itemPrice).apply();
        itemPriceTextView.setText(String.valueOf(itemPrice));
        List<Fragment> fragments = new ArrayList<>(getSupportFragmentManager().getFragments());
        for (int index = 0; index < fragments.size(); index++) {
            Fragment currentFragment = fragments.get(index);
            if (currentFragment != null) {
                if (currentFragment.getTag().equals("SHOP_FRAGMENT")) {
                    ((ShopFragment) currentFragment).updatePriceShopFragment(itemPrice);
                }
            }
        }
    }

    public void increasePrice(View view) {
        itemPrice += 10;
        handleUpdateInPrice(itemPrice);
    }

    public void decreasePrice(View view) {
        if (itemPrice >= 10) {
            itemPrice -= 10;
            handleUpdateInPrice(itemPrice);
        } else {
            Toast.makeText(this, "Price cannot be decreased any further", Toast.LENGTH_SHORT).show();
        }
    }

    public void openFeedingFragment(View view) {
        FeedingFragment fragment = new FeedingFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.full_screen_fragment_container_fourth_activity,
                fragment, "FEEDING_FRAGMENT");
        Log.i("Custom Debugging", "Inside method openFeedingFragment(View view)");
        List<Fragment> fragments = new ArrayList<>(getSupportFragmentManager().getFragments());
        for (int index = 0; index < fragments.size(); index++) {
            Fragment currentFragment = fragments.get(index);
            if (currentFragment != null) {
                Log.i("Custom Debugging", "fragment at index = " + index + " is NOT null");
                Log.i("Custom Debugging", "tag of current fragment = " + currentFragment.getTag());
            } else {
                Log.i("Custom Debugging", "fragment at index = " + index + " is null");
            }
        }
        transaction.commit();
    }

    private void callShopFragment() {
        ShopFragment fragment = new ShopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.full_screen_fragment_container_fourth_activity,
                fragment, "SHOP_FRAGMENT");
        Log.i("Custom Debugging", "Inside method callShopFragment()");
        List<Fragment> fragments = new ArrayList<>(getSupportFragmentManager().getFragments());
        for (int index = 0; index < fragments.size(); index++) {
            Fragment currentFragment = fragments.get(index);
            if (currentFragment != null) {
                Log.i("Custom Debugging", "fragment at index = " + index + " is NOT null");
                Log.i("Custom Debugging", "tag of current fragment = " + currentFragment.getTag());
            } else {
                Log.i("Custom Debugging", "fragment at index = " + index + " is null");
            }
        }
        transaction.commit();
    }

    public void openShopFragment(View view) {
        callShopFragment();
    }

    /** Methods to override of fragments **/
    @Override
    public void onFeedingFragmentInteractionBackClicked() {
        onBackPressed();
    }

    @Override
    public void onFeedingFragmentInteractionOutOfStock() {
        callShopFragment();
    }

    @Override
    public void onFeedingFragmentInteractionOpenShopFragment() {
        callShopFragment();
    }

    @Override
    public void onFeedingFragmentInteractionUpdateStock(int updatedStock) {
        handleUpdateInStock(updatedStock);
    }

    @Override
    public void onShopFragmentInteractionBackClicked() {
        onBackPressed();
    }

    @Override
    public void onShopFragmentInteractionOutOfStock() {
        /* This code block is not useful as of now w.r.t. the 'Feed the Animal' project.
           Since, ShopFragment is made to buy stuff
        */
        Toast.makeText(this, "We are currently out of stock", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShopFragmentInteractionUpdateStock(int updatedStock) {
        handleUpdateInStock(updatedStock);
    }

    @Override
    public void onShopFragmentInteractionPriceDeductionBelowLimit() {
        /* This code block is not useful as of now w.r.t. the 'Feed the Animal' project.
           Since, user is never given to option to change the prices of the goods that he may purchase in the app
        */
        Toast.makeText(this, "Price cannot be decreased any further", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShopFragmentInteractionUpdatePrice(int updatedPrice) {
        handleUpdateInPrice(updatedPrice);
    }
}