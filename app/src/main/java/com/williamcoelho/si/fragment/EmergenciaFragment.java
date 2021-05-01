package com.williamcoelho.si.fragment;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.williamcoelho.si.R;
import com.williamcoelho.si.activity.AdicionarNumeroActivity;
import com.williamcoelho.si.adapter.EmergenciaAdapter;
import com.williamcoelho.si.helper.RecyclerItemClickListener;
import com.williamcoelho.si.model.NumerosEmergencia;

import java.util.ArrayList;
import java.util.List;

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

    private RecyclerView recyclerView;
    private EmergenciaAdapter emergenciaAdapter;
    List<NumerosEmergencia> listaNumeros = new ArrayList<>();
    private ImageView discar;
    private DatabaseReference myRef;
    private ChildEventListener childEventListenerRef;

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
        discar = view.findViewById(R.id.imageViewDiscar);
        recyclerView = view.findViewById(R.id.recyclerView);

        fabPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AdicionarNumeroActivity.class);
                startActivity(i);
                emergenciaAdapter.notifyDataSetChanged();
            }
        });

        carregarLista();

        return view;
    }

    public void carregarLista(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("emergencia");

        if(listaNumeros.isEmpty()){

            NumerosEmergencia samu = new NumerosEmergencia();
            NumerosEmergencia policia = new NumerosEmergencia();
            NumerosEmergencia bombeiros = new NumerosEmergencia();

            samu.setNomeContato("Samu");
            samu.setNumContato("192");

            policia.setNomeContato("Polícia");
            policia.setNumContato("190");

            bombeiros.setNomeContato("Bombeiros");
            bombeiros.setNumContato("193");

            myRef.child("Samu").setValue(samu);
            myRef.child("Policia").setValue(policia);
            myRef.child("Bombeiros").setValue(bombeiros);

        }

        Log.i("INFO LISTA", "Passou" + listaNumeros.size());

        emergenciaAdapter = new EmergenciaAdapter(listaNumeros);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(emergenciaAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                NumerosEmergencia numero = listaNumeros.get(position);

                Uri uri = Uri.parse("tel:" + numero.getNumContato());
                Intent i = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(i);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

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

    public void recuperar(){

        listaNumeros.clear();

        childEventListenerRef = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                NumerosEmergencia nums = snapshot.getValue(NumerosEmergencia.class);
                Log.i("INFO LISTA", "Passou" + nums);
                listaNumeros.add(nums);
                emergenciaAdapter.notifyDataSetChanged();

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
}