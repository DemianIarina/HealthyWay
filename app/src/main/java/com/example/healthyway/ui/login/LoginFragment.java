package com.example.healthyway.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthyway.R;

public class LoginFragment extends Fragment {

    Button buttonLogin, buttonRegister, buttonRegisterWithCode;
    EditText editTextEmail, editTextPassword;
    CallbackFragment callbackFragment;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);

        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonRegister = view.findViewById(R.id.buttonRegister);
        buttonRegisterWithCode = view.findViewById(R.id.buttonRegisterWithCode);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callbackFragment!=null){
                    callbackFragment.changeFragment(1);
                }
            }
        });

        buttonRegisterWithCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callbackFragment!=null){
                    callbackFragment.changeFragment(2);
                }
            }
        });


        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;    }
}
