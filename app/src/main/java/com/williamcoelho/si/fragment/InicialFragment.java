package com.williamcoelho.si.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.williamcoelho.si.R;
import com.williamcoelho.si.activity.AddMedicamentosActivity;
import com.williamcoelho.si.activity.AdicionarNumeroActivity;
import com.williamcoelho.si.adapter.EmergenciaAdapter;
import com.williamcoelho.si.adapter.InicialAdapter;
import com.williamcoelho.si.model.Medicamentos;
import com.williamcoelho.si.model.NumerosEmergencia;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicialFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FloatingActionButton fabIncial;
    private DatabaseReference myRef;
    List<Medicamentos> listaMedicamentos = new ArrayList<>();
    private ChildEventListener childEventListenerRef;
    private RecyclerView recyclerView;
    private InicialAdapter medicamentosAdapter;

    public InicialFragment() {
        // Required empty public constructor
    }

    public static InicialFragment newInstance(String param1, String param2) {
        InicialFragment fragment = new InicialFragment();
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
        View view =  inflater.inflate(R.layout.fragment_primeiro, container, false);

        fabIncial = view.findViewById(R.id.floatingActionButtonInicial);
        recyclerView = view.findViewById(R.id.recyclerViewInicial);
        fabIncial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddMedicamentosActivity.class);
                startActivity(i);
            }
        });

        carregarLista();

        return view;
    }

    public void carregarLista(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("medicamentos");

        medicamentosAdapter = new InicialAdapter(listaMedicamentos);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(medicamentosAdapter);


    }

    public void recuperar(){

        listaMedicamentos.clear();

        childEventListenerRef = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Medicamentos med = snapshot.getValue(Medicamentos.class);
                Log.i("INFO LISTA", "Passou" + med);
                listaMedicamentos.add(med);
                medicamentosAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperar();
    }

    @Override
    public void onStop() {
        super.onStop();
        myRef.removeEventListener(childEventListenerRef);
    }
}