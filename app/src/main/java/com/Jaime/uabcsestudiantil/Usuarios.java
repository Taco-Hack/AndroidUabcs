package com.Jaime.uabcsestudiantil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Usuarios extends Fragment {

    CardView horario,apuntes,califi;
    String id;

    public Usuarios() {
    }

    public static Usuarios newInstance() {
        Usuarios fragment = new Usuarios();
        Bundle args = new Bundle();

        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_usuarios,container,false);

        horario = v.findViewById(R.id.fCardHor);
        apuntes = v.findViewById(R.id.fCardApu);
        califi = v.findViewById(R.id.fCardCal);

        horario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),Horarios.class);
                String texto=getArguments().getString("Carrera");
                id=getArguments().getString("id");
                i.putExtra("Carrera",texto);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        apuntes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT).show();
                id=getArguments().getString("id");
                Intent i = new Intent(getContext(),Nota.class);
                Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        califi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void onButtonPressed(Uri uri) {
    }

    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
