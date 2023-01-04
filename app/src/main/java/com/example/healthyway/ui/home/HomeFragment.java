package com.example.healthyway.ui.home;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthyway.databinding.FragmentDashboardBinding;
import com.example.healthyway.databinding.FragmentHomeBinding;
import com.example.healthyway.ui.dashboard.DashboardViewModel;

public class HomeFragment extends Fragment implements SensorEventListener {

    private FragmentHomeBinding binding;
    private SensorManager sensorManager;
    private boolean running  = false;
    private Float totalSteps = 0f;
    private Float previousTotalSteps = 0f;
    //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        sensorManager = (SensorManager) requireContext().getSystemService(SENSOR_SERVICE);


        loadData();
        resetSteps();

                return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {

        super.onResume();

        running = true;
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(stepSensor == null){
            Toast.makeText(getActivity(), "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            // Rate suitable for the user interface
            sensorManager.registerListener((SensorEventListener) this, stepSensor, SensorManager.SENSOR_DELAY_UI);
            final TextView tv_stepsTaken = binding.tvStepsTaken;
            Integer currentSteps = totalSteps.intValue() - previousTotalSteps.intValue();

            tv_stepsTaken.setText(currentSteps.toString());
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final TextView tv_stepsTaken = binding.tvStepsTaken;

        if(running){
            totalSteps = sensorEvent.values[0];

            Integer currentSteps = totalSteps.intValue() - previousTotalSteps.intValue();

            tv_stepsTaken.setText(currentSteps.toString());
        }

    }

    private void resetSteps(){
        final TextView tv_stepsTaken = binding.tvStepsTaken;
        tv_stepsTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Long tap to reset steps", Toast.LENGTH_SHORT).show();
            }
        });

        tv_stepsTaken.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                previousTotalSteps = totalSteps;
                tv_stepsTaken.setText("0");
                saveData();
                return true;
            }
        });
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("stepsPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();
    }

    private  void loadData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("stepsPreferences", Context.MODE_PRIVATE);
        previousTotalSteps = sharedPreferences.getFloat("key1", 0f);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}