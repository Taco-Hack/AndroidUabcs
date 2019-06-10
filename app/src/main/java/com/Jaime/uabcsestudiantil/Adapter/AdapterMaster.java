package com.Jaime.uabcsestudiantil.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Jaime.uabcsestudiantil.Materias.Materias;
import com.Jaime.uabcsestudiantil.R;

import java.util.ArrayList;

public class AdapterMaster extends RecyclerView.Adapter<AdapterMaster.ViewHolderMaster> {

    ArrayList<Materias> lisDatosMaster;

    public AdapterMaster(ArrayList<Materias> lisDatosMaster) {
        this.lisDatosMaster = lisDatosMaster;
    }

    @NonNull
    @Override
    public AdapterMaster.ViewHolderMaster onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mast,null,false);

        return new AdapterMaster.ViewHolderMaster(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMaster.ViewHolderMaster viewHolderMaster, int i) {
        viewHolderMaster.asignarDatos(lisDatosMaster.get(i));
    }

    @Override
    public int getItemCount() {
        return lisDatosMaster.size();
    }

    public class ViewHolderMaster extends RecyclerView.ViewHolder {
        TextView nombre,salon,hora,dia;
        public ViewHolderMaster(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.fTxtNomProf);
            salon=itemView.findViewById(R.id.fTxtSalProf);
            hora=itemView.findViewById(R.id.fTxtHorProf);
            dia=itemView.findViewById(R.id.fTxtDia);
        }

        public void asignarDatos(Materias materias) {
            nombre.setText(materias.getProfesor());
            salon.setText(materias.getSalon());
            hora.setText(materias.getHora());
            dia.setText(materias.getDia());
            dia.setText(materias.getDia());

        }
    }
}
