package com.Jaime.uabcsestudiantil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.internal.location.zzas;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.getSystemService;
import static com.google.android.gms.maps.model.CameraPosition.*;


public class Mapita extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener { //, LocationListener

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private Marker marcador, mControl, mDele, mSistemas,mPoli,mBiblio,mHosp,mRadio,mRector;
    LatLng coordenada, cControl, cDele, cSistemas, cPoli,cBiblio,cHosp,cRadio, cRector;

    double lat = 24.102457, lng = -110.316258;
    View view;

    LocationManager locationManager;


    public Mapita() {
    }

    public static Mapita newInstance() {
        Mapita fragment = new Mapita();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapita, container, false);

        init();
        return v;
    }

    private void init() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            //locationStart();
        }


        mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = SupportMapFragment.newInstance();
        fragmentTransaction.replace(R.id.map, mapFragment).commit();

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //control escolar 24.102502, -110.315662
        coordenada = new LatLng(24.102754, -110.316039);
        cControl = new LatLng(24.102502, -110.315662);
        cDele = new LatLng(24.100365, -110.316689);
        cSistemas = new LatLng(24.102457, -110.316258);
        cPoli = new LatLng(24.103153, -110.315869);
        cBiblio = new LatLng(24.101780, -110.316429);
        cHosp = new LatLng(24.101759, -110.315886);
        cRadio = new LatLng(24.101962, -110.315816);
        cRector = new LatLng(24.101053, -110.316590);
        CameraPosition positions = new Builder().target(coordenada).tilt(90).build();

        LatLng lapaz= new LatLng(lat, lng);


        marcador = mMap.addMarker(new MarkerOptions().position(coordenada).title("Macrocentro")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mControl = mMap.addMarker(new MarkerOptions().position(cControl).title("Control escolar")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mDele = mMap.addMarker(new MarkerOptions().position(cDele).title("Departamento de Lenguas Extrangeras")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mSistemas = mMap.addMarker(new MarkerOptions().position(cSistemas).title("Departamento de Sistemas Computacionales")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));//24.103153, -110.315869
        mPoli = mMap.addMarker(new MarkerOptions().position(cPoli).title("Poliforo Universitario")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mBiblio = mMap.addMarker(new MarkerOptions().position(cBiblio).title("Biblioteca Universitaria")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mHosp= mMap.addMarker(new MarkerOptions().position(cHosp).title("Biblioteca Universitaria")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mRadio= mMap.addMarker(new MarkerOptions().position(cRadio).title("Biblioteca Universitaria")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mRector= mMap.addMarker(new MarkerOptions().position(cRector).title("Rectoria")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));


        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(positions));

        //mMap.addMarker(new MarkerOptions().position(lapaz).title("Yo"));
        mMap.addMarker(new MarkerOptions().position(cSistemas).title("Departamento de Sistemas Computacionales")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lapaz,18));
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        mMap.setOnMarkerClickListener(this);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//m0 = macro, m3 = sistemas, m1 = controlescolar, m2 = dele
        //Toast.makeText(getContext(),marker.getId(),Toast.LENGTH_SHORT).show();
        Intent i;
        switch (marker.getId()){
            case "m0":
            case "m1":
            case "m2":
            case "m3":
            case "m4":
            case "m5":
            case "m6":
            case "m7":
            case "m8":
                i = new Intent(getContext(),AEdificios.class);
                i.putExtra("id",marker.getId());
                i.putExtra("titulo",marker.getTitle());
                Toast.makeText(getContext(),marker.getId()+" ",Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
        }

        //Toast.makeText(getContext(),marker.getId()+" ",Toast.LENGTH_SHORT).show();
        return false;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
