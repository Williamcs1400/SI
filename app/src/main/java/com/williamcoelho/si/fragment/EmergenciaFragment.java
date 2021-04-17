package com.williamcoelho.si.fragment;

import android.content.Intent;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.williamcoelho.si.R;
import com.williamcoelho.si.activity.AdicionarNumeroActivity;
import com.williamcoelho.si.activity.MainActivity;
import com.williamcoelho.si.activity.QuemSomosActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergenciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergenciaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmergenciaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfigFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmergenciaFragment newInstance(String param1, String param2) {
        EmergenciaFragment fragment = new EmergenciaFragment();
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

        View view = inflater.inflate(R.layout.fragment_emergencia, container, false);
        FloatingActionButton fabPrincipal = view.findViewById(R.id.fabEmer);

        fabPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AdicionarNumeroActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}