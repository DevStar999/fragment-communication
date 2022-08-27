package com.example.fragmentcommunication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private FragmentAListener listener;
    private AppCompatEditText editTextFragmentA;
    private AppCompatButton buttonOkFragmentA;

    /*  Remember to communicate from this fragment i.e. 'FragmentA' to the activity we are using
        the following interface
    */
    // i.e. Communication from Fragment to Parent Activity
    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        editTextFragmentA = view.findViewById(R.id.edit_text_fragment_a);
        buttonOkFragmentA = view.findViewById(R.id.button_ok_fragment_a);

        buttonOkFragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = editTextFragmentA.getText();
                listener.onInputASent(input);
            }
        });

        return view;
    }

    // To get data from the parent activity into this fragment 'FragmentA'
    // i.e. Communication from Parent Activity to Fragment
    public void updateEditText(CharSequence newText) {
        editTextFragmentA.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
