package com.Jaime.uabcsestudiantil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.Jaime.uabcsestudiantil.Adapter.Adapter;
import com.Jaime.uabcsestudiantil.Aulas.Aulas;
import com.Jaime.uabcsestudiantil.Edificio.Edificios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AEdificios extends AppCompatActivity {
    TextView name;
    RecyclerView aulas;

    private Boolean bandera;
    private List<Aulas> lisUsu,lisAul;
    private List<Edificios> lisEdi;
    private DatabaseReference bd;
    String datos;
    ArrayList<String> lisDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aedificios);
        name=findViewById(R.id.xmlTNameEdi);
        aulas=findViewById(R.id.fRcrAul);

        aulas.setLayoutManager(new LinearLayoutManager(this));
        lisDatos= new ArrayList<String>();

        lisUsu = new ArrayList<Aulas>();
        lisAul = new ArrayList<Aulas>();
        lisEdi = new ArrayList<Edificios>();

        bd= FirebaseDatabase.getInstance().getReference();



        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            datos = parametros.getString("id");
            String t=parametros.getString("titulo");
            name.setText(t);
        }
        else{
            Toast.makeText(getApplicationContext(),"no llegó nada",Toast.LENGTH_SHORT).show();
        }
        consulta();
       Adapter adap = new Adapter(lisDatos);
       aulas.setAdapter(adap);



    }


    public void consulta(){
        bd.child("Aulas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                //Toast.makeText(getApplicationContext()," sipi ",Toast.LENGTH_SHORT).show();

                for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
                    //Toast.makeText(getApplicationContext()," s ",Toast.LENGTH_SHORT).show();
                    Aulas a= snapshot.getValue(Aulas.class);
                    lisUsu.add(a);
                }
                cicloForEach();
                Toast.makeText(getApplicationContext()," "+lisUsu.size(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
    public void cicloForEach(){
        Toast.makeText(getApplicationContext()," "+lisUsu.size()+" ",Toast.LENGTH_SHORT).show();
        for(Aulas a : lisUsu){
            //lisDatos.add(a.getNombre());
            if ( a.getIdEdificio().equals(datos) ){
                //name.setText(a.getNombre());
                lisAul.add(a);
                lisDatos.add(a.getNombre());
            }
        }
    }



}
