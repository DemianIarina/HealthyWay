package com.example.healthyway.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthyway.Entities.UserEntity;
import com.example.healthyway.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterWithCodeFragment extends Fragment{
    Button buttonRegister;
    EditText editTextPassword, editTextEmail, editDietitianCode, editTextPasswordRetype;
    DatabaseReference reference;
    UserEntity user;

    long maxid = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registerwithcode_fragment, container, false);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editDietitianCode = view.findViewById(R.id.editDietitianCode);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextPasswordRetype = view.findViewById(R.id.editTextPasswordRetype);

        buttonRegister = view.findViewById(R.id.buttonRegister);

        user = new UserEntity();
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(editTextEmail.getText());
                String password = String.valueOf(editTextPassword.getText());
                String retypePassword = String.valueOf(editTextPasswordRetype.getText());

                if(isValidEmail(email)){
                    if(password.equals(retypePassword)){
                        if(isValidPassword(password)){
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setDietitianId(1);
                            reference.child(String.valueOf(maxid+1)).setValue(user);
                            Toast.makeText(getActivity(), "Data inerted succesfully", Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(getActivity(), "Password must contain minimum 8" +
                                " at least 1 Alphabet, 1 Number and 1 Special Character", Toast.LENGTH_LONG).show();

                    }
                    else
                        Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getActivity(), "Email not valid", Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
