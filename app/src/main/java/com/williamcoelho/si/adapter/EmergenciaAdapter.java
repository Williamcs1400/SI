package com.williamcoelho.si.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamcoelho.si.R;
import com.williamcoelho.si.model.NumerosEmergencia;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmergenciaAdapter extends RecyclerView.Adapter<EmergenciaAdapter.MyViewHolder> {

    private List<NumerosEmergencia> listaNum;

    public EmergenciaAdapter(List<NumerosEmergencia> lista) {
        this.listaNum = lista;
    }

    @NonNull
    @Override
    public EmergenciaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.lista_contatos_emergencia_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergenciaAdapter.MyViewHolder holder, int position) {

        NumerosEmergencia numerosEmergencia = listaNum.get(position);
        holder.nome.setText(numerosEmergencia.getNomeContato());
        holder.num.setText(numerosEmergencia.getNumContato());

        if(holder.nome.getText() == "Samu"){
            Log.i("INFO ADAPTER", "Samu");
            holder.circleImageView.setImageResource(R.drawable.samu);
        }
        if(holder.nome.getText() == "Polícia"){
            Log.i("INFO ADAPTER", "Polícia");
            holder.circleImageView.setImageResource(R.drawable.pm);
        }
        if(holder.nome.getText() == "Bombeiros"){
            Log.i("INFO ADAPTER", "Bombeiros");
            holder.circleImageView.setImageResource(R.drawable.bombeiros);
        }

    }

    @Override
    public int getItemCount() {
        return this.listaNum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView num;
        CircleImageView circleImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewEmergenciaNome);
            num = itemView.findViewById(R.id.textViewEmergenciaNum);
            circleImageView = itemView.findViewById(R.id.circleImageViewEmergencia);

        }
    }



}
