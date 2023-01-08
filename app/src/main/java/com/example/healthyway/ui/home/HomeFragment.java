package com.example.healthyway.ui.home;

import static android.content.Context.SENSOR_SERVICE;

import android.app.AlertDialog;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthyway.R;
import com.example.healthyway.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements SensorEventListener {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private FragmentHomeBinding binding;
    private SensorManager sensorManager;
    private boolean running  = false;
    private Float totalSteps = 0f;
    private Float previousTotalSteps = 0f;
    private Float totalKcal = 0f;
    private EditText inputKcal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sensorManager = (SensorManager) requireContext().getSystemService(SENSOR_SERVICE);


        loadData();
        resetSteps();
        addKcal();

        return root;
    }

    private void addKcal() {
        final TextView tv_kcal = binding.tvKcal;
        tv_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAddKcalDialog();
            }
        });
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

            final TextView tv_kcal = binding.tvKcal;
            tv_kcal.setText(totalKcal.toString());
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
                saveStepsData();
                return true;
            }
        });
    }

    private void saveStepsData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("stepsPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();
    }

    private  void loadData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("stepsPreferences", Context.MODE_PRIVATE);
        previousTotalSteps = sharedPreferences.getFloat("key1", 0f);
        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("kcalPreferences", Context.MODE_PRIVATE);
        totalKcal = sharedPreferences2.getFloat("kcal", 0f);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void createAddKcalDialog(){
        dialogBuilder = new AlertDialog.Builder(this.requireContext());
        final View addKcalPopupView = getLayoutInflater().inflate(R.layout.fragment_addkcal, null);

        inputKcal = (EditText) addKcalPopupView.findViewById(R.id.inputKcal);
        loadData();

        Button button = (Button) addKcalPopupView.findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Float kcalNr = Float.parseFloat(String.valueOf(inputKcal.getText()));
                    totalKcal = totalKcal + kcalNr;
                    saveKcalData();
                    Toast.makeText(getActivity(), "Kcal added", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    final TextView tv_kcal = binding.tvKcal;
                    tv_kcal.setText(totalKcal.toString());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getActivity(), "The value must be numeric", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setView(addKcalPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
    }

    private void saveKcalData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("kcalPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("kcal", totalKcal);
        editor.apply();
    }
}