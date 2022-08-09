package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.databinding.FragmentPerformanceBinding;
import com.example.myapplication.databinding.PtiModuleBinding;
import com.example.myapplication.model.MainEntryViewModel;

import java.util.ArrayList;
import java.util.List;


public class Performance extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FragmentPerformanceBinding binding;
    private static final String performance_declare = "PERFORMANCE";

    private List<String> airspeed_command;
    private List<String> altitude_command;

    private List<String> flight_card;
    private String flight_card_val;
    private int card_num = R.drawable.b_test_card_1;
    private List<String> module;
    private String module_val;


    // TODO: Rename and change types of parameters
    private String airspeed_val;
    private String altitude_val;

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
        View view = inflater.inflate(R.layout.fragment_performance, container, false);
        View root = view.getRootView();

        airspeed_command = new ArrayList<>();
        airspeed_command.add("Airspeed Command");
        airspeed_command.add("Min Airspeed");
        airspeed_command.add("Cruise Airspeed");
        airspeed_command.add("Max Airspeed");

        altitude_command = new ArrayList<>();
        altitude_command.add("Altitude Command");
        altitude_command.add("75m AGL");
        altitude_command.add("100m AGL");
        altitude_command.add("125m AGL");
        altitude_command.add("150m AGL");

        flight_card = new ArrayList<>();
        flight_card.add(0, "Change Card");
        flight_card.add("0");
        flight_card.add("1");
        flight_card.add("2");
        flight_card.add("3");
        flight_card.add("4");
        flight_card.add("5");
        flight_card.add("6");

        module = new ArrayList<>();
        module.add("Select Module");
        module.add("Module 1 Performance");
        module.add("Module 2 Dynamics");


        Spinner airspeed_command_spinner = view.findViewById(R.id.airspeed_spinner);
        ArrayAdapter<String> airspeed_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, airspeed_command);
        // Specify the layout to use when the list of choices appears
        airspeed_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        airspeed_command_spinner.setAdapter(airspeed_adapter);
        airspeed_command_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Injection Point")) {
                    //do nothing
                } else {
                    String item = adapterView.getItemAtPosition(i).toString();
                    //Toast.makeText(adapterView.getContext(), "Selected : " + item, Toast.LENGTH_SHORT).show();
                    airspeed_val = adapterView.getItemAtPosition(i).toString();

                    if (airspeed_val == "Min Airspeed"){
                        airspeed_val = "MINAirspeed";
                    }
                    if (airspeed_val == "Cruise Airspeed"){
                        airspeed_val = "CRUISEAirspeed";
                    }
                    if (airspeed_val == "Max Airspeed"){
                        airspeed_val = "MAXAirspeed";
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner altitude_command_spinner = view.findViewById(R.id.altitude_spinner);
        ArrayAdapter<String> altitude_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, altitude_command);
        // Specify the layout to use when the list of choices appears
        altitude_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        altitude_command_spinner.setAdapter(altitude_adapter);
        altitude_command_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Altitude Command")) {
                    //do nothing
                } else {
                    String item = adapterView.getItemAtPosition(i).toString();
//                    Toast.makeText(adapterView.getContext(), "Selected : " + item, Toast.LENGTH_SHORT).show();
                    altitude_val = adapterView.getItemAtPosition(i).toString();

                    if (altitude_val == "75m AGL"){
                        altitude_val = "75m";
                    }
                    if (altitude_val == "100m AGL"){
                        altitude_val = "100m";
                    }
                    if (altitude_val == "125m AGL"){
                        altitude_val = "125m";
                    }
                    if (altitude_val == "150m AGL"){
                        altitude_val = "150m";
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //spinner to change flight card
        Spinner flight_card_spinner = view.findViewById(R.id.perf_flight_card_spinner);
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
                        card_num = R.drawable.b_test_card_1;
                    }
                    if (flight_card_val == "1") {
                        card_num = R.drawable.b_test_card_1;
                    }
                    if (flight_card_val == "2") {
                        card_num = R.drawable.c_test_card_2;
                    }
                    if (flight_card_val == "3") {
                        card_num = R.drawable.d_test_card_3;
                    }
                    if (flight_card_val == "4") {
                        card_num = R.drawable.e_test_card_4;
                    }
                    if (flight_card_val == "5") {
                        card_num = R.drawable.f_test_card_5;
                    }
                    if (flight_card_val == "6") {
                        card_num = R.drawable.g_test_card_6;
                    }
                    //iv.setImageResource(drawableID);
//                    int drawableResourceId = getResources().getIdentifier(flight_card_val,
//                            "drawable", );
                    root.setBackgroundResource(card_num);
                    Toast.makeText(adapterView.getContext(), "Selected : " + card_num, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //switches module
        Spinner switch_module_spinner = view.findViewById(R.id.perf_switch_module_spinner);
        ArrayAdapter<String> switch_module_adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, module);
        // Specify the layout to use when the list of choices appears
        switch_module_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        switch_module_spinner.setAdapter(switch_module_adapter);
        switch_module_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Module 1 Performance")) {
                    //do nothing
                } else {
                    module_val = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), "Selected : " + module_val, Toast.LENGTH_SHORT).show();
                    if (module_val == "Module 2 Dynamics") {
                        Navigation.findNavController(view).navigate(R.id.action_Performance_to_PTIModule);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        //when you click the go button begins the test
        view.findViewById(R.id.send_command_button).setOnClickListener(new View.OnClickListener(){
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
                messageSender.execute(performance_declare + " " + time.getValue() + " "
                        + vel.getValue() + " "
                        + airspeed_val + " "
                        + altitude_val);
            }
        });

        return view;
    }
}