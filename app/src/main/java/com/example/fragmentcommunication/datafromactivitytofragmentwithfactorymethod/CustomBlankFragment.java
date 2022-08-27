package com.example.fragmentcommunication.datafromactivitytofragmentwithfactorymethod;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.fragmentcommunication.R;

public class CustomBlankFragment extends Fragment {
    private static final String ARG_TEXT = "argText";
    private static final String ARG_NUMBER = "argNumber";

    private String text;
    private int number;

    private AppCompatTextView textViewCustomBlankFragment;

    public CustomBlankFragment() {
    }

    public static CustomBlankFragment newInstance(String text, int number) {
        CustomBlankFragment fragment = new CustomBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_blank, container, false);
        textViewCustomBlankFragment = view.findViewById(R.id.text_view_custom_blank_fragment);

        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
            number = getArguments().getInt(ARG_NUMBER);
        }

        textViewCustomBlankFragment.setText(text + number);

        return view;
    }
}
