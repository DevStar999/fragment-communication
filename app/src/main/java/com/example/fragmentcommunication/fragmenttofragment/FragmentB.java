package com.example.fragmentcommunication.fragmenttofragment;

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

import com.example.fragmentcommunication.R;

public class FragmentB extends Fragment {
    private FragmentBListener listener;
    private AppCompatEditText editTextFragmentB;
    private AppCompatButton buttonOkFragmentB;

    /*  Remember to communicate from this fragment i.e. 'FragmentB' to the activity we are using
        the following interface
    */
    // i.e. Communication from Fragment to Parent Activity
    public interface FragmentBListener {
        void onInputBSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        editTextFragmentB = view.findViewById(R.id.edit_text_fragment_b);
        buttonOkFragmentB = view.findViewById(R.id.button_ok_fragment_b);

        buttonOkFragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = editTextFragmentB.getText();
                listener.onInputBSent(input);
            }
        });

        return view;
    }

    // To get data from the parent activity into this fragment 'FragmentB'
    // i.e. Communication from Parent Activity to Fragment
    public void updateEditText(CharSequence newText) {
        editTextFragmentB.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener) {
            listener = (FragmentBListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentBListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
