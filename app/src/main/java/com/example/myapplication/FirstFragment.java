package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView show_count_text_view;


    private void count_me(View view){
        // Get the value of the text view
        String countString = show_count_text_view.getText().toString();
        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        show_count_text_view.setText(count.toString());
    }

    //init the view
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        // Get the count text view
        show_count_text_view = fragmentFirstLayout.findViewById(R.id.textview_first);
//        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return fragmentFirstLayout;
//        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //switch between first and second views
        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                //get the count
//                int currentCount = Integer.parseInt(show_count_text_view.getText().toString());
//                FragmentFirstBinding.d action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
//                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
            }
        });

        //when you click the toast button this will listen and if clicked well show hello toast
        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
                MessageSender messageSender =new MessageSender();
                messageSender.execute("Hello");
            }
        });

        view.findViewById(R.id.count_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_me(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}