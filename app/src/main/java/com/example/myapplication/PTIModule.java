package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.databinding.PtiModuleBinding;
import com.example.myapplication.model.MainEntryViewModel;

import java.util.ArrayList;
import java.util.List;


/*PTI MODULE SENDS UDP MESSAGE AS FOLLOWS:
* TEST TYPE : FREQ SWEEP
* TIME : Seconds
* VELOCITY: M/S
* INJECTION POINT: 0 - 9
* AMP LEVEL : LOW MEDIUM HIGH
* */

public class PTIModule extends Fragment {

    private PtiModuleBinding binding;
    private static final String freq_sweep = "FREQ_SWEEP";
    private List<String> injection_point;
    private List<String> loop_gain;
    private List<String> levels;
    private List<String> flight_card;
    private List<String> module;

    private String injection_val;
    private String level_val;
    private String flight_card_val;
    private String loop_gain_val;

    private int card_num = R.drawable.b_dyn_card_p2;

    private String module_val;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //binding = PtiModuleBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.pti_module, container, false);
        View root = view.getRootView();

        //these things need to be factored
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

        loop_gain = new ArrayList<>();
        loop_gain.add("Baseline Gain");
        loop_gain.add("+20% INCREASE");
        loop_gain.add("-20% DECREASE");

        levels = new ArrayList<>();
        levels.add(0, "Set Amplitude");
        levels.add("Low");
        levels.add("Medium");
        levels.add("High");

        flight_card = new ArrayList<>();
        flight_card.add(0, "Change Card");
        flight_card.add("0");
        flight_card.add("1");
        flight_card.add("2");
        flight_card.add("3");
        flight_card.add("4");
        flight_card.add("5");
        flight_card.add("6");
        flight_card.add("7");
        flight_card.add("8");
        flight_card.add("9");
        flight_card.add("10");
        flight_card.add("11");
        flight_card.add("12");
        flight_card.add("13");
        flight_card.add("14");
        flight_card.add("15");
        flight_card.add("16");

        module = new ArrayList<>();
        module.add("Select Module");
        module.add("Module 1 Performance");
        module.add("Module 2 Dynamics");

        //injection dropdown
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
                    injection_val = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //changes the level of the input
        Spinner loop_gain_spinner = view.findViewById(R.id.loop_gain_spinner);
        ArrayAdapter<String> loop_gain_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, loop_gain);
        // Specify the layout to use when the list of choices appears
        loop_gain_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loop_gain_spinner.setAdapter(loop_gain_adapter);
        loop_gain_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Injection Point")) {
                    //do nothing
                } else {
                    loop_gain_val = adapterView.getItemAtPosition(i).toString();

                    if (loop_gain_val == "Baseline Gain"){
                        loop_gain_val = "BASE";
                    }

                    if (loop_gain_val == "+20% INCREASE"){
                        loop_gain_val = "INCREASE";
                    }

                    if (loop_gain_val == "-20% DECREASE"){
                        loop_gain_val = "DECREASE";
                    }

                    Toast.makeText(adapterView.getContext(), "Selected : " + loop_gain_val, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //changes the level of the input
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
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //spinner to change flight card
        Spinner flight_card_spinner = view.findViewById(R.id.flight_card_spinner);
        ArrayAdapter<String> flight_card_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, flight_card);
        // Specify the layout to use when the list of choices appears
        flight_card_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flight_card_spinner.setAdapter(flight_card_adapter);
        flight_card_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Injection Point")) {
                    //do nothing
                } else {
                    flight_card_val = adapterView.getItemAtPosition(i).toString();
                    if (flight_card_val == "0") {
                        card_num = R.drawable.b_dyn_card_p2;
                    }
                    if (flight_card_val == "1") {
                        card_num = R.drawable.b_dyn_card_p2;
                    }
                    if (flight_card_val == "2") {
                        card_num = R.drawable.c_dyn_card_2p1;
                    }
                    if (flight_card_val == "3") {
                        card_num = R.drawable.d_dyn_card_2p2;
                    }
                    if (flight_card_val == "4") {
                        card_num = R.drawable.e_dyn_card_2p3;
                    }
                    if (flight_card_val == "5") {
                        card_num = R.drawable.g_dyn_card_3p2;
                    }
                    if (flight_card_val == "6") {
                        card_num = R.drawable.h_dyn_card_3p3;
                    }
                    if (flight_card_val == "7") {
                        card_num = R.drawable.i_flight_card_4p1;
                    }
                    if (flight_card_val == "8") {
                        card_num = R.drawable.j_dyn_card_4p2;
                    }
                    if (flight_card_val == "9") {
                        card_num = R.drawable.k_dyn_card_4p3;
                    }
                    if (flight_card_val == "10") {
                        card_num = R.drawable.l_dyn_card_5p1;
                    }
                    if (flight_card_val == "11") {
                        card_num = R.drawable.m_dyn_card_5p2;
                    }
                    if (flight_card_val == "12") {
                        card_num = R.drawable.n_dyn_card_5p3;
                    }
                    if (flight_card_val == "13") {
                        card_num = R.drawable.o_dyn_card_6p1;
                    }
                    if (flight_card_val == "14") {
                        card_num = R.drawable.p_dyn_card_6p2;
                    }
                    if (flight_card_val == "15") {
                        card_num = R.drawable.q_dyn_card_6p3;
                    }
                    if (flight_card_val == "16") {
                        card_num = R.drawable.r_dyn_card_7;
                    }
                    //iv.setImageResource(drawableID);
//                    int drawableResourceId = getResources().getIdentifier(flight_card_val,
//                            "drawable", );
                    root.setBackgroundResource(card_num);
//                    Toast.makeText(adapterView.getContext(), "Selected : " + card_num, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //switches module
        Spinner switch_module_spinner = view.findViewById(R.id.switch_module_spinner);
        ArrayAdapter<String> switch_module_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, module);
        // Specify the layout to use when the list of choices appears
        switch_module_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        switch_module_spinner.setAdapter(switch_module_adapter);
        switch_module_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Injection Point")) {
                    //do nothing
                } else {
                    module_val = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), "Selected : " + module_val, Toast.LENGTH_SHORT).show();
                    if (module_val == "Module 1 Performance") {
                        Navigation.findNavController(view).navigate(R.id.action_PTIModule_to_Performance);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //when you click the go button begins the test
        view.findViewById(R.id.go_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainEntryViewModel main_entry_view = new ViewModelProvider(requireActivity()).get(MainEntryViewModel.class);
                MutableLiveData<String> ip = main_entry_view.getIP();
                MutableLiveData<String> vel = main_entry_view.getVel();
                MutableLiveData<String> time = main_entry_view.getTime();

                Toast myToast = Toast.makeText(getActivity(), ip.getValue(), Toast.LENGTH_SHORT);
                myToast.show();
                MessageSender messageSender =new MessageSender();
                messageSender.setIP(ip.getValue());
                messageSender.execute(freq_sweep + " " + time.getValue() + " "
                        + vel.getValue() + " "
                        + injection_val + " "
                        + level_val + " "
                        + loop_gain_val);
            }
        });

        //when you click the go button begins the test
        view.findViewById(R.id.end_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                MainEntryViewModel main_entry_view = new ViewModelProvider(requireActivity()).get(MainEntryViewModel.class);
                MutableLiveData<String> ip = main_entry_view.getIP();

                MessageSender messageSender =new MessageSender();
                messageSender.setIP(ip.getValue());
                messageSender.execute(freq_sweep + " " + "END");
            }
        });
        return view;
    }
}