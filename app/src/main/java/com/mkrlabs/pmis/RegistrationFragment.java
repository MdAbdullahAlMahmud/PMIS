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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class RegistrationFragment extends Fragment {


    private TextView registrationSignIn;
    private AppCompatButton signUpButton;
    private EditText nameEdt,emailEdt,passwordEdt,confirmPasswordEdt , batchEdt;
    AutoCompleteTextView customerAutoTV;

    private ProgressBar registrationProgressBar;
    private FirebaseAuth mAuth;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        initView(view);

        return  view;
    }

    private void initView(View view) {

        mAuth = FirebaseAuth.getInstance();
        signUpButton =view.findViewById(R.id.signUpButton);
        registrationSignIn =view.findViewById(R.id.registrationSignIn);

        nameEdt =view.findViewById(R.id.registrationNameEdt);
        emailEdt =view.findViewById(R.id.registrationEmailEdt);
        batchEdt =view.findViewById(R.id.registrationBatchEdt);

        passwordEdt =view.findViewById(R.id.registrationPasswordEdt);
        confirmPasswordEdt =view.findViewById(R.id.registrationConfirmPasswordEdt);
        customerAutoTV =view.findViewById(R.id.customerTextView);
        registrationProgressBar =view.findViewById(R.id.registrationProgressBar);

        initUI();


    }

    private void initUI()
    {
        //UI reference of textView

        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, customerList);


        customerAutoTV.setAdapter(adapter);



    }
    private ArrayList<String> getCustomerList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("CSE");
        customers.add("SWE");
        return customers;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        registrationSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((AuthenticationActivity)getActivity()).setUpFragment(new LoginFragment());

            }
        });



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                validateField();

            }
        });

    }


    private  void  validateField(){

        String name = nameEdt.getText().toString().trim();
        String email = emailEdt.getText().toString().trim();
        String batch = batchEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString();
        String confirmPassword = confirmPasswordEdt.getText().toString();

//    public User(String name, String number, String email, String image, String uid, double latitude, double longitude) {


        if (name.isEmpty()){
            nameEdt.setError("required");
            return;
        }

        if (email.isEmpty()){
            emailEdt.setError("required");
            return;
        }

        if (batch.isEmpty()){
            batchEdt.setError("required");
            return;
        }

         if (customerAutoTV.getText().toString().isEmpty()){
            customerAutoTV.setError("required");
            return;
        }



        if (password.isEmpty()){
            passwordEdt.setError("required");
            return;
        }
        if (confirmPassword.isEmpty()){
            confirmPasswordEdt.setError("required");
            return;
        }
        if (!password.equals(confirmPassword)){
            Toasty.error(getContext(),"Password Mismatch").show();
            return;
        }



        registrationProgressBar.setVisibility(View.VISIBLE);
        createAccountWithUserCredential(email,password);



    }

    private void createAccountWithUserCredential(String email, String password) {


        mAuth .createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()){

                    registrationProgressBar.setVisibility(View.GONE);
                    Toasty.success(getContext(),"Congratulations , Your account has been Created").show();

                    Intent intent = new Intent(getContext(),HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();


                }else {
                    registrationProgressBar.setVisibility(View.GONE);
                    Toasty.success(getContext(),"Sorry !! Please try again later").show();

                }
            }
        });


    }
}