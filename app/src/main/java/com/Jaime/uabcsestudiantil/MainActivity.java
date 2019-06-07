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
                        Bundle b= new Bundle();
                        Toast.makeText(getApplicationContext(),"Bienvenido ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Menu.class);
                        b.putString("Carrera",sName);
                        i.putExtras(b);
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
                sName=a.getCarrera();
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

       // Map<String, String> datosEdificios=new HashMap<>();

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas 2");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Juan Francisco Rios Franco");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas 2");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Juan Francisco Rios Franco");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas 2");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Arturo Villegas Fimbres");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas 2");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Juan Francisco Rios Franco");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas 2");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Arturo Villegas Fimbres");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas 2");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Arturo Villegas Fimbres");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        //Programacion//---------------------------------------------------------------******
        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Israel Duran Encinas");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Israel Duran Encinas");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Israel Duran Encinas");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Elvia Esthel Aispuro Felixs");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Elvia Esthel Aispuro Felix");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Elvia Esthel Aispuro Felix");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Antonio Aguilar");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Antonio Aguilar");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Antonio Aguilar");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        //mate discretas//---------------------------------------------******
        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        //Electro Basic//----------------------------------------****
        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Electronica Basica");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Miguel Espiritu Jimenez");
        datosEdificios.put("salon","Aula TESLA");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Electronica Basica");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Miguel Espiritu Jimenez");
        datosEdificios.put("salon","Aula TESLA");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Electronica Basica");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jorge Calderon Palafox");
        datosEdificios.put("salon","Aula TESLA");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Ingles 2");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Marisol Mendez Ramirez");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Ingles 2");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Marisol Mendez Ramirez");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        //etica//-----------------------------------------------------***********
        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Etica Profecional y Derecho Informatico");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Alejandro Espinoza");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Etica Profecional y Derecho Informatico");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Alejandro Espinoza");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Etica Profecional y Derecho Informatico");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Alan Baeza Meza");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","IDS");
        datosEdificios.put("nombre","Etica Profecional y Derecho Informatico");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Alan Baeza Meza");
        datosEdificios.put("salon","Aula LISP");
        bd.child("Materias").push().setValue(datosEdificios);

        //ITC//-------------------------------------------------------------------------******

        //fisica//--------------------------------------------------

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Fisica II");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Armando Monge Quevedo");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Fisica II");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Armando Monge Quevedo");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Fisica II");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Armando Monge Quevedo");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Fisica II");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Armando Monge Quevedo");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        //mate//----------------------------------------------******************
        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas II");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Luis Silva");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas II");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Luis Silva");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas II");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Luis Silva");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);


        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas II");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Arturo villegas Fimbres");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas II");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Arturo villegas Fimbres");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas II");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Arturo villegas Fimbres");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        //ingles2--------------------------------------------------------**************
        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ingles II");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Marisol Mendez Ramirez");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ingles II");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","Marisol Mendez Ramirez");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ingles II");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Veronica Carrillo y Carrillo");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ingles II");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Veronica Carrillo y Carrillo");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        //mate dis//------------------------------------------**********+
        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Nelson Olachea Urias");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","Nelson Olachea Urias");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jose Luis Torres Pinzon");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Matematicas Discretas");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jose Luis Torres Pinzon");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        //Ciencias Nat//------------------------------------------------****
        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ciencias Naturales y Desarrollo Sustentable");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","09:00 - 11:00");
        datosEdificios.put("profesor","David Petatan");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ciencias Naturales y Desarrollo Sustentable");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","07:00 - 09:00");
        datosEdificios.put("profesor","David Petatan");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ciencias Naturales y Desarrollo Sustentable");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","David Petatan");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Ciencias Naturales y Desarrollo Sustentable");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","David Petatan");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        //Programacion///---------------------------------------*************
        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Jonathan Soto Muñoz");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Jonathan Soto Muñoz");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jonathan Soto Muñoz");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jonathan Soto Muñoz");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Julio Chavez Ocampo");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Julio Chavez Ocampo");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jonathan Soto Muñoz");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Jonathan Soto Muñoz");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        //empre//---------------------------------------------------*********+
        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Emprendurismo");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Miguel Ojeda");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Emprendurismo");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","11:00 - 13:00");
        datosEdificios.put("profesor","Miguel Ojeda");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Emprendurismo");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Elvia Marin");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","ITC");
        datosEdificios.put("nombre","Emprendurismo");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Elvia Marin");
        datosEdificios.put("salon","Aula RUBY");
        bd.child("Materias").push().setValue(datosEdificios);

        //lati//jotos//---------------------------------------------------****
        //model org y siste
        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Modelo Organizacional y Sistemas de Informacion");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Aida Sanchez Paz Rubio");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Modelo Organizacional y Sistemas de Informacion");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Aida Sanchez Paz Rubio");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        //Estruc Datos-----------------------------------------------**************
        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Estructura de Datos");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Estructura de Datos");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Estructura de Datos");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Miriam Maray Carreño");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        //Programacion-------------------------------------------------*********
        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Yuliana Romero Castro");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Yuliana Romero Castro");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Yuliana Romero Castro");
        datosEdificios.put("salon","Laboratorio A");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Aaron Chavez");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Aaron Chavez");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Programacion");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Aaron Chavez");
        datosEdificios.put("salon","Laboratorio F");
        bd.child("Materias").push().setValue(datosEdificios);

        //Paradigmas Tecnologicos//------------------------------------******
        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Paradigmas Tecnologicos");
        datosEdificios.put("dia","Lunes");
        datosEdificios.put("hora","16:00 - 18:00");
        datosEdificios.put("profesor","Javier Aguilar Parra");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Paradigmas Tecnologicos");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Javier Aguilar Parra");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        //ingles------------------------------------------------------***********
        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Ingles 2");
        datosEdificios.put("dia","Martes");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Veronica Carrillo y Carrillo");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Ingles 2");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Veronica Carrillo y Carrillo");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        //algebra lineal//-----------------------------------------------*******
        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Algebra Lineal");
        datosEdificios.put("dia","Miercoles");
        datosEdificios.put("hora","18:00 - 20:00");
        datosEdificios.put("profesor","Arturo Villegas Fimbres");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);


        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Algebra Lineal");
        datosEdificios.put("dia","Jueves");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Arturo Villegas Fimbres");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);

        datosEdificios.put("idCarrera","LATI");
        datosEdificios.put("nombre","Algebra Lineal");
        datosEdificios.put("dia","Viernes");
        datosEdificios.put("hora","20:00 - 22:00");
        datosEdificios.put("profesor","Arturo Villegas Fimbres");
        datosEdificios.put("salon","Aula ASSEMBLY");
        bd.child("Materias").push().setValue(datosEdificios);


    }
}
