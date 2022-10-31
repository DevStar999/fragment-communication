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

public class ShopFragment extends Fragment {
    private Context context;
    private OnShopFragmentInteractionListener mListener;
    private SharedPreferences sharedPreferences;
    private AppCompatImageView backButton;
    private int stockLeft;
    private AppCompatTextView stockLeftTextView;
    private AppCompatButton increaseStock;
    private AppCompatButton decreaseStock;
    private int itemPrice;
    private AppCompatTextView itemPriceTextView;
    private AppCompatButton increasePrice;
    private AppCompatButton decreasePrice;

    public ShopFragment() {
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
                    mListener.onShopFragmentInteractionBackClicked();
                }
            }
        });
        increaseStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockLeft += 1;
                if (mListener != null) {
                    mListener.onShopFragmentInteractionUpdateStock(stockLeft);
                }
            }
        });
        decreaseStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stockLeft >= 1) {
                    stockLeft -= 1;
                    if (mListener != null) {
                        mListener.onShopFragmentInteractionUpdateStock(stockLeft);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onShopFragmentInteractionOutOfStock();
                    }
                }
            }
        });
        increasePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemPrice += 10;
                if (mListener != null) {
                    mListener.onShopFragmentInteractionUpdatePrice(itemPrice);
                }
            }
        });
        decreasePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemPrice >= 10) {
                    itemPrice -= 10;
                    if (mListener != null) {
                        mListener.onShopFragmentInteractionUpdatePrice(itemPrice);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onShopFragmentInteractionPriceDeductionBelowLimit();
                    }
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

        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        backButton = view.findViewById(R.id.title_back_shop_fragment_button);
        stockLeft = sharedPreferences.getInt("stockLeft", 20);
        stockLeftTextView = view.findViewById(R.id.stock_left_value_text_view_shop_fragment);
        stockLeftTextView.setText(String.valueOf(stockLeft));
        increaseStock = view.findViewById(R.id.increase_stock_button_shop_fragment);
        decreaseStock = view.findViewById(R.id.decrease_stock_button_shop_fragment);
        itemPrice = sharedPreferences.getInt("itemPrice", 100);
        itemPriceTextView = view.findViewById(R.id.item_price_value_text_view_shop_fragment);
        itemPriceTextView.setText(String.valueOf(itemPrice));
        increasePrice = view.findViewById(R.id.increase_item_price_button_shop_fragment);
        decreasePrice = view.findViewById(R.id.decrease_item_price_button_shop_fragment);

        settingOnClickListeners();

        return view;
    }

    public void updateStockShopFragment(int updatedStock) {
        sharedPreferences.edit().putInt("stockLeft", updatedStock).apply();
        if (mListener != null) {
            stockLeft = updatedStock;
            stockLeftTextView.setText(String.valueOf(stockLeft));
        }
    }

    public void updatePriceShopFragment(int updatedPrice) {
        sharedPreferences.edit().putInt("itemPrice", updatedPrice).apply();
        if (mListener != null) {
            itemPrice = updatedPrice;
            itemPriceTextView.setText(String.valueOf(updatedPrice));
        }
    }

    public interface OnShopFragmentInteractionListener {
        void onShopFragmentInteractionBackClicked();
        void onShopFragmentInteractionOutOfStock();
        void onShopFragmentInteractionUpdateStock(int updatedStock);
        void onShopFragmentInteractionPriceDeductionBelowLimit();
        void onShopFragmentInteractionUpdatePrice(int updatedPrice);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnShopFragmentInteractionListener) {
            mListener = (OnShopFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnShopFragmentInteractionListener");
        }
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
