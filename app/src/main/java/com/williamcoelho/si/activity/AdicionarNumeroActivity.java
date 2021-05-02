package com.williamcoelho.si.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.williamcoelho.si.R;
import com.williamcoelho.si.model.NumerosEmergencia;

public class AdicionarNumeroActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private TextInputEditText inputNome;
    private TextInputEditText inputNum;
    private String nome;
    private String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_numero);

        fab = findViewById(R.id.floatingActionButtonAdd);
        inputNome = findViewById(R.id.inputNome);
        inputNum = findViewById(R.id.inputNum);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("emergencia");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = inputNome.getText().toString();
                num = inputNum.getText().toString();
                salvar();
            }
        });
    }

    public void salvar(){

        if(!(nome.isEmpty() || num.isEmpty())){

            NumerosEmergencia usuario = new NumerosEmergencia();
            usuario.setNomeContato(nome);
            usuario.setNumContato(num);

            myRef.child(nome).setValue(usuario);

            Toast toast = Toast.makeText(getApplicationContext(), "Usuário salvo com sucesso", Toast.LENGTH_SHORT);
            toast.show();

            finish();

        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Não é permitido deixar nenhuma opção vazia", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }

    }


}