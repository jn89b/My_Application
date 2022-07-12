package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.MainEntryBinding;

public class MainEntry extends Fragment {

    private static final String IP_ADDRESS_KEY = "ip_address_key";
    private static final String TEST_TIME_KEY = "test_time_key";
    private static final String VELOCITY_KEY = "velocity_key";

    private MainEntryBinding binding;

    EditText ip_address_entry;
    private String ip_val;

    EditText time_entry;
    private String time_val;

    EditText velocity_entry;
    private String velocity_val;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ip_val = savedInstanceState.getString(IP_ADDRESS_KEY);
            ip_address_entry.setText(ip_val);

            time_val = savedInstanceState.getString(TEST_TIME_KEY);
            time_entry.setText(time_val);

            velocity_val = savedInstanceState.getString(VELOCITY_KEY);
            velocity_entry.setText(velocity_val);
        }
    }

    //init the view
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.main_entry, container, false);
        ip_address_entry = view.findViewById(R.id.ip_address_entry);

        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //switch between first and second views
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainEntry.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //saves to hashtable outstate consisting of key and value pair of the value
        outState.putString(IP_ADDRESS_KEY, String.valueOf(ip_address_entry.getText().toString()));
        outState.putString(VELOCITY_KEY, String.valueOf(velocity_entry.getText().toString()));
        outState.putString(TEST_TIME_KEY, String.valueOf(time_entry.getText().toString()));
        Log.d("Value", outState.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}