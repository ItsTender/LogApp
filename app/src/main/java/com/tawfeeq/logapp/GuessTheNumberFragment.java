package com.tawfeeq.logapp;

import android.content.ClipData;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuessTheNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuessTheNumberFragment extends Fragment {

    TextView Log;
    EditText Number;
    Button Guess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_guess_the_number, container, false);

        Log= view.findViewById(R.id.tvLogged);

        Bundle bundle= this.getArguments();

        Log.setText(bundle.getString("User"));



        return view;

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuessTheNumberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuessTheNumberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuessTheNumberFragment newInstance(String param1, String param2) {
        GuessTheNumberFragment fragment = new GuessTheNumberFragment();
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
    public void onStart() {
        super.onStart();

        Number= getView().findViewById(R.id.etNumber);
        Guess= getView().findViewById(R.id.btnGuess);
        Guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Data Validation ( The Given Info is Correct )
                String num = Number.getText().toString();
                int n = Integer.parseInt(num);
                Random rnd = new Random();
                int x = rnd.nextInt(10);
                if (x == n)
                    Toast.makeText(getActivity(), "Your Guess Is Correct " + x, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Your Guess Is Incorrect " + x, Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}