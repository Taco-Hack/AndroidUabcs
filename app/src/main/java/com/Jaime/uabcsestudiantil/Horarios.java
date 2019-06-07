package com.Jaime.uabcsestudiantil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.Jaime.uabcsestudiantil.Adapter.Adapter;
import com.Jaime.uabcsestudiantil.Adapter.AdapterMat;
import com.Jaime.uabcsestudiantil.Aulas.Aulas;
import com.Jaime.uabcsestudiantil.Edificio.Edificios;
import com.Jaime.uabcsestudiantil.Materias.Materias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Horarios extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Materias> lisMat,lisMatF;
    private String datos;

    private DatabaseReference bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        recycler=findViewById(R.id.fRecHor);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        bd= FirebaseDatabase.getInstance().getReference();

        lisMat = new ArrayList<Materias>();
        lisMatF = new ArrayList<Materias>();

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            datos = parametros.getString("Carrera");
        }
        else{
            Toast.makeText(getApplicationContext(),"no llegó nada",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getApplicationContext(),datos,Toast.LENGTH_SHORT).show();
        consulta();
        //parte del adapter y el recycler
        AdapterMat adap = new AdapterMat(lisMatF);
        recycler.setAdapter(adap);



    }
    public void consulta(){
        bd.child("Materias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                //Toast.makeText(getApplicationContext()," sipi ",Toast.LENGTH_SHORT).show();

                for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
                    //Toast.makeText(getApplicationContext()," s ",Toast.LENGTH_SHORT).show();
                    Materias a= snapshot.getValue(Materias.class);
                    lisMat.add(a);
                }
                cicloForEach();
                //Toast.makeText(getApplicationContext()," "+lisMat.size(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    public void cicloForEach(){
        //Toast.makeText(getApplicationContext()," "+lisMat.size()+" ",Toast.LENGTH_SHORT).show();
        for(Materias a : lisMat){
            //lisDatos.add(a.getNombre());

            //Toast.makeText(getApplicationContext(),a.getIdCarrera()+" "+datos,Toast.LENGTH_SHORT).show();
            if ( a.getIdCarrera().equals(datos) ){
                //Toast.makeText(getApplicationContext(),"simon",Toast.LENGTH_SHORT).show();
                lisMatF.add(a);
            }
        }
    }
}
