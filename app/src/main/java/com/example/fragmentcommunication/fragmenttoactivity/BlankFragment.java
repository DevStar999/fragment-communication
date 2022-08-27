package com.example.fragmentcommunication.fragmenttoactivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.example.fragmentcommunication.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private static final String TEXT = "text";

    private String text;

    private OnFragmentInteractionListener mListener;

    private AppCompatEditText editTextBlankFragment;
    private AppCompatButton sendInfoBackBlankFragment;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String text) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.text = getArguments().getString(TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        editTextBlankFragment = view.findViewById(R.id.edit_text_blank_fragment);
        sendInfoBackBlankFragment = view.findViewById(R.id.send_button_blank_fragment);
        editTextBlankFragment.setText(this.text);
        /* Adding the following line so that it gets focus automatically when the fragment is
        opened
        */
        editTextBlankFragment.requestFocus();

        sendInfoBackBlankFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendBackText = Objects.requireNonNull(editTextBlankFragment.getText()).toString();
                sendBackTextFromFragment(sendBackText);
            }
        });

        return view;
    }

    public void sendBackTextFromFragment(String sendBackText) {
        if (mListener != null) {
            // We are sending the text from the Fragment to the Parent Activity from here
            mListener.onFragmentInteraction(sendBackText);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String sendBackText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}