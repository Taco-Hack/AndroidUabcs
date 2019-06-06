package com.Jaime.uabcsestudiantil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Jaime.uabcsestudiantil.Ususario.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button ok,registro;
    private EditText email,pass;

    private String sEmail, sPass,sName;
    private Boolean bandera;
    private List<Usuario> lisUsu;

    private DatabaseReference bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ok=findViewById(R.id.xmlStart);
        registro=findViewById(R.id.xmlBtnRegistro);
        email=findViewById(R.id.xmlETxtEmailLog);
        pass=findViewById(R.id.xmlETxtPassLog);

         sEmail = email.getText().toString().trim();
         sPass = pass.getText().toString().trim();
         lisUsu = new ArrayList<Usuario>();
         bandera=false;

        bd= FirebaseDatabase.getInstance().getReference();

        consulta();

//m0 = macro, m3 = sistemas, m2 = dele


        registro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty() ||
                        pass.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),
                            "Campos vacios",
                            Toast.LENGTH_SHORT).show();
                }else{

                    //consultar si existe :v

                    sEmail = email.getText().toString().trim();
                    sPass = pass.getText().toString().trim();

                    cicloForEach();

                    if (!bandera){
                        Toast.makeText(getApplicationContext(),"Usuario invalido ",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Bienvenido ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Menu.class);
                        i.putExtra("Usuario",sName);
                        startActivity(i); // descomentar
                    }
                }
            }
        });
    }

    public void consulta(){
        bd.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
                    Usuario u= snapshot.getValue(Usuario.class);
                    lisUsu.add(u);
                }
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    public void cicloForEach(){
        for(Usuario a : lisUsu){
            if ( a.getEmail().equals(sEmail) ){
                sName=a.getNombre();
                bandera=true;
                break;
            }
        }
    }

    public void insertar(){
        Map<String, String> datosEdificios=new HashMap<>();

        datosEdificios.put("id","m0");
        datosEdificios.put("nombre","Macrocentro");
        bd.child("Edificios").push().setValue(datosEdificios);

        datosEdificios.put("nombre","Laboratorio A");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio B");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio C");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio D");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio E");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio F");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula C++");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula ADA");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula ASSEMBLY");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula VISUAL BASIC");
        datosEdificios.put("idEdificio","m0");
        bd.child("Aulas").push().setValue(datosEdificios);


//dele
        datosEdificios.put("id","m2");//dele
        datosEdificios.put("nombre","Dep. de Lenguas Extranjeras");
        bd.child("Edificios").push().setValue(datosEdificios);

        datosEdificios.put("nombre","Aula RUBY");
        datosEdificios.put("idEdificio","m2");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula PROLOG");
        datosEdificios.put("idEdificio","m2");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula LIPS");
        datosEdificios.put("idEdificio","m2");
        bd.child("Aulas").push().setValue(datosEdificios);


        datosEdificios.put("id","m3");//sistemas
        datosEdificios.put("nombre","Dep. de Sistemas Computacionales");
        bd.child("Edificios").push().setValue(datosEdificios);

        datosEdificios.put("nombre","Laboratorio de Electronica TESLA");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio de Electronica EDISON");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Laboratorio de Redes y Seguridad");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula COBOL");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula JAVA");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula PASCAL");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula PYTHON");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
        datosEdificios.put("nombre","Aula ALAN TURING");
        datosEdificios.put("idEdificio","m3");
        bd.child("Aulas").push().setValue(datosEdificios);
    }
}
