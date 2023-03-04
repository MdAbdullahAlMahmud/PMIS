package com.mkrlabs.pmis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mkrlabs.pmis.ui.HomeActivity;

import es.dmoral.toasty.Toasty;


public class LoginFragment extends Fragment {



    private TextView dontHaveAnAccount,loginForgotPassword;
    private AppCompatButton logInButton;
    private EditText loginEmail,loginPasswordEdt;
    private FirebaseAuth mAuth;

    private ProgressBar loginProgressBar;
    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return  view;
    }

    private void initView(View view) {

        loginEmail= view.findViewById(R.id.loginEamil);
        loginPasswordEdt= view.findViewById(R.id.loginPassword);
        dontHaveAnAccount = view.findViewById(R.id.dontHaveAnAccount);
        loginForgotPassword =view.findViewById(R.id.loginForgotPassword);
        logInButton = view.findViewById(R.id.logInButton);
        loginProgressBar = view.findViewById(R.id.loginProgressBar);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        loginForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // ((AuthenticationActivity)getActivity()).setUpFragment(new ForgotPasswordFragment());
            }
        });

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AuthenticationActivity)getActivity()).setUpFragment(new RegistrationFragment());
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailAddress = loginEmail.getText().toString();
                String password = loginPasswordEdt.getText().toString();



                if (emailAddress.isEmpty()){
                    loginEmail.setError("required");
                    return;
                }

                if (password.isEmpty()){
                    loginPasswordEdt.setError("required");
                    return;
                }



                loginProgressBar.setVisibility(View.VISIBLE);
                checkUserValid(emailAddress, password);
            }
        });



    }

    private void checkUserValid(String emailAddress, String password) {


        mAuth.signInWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    loginProgressBar.setVisibility(View.GONE);

                    Toasty.success(getContext(),"Successfully Login").show();

                    startActivity(new Intent(getContext(), HomeActivity.class));
                    getActivity().finish();

                }else {
                    loginProgressBar.setVisibility(View.GONE);
                    Toasty.error(getContext(),"Bad  Credential").show();



                }
            }
        });

    }


}