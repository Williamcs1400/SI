package com.williamcoelho.si.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamcoelho.si.R;
import com.williamcoelho.si.model.Medicamentos;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InicialAdapter extends RecyclerView.Adapter<InicialAdapter.MyViewHolder> {

    private List<Medicamentos> listaMedicamentos;


    public InicialAdapter(List<Medicamentos> lista) {

        this.listaMedicamentos = lista;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inicial_adapter, parent, false);

        return new InicialAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Medicamentos med = listaMedicamentos.get(position);
        holder.nome.setText(med.getNomeMedicamento());
        holder.doenca.setText(med.getDoencaRelacionada());
        String horario;

        if(med.getHoras() < 10 && med.getMinutos() < 10){
            horario = "0" + med.getHoras() + ":0" + med.getMinutos();
        }
        else if(med.getHoras() < 10){
            horario = "0" + med.getHoras() + ":" + med.getMinutos();
        }
        else if(med.getMinutos() < 10){
            horario = med.getHoras() + ":0" + med.getMinutos();
        }else{
            horario = med.getHoras() + ":" + med.getMinutos();
        }
        holder.horario.setText(horario);

    }

    @Override
    public int getItemCount() {
        return listaMedicamentos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView doenca;
        TextView horario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nomeMedicamento);
            doenca = itemView.findViewById(R.id.textViewDoenca);
            horario = itemView.findViewById(R.id.textViewHorario);

        }
    }

}
