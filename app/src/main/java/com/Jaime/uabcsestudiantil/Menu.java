package com.Jaime.uabcsestudiantil;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements Mapita.OnFragmentInteractionListener,
        Usuarios.OnFragmentInteractionListener,
        MasterHorario.OnFragmentInteractionListener{

    private TextView mTextMessage;

    Mapita m;
    Usuarios u;
    MasterHorario h;
    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ft=getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ft.replace(R.id.xmlContendorFragment,m);
                    ft.commit();
                    return true;
                case R.id.navigation_dashboard:
                    ft.replace(R.id.xmlContendorFragment,u);
                    ft.commit();
                    return true;
                case R.id.navigation_notifications:
                    ft.replace(R.id.xmlContendorFragment,h);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Bundle bun=getIntent().getExtras();
        String texto=bun.getString("Carrera");
        String email=bun.getString("id");
        Bundle arg=new Bundle();
        arg.putString("Carrera",texto);
        arg.putString("id",email);

        m= new Mapita();
        u=new Usuarios();
        h=new MasterHorario();

        u.setArguments(arg);
        h.setArguments(arg);
        getSupportFragmentManager().beginTransaction().add(R.id.xmlContendorFragment, m).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
