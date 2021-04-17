package com.williamcoelho.si.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.williamcoelho.si.R;
import com.williamcoelho.si.activity.AjudaCadastroActivity;
import com.williamcoelho.si.activity.AjudaUsarActivity;
import com.williamcoelho.si.activity.QuemSomosActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjudaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjudaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textQuem;
    TextView textCadastro;
    TextView textUsar;


    public AjudaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AjudaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AjudaFragment newInstance(String param1, String param2) {
        AjudaFragment fragment = new AjudaFragment();
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

        View view = inflater.inflate(R.layout.fragment_ajuda, container, false);
        textQuem = view.findViewById(R.id.textQuemSomos);
        textCadastro = view.findViewById(R.id.textViewCadastro);
        textUsar = view.findViewById(R.id.textViewUsar);

        textQuem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuemSomosActivity.class);
                startActivity(intent);
            }
        });

        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AjudaCadastroActivity.class);
                startActivity(intent);
            }
        });

        textUsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AjudaUsarActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void abrirQuem(View view){
        Intent intent = new Intent(getActivity(), QuemSomosActivity.class);
        startActivity(intent);
    }
}