package com.Jaime.uabcsestudiantil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Jaime.uabcsestudiantil.Adapter.AdapterMaster;
import com.Jaime.uabcsestudiantil.Materias.Materias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MasterHorario extends Fragment {


    private OnFragmentInteractionListener mListener;

    private DatabaseReference bd;

    ArrayList<Materias> lisMast,lisMastF;
    String datos;
    RecyclerView rec;

    public MasterHorario() {
        // Required empty public constructor
    }

    public static MasterHorario newInstance(String param1, String param2) {
        MasterHorario fragment = new MasterHorario();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_master_horario,container,false);

        datos=getArguments().getString("Carrera");
        Toast.makeText(getContext()," "+datos,Toast.LENGTH_SHORT).show();

        bd= FirebaseDatabase.getInstance().getReference();

        lisMast = new ArrayList<Materias>();

        lisMastF = new ArrayList<Materias>();

        //falta instanciar el recycler, poner materias

        rec=v.findViewById(R.id.fRecMast);
        RecyclerView.LayoutManager layoutManager;
        rec.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rec.setLayoutManager(layoutManager);

        consulta();

//        AdapterMaster ma = new AdapterMaster(lisMastF);
//        rec.setAdapter(ma);



        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void consulta(){
        bd.child("Materias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                Toast.makeText(getContext()," sipi ", Toast.LENGTH_SHORT).show();

                for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
                    //Toast.makeText(getContext()," s ",Toast.LENGTH_SHORT).show();
                    Materias a= snapshot.getValue(Materias.class);
                    lisMast.add(a);
                }
                cicloForEach();
                Toast.makeText(getContext()," "+lisMast.size(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
    public void cicloForEach(){
        Toast.makeText(getContext()," "+lisMast.size()+" ",Toast.LENGTH_SHORT).show();
        for(Materias a : lisMast){
            //lisDatos.add(a.getNombre());

            //Toast.makeText(getApplicationContext(),a.getIdCarrera()+" "+datos,Toast.LENGTH_SHORT).show();
            if ( a.getIdCarrera().equals(datos) ){
                //Toast.makeText(getContext(),"simon",1).show();
                lisMastF.add(a);
            }
        }
        AdapterMaster ma = new AdapterMaster(lisMastF);
        rec.setAdapter(ma);
    }

}
