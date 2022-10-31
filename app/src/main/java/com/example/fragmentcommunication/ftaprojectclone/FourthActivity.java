package com.example.fragmentcommunication.ftaprojectclone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentcommunication.R;
import com.example.fragmentcommunication.fragmenttofragment.MainActivity;

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
    }

    /** onClick listeners for the views of this activity's layout **/
    public void gotoMainActivity(View view) {
        Intent intent = new Intent(FourthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void increaseStock(View view) {
        stockLeft += 1;
        stockLeftTextView.setText(String.valueOf(stockLeft));
        sharedPreferences.edit().putInt("stockLeft", stockLeft).apply();
        // TODO -> Call to method of FeedingFragment to update the value of stockLeft
    }

    public void decreaseStock(View view) {
        if (stockLeft >= 1) {
            stockLeft -= 1;
            stockLeftTextView.setText(String.valueOf(stockLeft));
            sharedPreferences.edit().putInt("stockLeft", stockLeft).apply();
            // TODO -> Call to method of FeedingFragment to update the value of stockLeft
        } else {
            callShopFragment();
        }
    }

    public void increasePrice(View view) {
        itemPrice += 10;
        itemPriceTextView.setText(String.valueOf(itemPrice));
        sharedPreferences.edit().putInt("itemPrice", itemPrice).apply();
    }

    public void decreasePrice(View view) {
        if (itemPrice >= 10) {
            itemPrice -= 10;
            itemPriceTextView.setText(String.valueOf(itemPrice));
            sharedPreferences.edit().putInt("itemPrice", itemPrice).apply();
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
                fragment, "FEEDING_FRAGMENT").commit();
    }

    private void callShopFragment() {
        ShopFragment fragment = new ShopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.full_screen_fragment_container_fourth_activity,
                fragment, "SHOP_FRAGMENT").commit();
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
    public void onShopFragmentInteractionBackClicked() {
        onBackPressed();
    }
}