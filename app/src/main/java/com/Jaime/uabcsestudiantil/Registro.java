package com.Jaime.uabcsestudiantil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Registro extends AppCompatActivity {

    private EditText name,lName,email,pass1,pass2;
    private Spinner carrera,semestre;
    private Button ok;
    private DatabaseReference bd;
    private  List<Usuario> lisUsu;
    private String sEmail, itemCar,itemSem;
    private boolean bandera;
    ArrayAdapter adapter,adapter2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        name=findViewById(R.id.xmlETxtName);
        lName=findViewById(R.id.xmlETxtLName);
        email=findViewById(R.id.xmlETxtEmail);
        pass1=findViewById(R.id.xmlETxtPas1);
        pass2=findViewById(R.id.xmlETxtPas2);
        carrera=findViewById(R.id.xmlSprCar);
        semestre=findViewById(R.id.xmlSprSem);
        ok=findViewById(R.id.xmlBtnOk);

        adapter = ArrayAdapter.createFromResource(this,R.array.carreras, android.R.layout.simple_spinner_item);
        carrera.setAdapter(adapter);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.semestre, android.R.layout.simple_spinner_item);
        semestre.setAdapter(adapter2);

        bandera=false;
        lisUsu = new ArrayList<Usuario>();

        itemCar = carrera.getSelectedItem().toString();
        itemSem = semestre.getSelectedItem().toString();

        bd= FirebaseDatabase.getInstance().getReference();
        consulta();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()||
                        lName.getText().toString().isEmpty()||
                        email.getText().toString().isEmpty()||
                        pass1.getText().toString().isEmpty()||
                        pass2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Campos vacios",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if (pass1.getText().toString().equals(pass2.getText().toString())){
                        if (pass1.getText().toString().trim().length()>6 ){

                            cicloForEach();
                            itemCar = carrera.getSelectedItem().toString();
                            itemSem = semestre.getSelectedItem().toString();


                            if (!bandera){
                                Map<String, Object> datosUsuario=new HashMap<>();
                                datosUsuario.put("nombre",name.getText().toString().trim());
                                datosUsuario.put("apellido",lName.getText().toString().trim());
                                datosUsuario.put("email",email.getText().toString().trim());
                                datosUsuario.put("password",pass1.getText().toString().trim());
                                datosUsuario.put("carrera",itemCar);
                                datosUsuario.put("semestre",itemSem);
                                datosUsuario.put("notas","hola estas son tus notas:   ");

                                bd.child("Usuario").push().setValue(datosUsuario);

                                name.setText("");
                                lName.setText("");
                                email.setText("");
                                pass1.setText("");
                                pass2.setText("");

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);

                            }else{
                                Toast.makeText(getApplicationContext(),"email ya existe",Toast.LENGTH_SHORT).show();
                            }
                            bandera=false;

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Contraseñas muy pequeña",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Contraseñas incorrectas",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cicloForEach(){
        sEmail = email.getText().toString().trim();
        for(Usuario a : lisUsu){
            if ( a.getEmail().equals(sEmail) ){
                bandera=true;
                break;
            }
        }
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
}

