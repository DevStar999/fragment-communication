package com.example.fragmentcommunication.ftaprojectclone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.fragmentcommunication.R;

public class FeedingFragment extends Fragment {
    private Context context;
    private OnFeedingFragmentInteractionListener mListener;
    private SharedPreferences sharedPreferences;
    private AppCompatImageView backButton;
    private int stockLeft;
    private AppCompatTextView stockLeftTextView;
    private AppCompatButton increaseStock;
    private AppCompatButton decreaseStock;
    private AppCompatButton openShopFragmentButton;

    public FeedingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void settingOnClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFeedingFragmentInteractionBackClicked();
                }
            }
        });
        increaseStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockLeft += 1;
                stockLeftTextView.setText(String.valueOf(stockLeft));
                sharedPreferences.edit().putInt("stockLeft", stockLeft).apply();
            }
        });
        decreaseStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stockLeft >= 1) {
                    stockLeft -= 1;
                    stockLeftTextView.setText(String.valueOf(stockLeft));
                    sharedPreferences.edit().putInt("stockLeft", stockLeft).apply();
                    // TODO -> Call to method of FeedingFragment to update the value of stockLeft
                } else {
                    if (mListener != null) {
                        mListener.onFeedingFragmentInteractionOutOfStock();
                    }
                }
            }
        });
        openShopFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFeedingFragmentInteractionOpenShopFragment();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requireActivity().getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        sharedPreferences = context.getSharedPreferences("com.example.fragmentcommunication", Context.MODE_PRIVATE);

        View view = inflater.inflate(R.layout.fragment_feeding, container, false);

        backButton = view.findViewById(R.id.title_back_feeding_fragment_button);
        stockLeft = sharedPreferences.getInt("stockLeft", 20);
        stockLeftTextView = view.findViewById(R.id.stock_left_value_text_view_feeding_fragment);
        stockLeftTextView.setText(String.valueOf(stockLeft));
        increaseStock = view.findViewById(R.id.increase_stock_button_feeding_fragment);
        decreaseStock = view.findViewById(R.id.decrease_stock_button_feeding_fragment);
        openShopFragmentButton = view.findViewById(R.id.open_shop_fragment_button_feeding_fragment);

        settingOnClickListeners();

        return view;
    }

    public interface OnFeedingFragmentInteractionListener {
        void onFeedingFragmentInteractionBackClicked();
        void onFeedingFragmentInteractionOutOfStock();
        void onFeedingFragmentInteractionOpenShopFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFeedingFragmentInteractionListener) {
            mListener = (OnFeedingFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFeedingFragmentInteractionListener");
        }
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
