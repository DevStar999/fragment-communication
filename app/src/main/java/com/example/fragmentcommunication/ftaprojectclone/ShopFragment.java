package com.example.fragmentcommunication.ftaprojectclone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.example.fragmentcommunication.R;

public class ShopFragment extends Fragment {
    private Context context;
    private OnShopFragmentInteractionListener mListener;
    private SharedPreferences sharedPreferences;
    private AppCompatImageView backButton;

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

        settingOnClickListeners();

        return view;
    }

    public interface OnShopFragmentInteractionListener {
        void onShopFragmentInteractionBackClicked();
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
