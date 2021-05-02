package com.williamcoelho.si.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.williamcoelho.si.R;
import com.williamcoelho.si.model.Medicamentos;

public class AddMedicamentosActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private TextInputEditText inputNome;
    private TextInputEditText inputDoenca;
    private Button buttonHorario;
    private String nome;
    private String doenca;
    private int hora;
    private int minuto;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicamentos);

        fab = findViewById(R.id.fabAddMed);
        inputNome = findViewById(R.id.inputNomeMed);
        inputDoenca = findViewById(R.id.inputDoenca);
        button = findViewById(R.id.addHorario);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("medicamentos");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = inputNome.getText().toString();
                doenca = inputDoenca.getText().toString();
                salvar();
            }
        });

    }

    private void salvar() {

        if(nome != null && doenca != null){

            Medicamentos med = new Medicamentos();
            med.setNomeMedicamento(nome);
            med.setDoencaRelacionada(doenca);
            med.setHoras(hora);
            med.setMinutos(minuto);

            myRef.child(nome).setValue(med);

            Toast toast = Toast.makeText(getApplicationContext(), "Medicamento salvo com sucesso", Toast.LENGTH_SHORT);
            toast.show();

            finish();
        }else{
            finish();
        }

    }

    public void popTimePicker(View view){

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                hora = hourOfDay;
                minuto = minute;
                if(hora < 10 && minute < 10){
                    button.setText("0" + hora + ":0" + minute);
                }else if(hora < 10){
                    button.setText("0" + hora + ":" + minute);
                }else if(minute < 10){
                    button.setText(hora + ":0" + minute);
                }else{
                    button.setText(hora + ":" + minute);
                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hora, minuto, true);

        timePickerDialog.setTitle("Selecione a hora de tomar o remédio");
        timePickerDialog.show();

    }

}