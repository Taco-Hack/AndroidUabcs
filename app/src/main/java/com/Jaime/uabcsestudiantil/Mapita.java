package com.Jaime.uabcsestudiantil;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import static com.google.android.gms.maps.model.CameraPosition.*;


public class Mapita extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private Marker marcador;
    double lat=0.000, lng=0.000;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapita,container,false);

        init();
        return v;
    }

    private void init() {
        mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = SupportMapFragment.newInstance();
        fragmentTransaction.replace(R.id.map, mapFragment).commit();

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap= googleMap;
        LatLng coordenada = new LatLng(24.102754, -110.316039);

        CameraPosition positions = new Builder().target(coordenada).tilt(90).build();
        LatLng lapaz= new LatLng(24.23811, -110.30973);

        if (marcador != null) {
            marcador.remove();
        }

        marcador = mMap.addMarker(new MarkerOptions().position(coordenada).title("Macrocentro")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(positions));

        mMap.addMarker(new MarkerOptions().position(lapaz).title("La Paz, BCS"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lapaz,13));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
