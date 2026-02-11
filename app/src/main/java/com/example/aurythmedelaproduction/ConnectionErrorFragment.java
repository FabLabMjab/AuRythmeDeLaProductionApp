package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ConnectionErrorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View v = inflater.inflate(
                R.layout.fragment_connection_error,
                container,
                false
        );

        Button retry = v.findViewById(R.id.btnRetry);

        retry.setOnClickListener(view -> {

            if (getActivity() instanceof LogIn) {
                ((LogIn) getActivity()).retryConnection();
            }
        });

        return v;
    }
}