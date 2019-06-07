package com.Jaime.uabcsestudiantil.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Jaime.uabcsestudiantil.Materias.Materias;
import com.Jaime.uabcsestudiantil.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<String> lisDatos;

    public Adapter(ArrayList<String> lisDatos) {
        this.lisDatos = lisDatos;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemaulas,null,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.asignarDatos(lisDatos.get(i));
    }

    @Override
    public int getItemCount() {
        return lisDatos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView texto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.fTVAula);

        }

        public void asignarDatos(String s) {
            texto.setText(s);
        }

    }

}
