package com.tawfeeq.logapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {

    private EditText EMail,Password;
    private TextView SignUpLink, ForgotPass;
    private Button LogIn;
    private FireBaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Connecting Components (FireBase, btn, Email, Password)
        fbs=FireBaseServices.getInstance();
        EMail =getView().findViewById(R.id.etLoginMail);
        Password= getView().findViewById(R.id.etLoginPass);
        SignUpLink=getView().findViewById(R.id.tvSignup);
        SignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToSignupFragment();
            }
        });
        ForgotPass=getView().findViewById(R.id.tvForgotPass);
        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {GoToForgotPasswordFragment();}
        });

        LogIn=getView().findViewById(R.id.btnLogin);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Data Validation ( The Given Info is Correct )
                String username = EMail.getText().toString();
                String pass = Password.getText().toString();
                if(username.trim().isEmpty() && pass.trim().isEmpty()){
                    Toast.makeText(getActivity(), "Some Field Are Missing!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Log In Procedure
                fbs.getAuth().signInWithEmailAndPassword(username, pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            // To DO
                            Toast.makeText(getActivity(), "Log In Successful!", Toast.LENGTH_LONG).show();
                            sendDataGoToMain();

                        }
                        else
                        {
                            // To DO
                            Toast.makeText(getActivity(), "Log In Failed!", Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }
        });
    }
    private void GoToSignupFragment() {
        FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new SignUpFragment());
        ft.commit();
    }
    private void GoToForgotPasswordFragment() {
        FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new ForgotPasswordFragment());
        ft.commit();
    }
    private void sendDataGoToMain(){
        String M="";
        M= EMail.getText().toString();

        Fragment gtn=new GuessTheNumberFragment();
        Bundle bundle= new Bundle();
        bundle.putString("User",M);
        gtn.setArguments(bundle);
        FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, gtn);
        ft.commit();
    }
}