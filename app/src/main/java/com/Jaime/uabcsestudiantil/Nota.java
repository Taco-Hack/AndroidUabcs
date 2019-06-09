package com.Jaime.uabcsestudiantil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Jaime.uabcsestudiantil.Adapter.AdapterMaster;
import com.Jaime.uabcsestudiantil.Materias.Materias;
import com.Jaime.uabcsestudiantil.Ususario.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Nota extends AppCompatActivity {

    private DatabaseReference bd;
    ArrayList<Usuario> listMat;
    String nombre;
    EditText nota;
    Button guardar;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        bd= FirebaseDatabase.getInstance().getReference();
        guardar=findViewById(R.id.fBtnGua);
        nota=findViewById(R.id.fTxtNota);
        listMat = new ArrayList<Usuario>();

        Bundle bun=getIntent().getExtras();
        nombre=bun.getString("id");
        user=new Usuario();

        consulta();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNotas(nota.getText().toString());
                bd.child("Usuario").child(user.getNombre()).setValue(user);
                Toast.makeText(getApplicationContext(),"Guardado correcto",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void consulta(){
        bd.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                //Toast.makeText(getContext()," sipi ", Toast.LENGTH_SHORT).show();

                for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
                    //Toast.makeText(getContext()," s ",Toast.LENGTH_SHORT).show();
                    Usuario a= snapshot.getValue(Usuario.class);
                    listMat.add(a);
                }
                cicloForEach();
                //Toast.makeText(getContext()," "+listMat.size(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
    public void cicloForEach(){
        //Toast.makeText(getContext()," "+lisMast.size()+" ",Toast.LENGTH_SHORT).show();
        for(Usuario a : listMat){
            //Toast.makeText(getApplicationContext(),a.getEmail()+" "+email,Toast.LENGTH_SHORT).show();
            if ( a.getNombre().equals(nombre) ){
                //Toast.makeText(getContext(),"simon",1).show();
                //asignamos a un text las a.getnotas
                user=a;
                nota.setText(a.getNotas());
            }
        }
    }
}
