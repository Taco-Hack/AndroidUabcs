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

public class AdapterMat extends RecyclerView.Adapter<AdapterMat.ViewHolderM> {

    ArrayList<Materias> lisDatosM;

    public AdapterMat(ArrayList<Materias> lisDatosM) {
        this.lisDatosM = lisDatosM;
    }

    @NonNull
    @Override
    public AdapterMat.ViewHolderM onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemmat,null,false);

        return new AdapterMat.ViewHolderM(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMat.ViewHolderM viewHolderM, int i) {
        viewHolderM.asignarDatos(lisDatosM.get(i));
    }

    @Override
    public int getItemCount() {
        return lisDatosM.size();
    }

    public class ViewHolderM extends RecyclerView.ViewHolder {
        TextView mat,prof,salon,dia,hora;
        public ViewHolderM(@NonNull View itemView) {
            super(itemView);
            mat=itemView.findViewById(R.id.fTxtMat);
            prof=itemView.findViewById(R.id.fTxtProf);
            salon=itemView.findViewById(R.id.fTxtSalon);
            hora=itemView.findViewById(R.id.fTxtHora);
            dia=itemView.findViewById(R.id.fTxtJaime);
        }

        public void asignarDatos(Materias m) {
            mat.setText(m.getNombre());
            prof.setText(m.getProfesor());
            salon.setText(m.getSalon());
            dia.setText(m.getDia());
            hora.setText(m.getHora());
            dia.setText(m.getDia());

        }
    }
}
