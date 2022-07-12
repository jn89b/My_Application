package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.databinding.PtiModuleBinding;

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;



public class PTIModule extends Fragment {

    private PtiModuleBinding binding;
    private static final String freq_sweep = "FREQ_SWEEP";
    private List<String> injection_point;
    private List<String> levels;

    private String injection_val;
    private String level_val;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //binding = PtiModuleBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.pti_module, container, false);

        injection_point = new ArrayList<>();
        injection_point.add(0, "Injection Point");
        injection_point.add("0");
        injection_point.add("1");
        injection_point.add("2");
        injection_point.add("3");
        injection_point.add("4");
        injection_point.add("5");
        injection_point.add("6");
        injection_point.add("7");
        injection_point.add("8");
        injection_point.add("9");

        levels = new ArrayList<>();
        levels.add(0, "Set Amplitude");
        levels.add("Low");
        levels.add("Medium");
        levels.add("High");


        Spinner injection_spinner = view.findViewById(R.id.injection_spinner);
        ArrayAdapter<String> inject_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, injection_point);
        // Specify the layout to use when the list of choices appears
        inject_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        injection_spinner.setAdapter(inject_adapter);
        injection_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Injection Point")) {
                    //do nothing
                } else {
                    String item = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), "Selected : " + item, Toast.LENGTH_SHORT).show();
//                    MessageSender messageSender =new MessageSender();
//                    messageSender.execute(item);
                    injection_val = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner level_spinner = view.findViewById(R.id.injection_level);
        ArrayAdapter<String> level_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, levels);
        // Specify the layout to use when the list of choices appears
        level_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level_spinner.setAdapter(level_adapter);
        level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Injection Point")) {
                    //do nothing
                } else {
                    level_val = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), "Selected : " + level_val, Toast.LENGTH_SHORT).show();
//                    MessageSender messageSender =new MessageSender();
//                    messageSender.execute(level_val);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        //when you click the toast button this will listen and if clicked well show hello toast
        view.findViewById(R.id.go_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
//                myToast.show();
                MessageSender messageSender =new MessageSender();
                messageSender.execute(freq_sweep+ " " + injection_val + " " + level_val);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}