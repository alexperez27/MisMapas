package com.alex.android.mismapas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions[] lugares;
    private int[] direccionImagenes;
    private Object[] TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        cargarLugares();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ubicarLugares(googleMap);
        configurarInfoWindow(googleMap);
        try {
            mMap.setMyLocationEnabled(true);
        }catch(SecurityException e){}
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                OnMapClick(latLng);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return OnLongMarkerClick(marker);
            }
        });
    }

    public void OnMapClick(LatLng latLng) {
        Toast.makeText(MapsActivity.this, latLng.toString(), Toast.LENGTH_SHORT).show();
    }

    public boolean OnLongMarkerClick(Marker marker) {
        Toast.makeText(MapsActivity.this, "TAG:"+marker.getPosition().toString(), Toast.LENGTH_SHORT).show();
        marker.showInfoWindow();
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);
        return false;
    }

    private void cargarLugares(){
        this.lugares=new MarkerOptions[3];
        this.direccionImagenes=new int[lugares.length];
        this.TAG= new String[lugares.length];
        this.lugares[0]= new MarkerOptions();
        this.lugares[0].position(new LatLng(12.14385340534494,-86.17241279628911)).title("Aeropuerto");
        this.lugares[1]= new MarkerOptions();
        this.lugares[1].position(new LatLng(12.147533882955857,-86.19406358745732)).title("La Subasta").flat(true);
        this.lugares[2]= new MarkerOptions();
        this.lugares[2].position(new LatLng(12.147278485277553,-86.21800498512425)).title("Hospital Alemán").flat(true);
        this.direccionImagenes[0]=R.drawable.aeropuerto;
        this.direccionImagenes[1]=R.drawable.la_subasta;
        this.direccionImagenes[2]=R.drawable.hospital_aleman;
        this.TAG[0]=String.valueOf(0);
        this.TAG[1]=String.valueOf(1);
        this.TAG[2]=String.valueOf(2);
    }

    private void ubicarLugares(GoogleMap googleMap){
        Marker marker = null;
        for (int id=0;id<lugares.length;id++){
            marker = googleMap.addMarker(lugares[id]);
            marker.setTag(id);
        }
        if (lugares.length>0)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lugares[lugares.length-1].getPosition(),16));
    }

    private void configurarInfoWindow(GoogleMap googleMap) {
        googleMap.setInfoWindowAdapter(new InfoWindow(this,direccionImagenes,TAG));
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent= new Intent(getApplicationContext(),LugarDetalles.class);
                intent.putExtra(LugarDetalles.Extra_Titulo,marker.getTitle());
                intent.putExtra(LugarDetalles.Extra_Imagen,obtenerDireccionImagen(marker.getTag().toString()));
                startActivity(intent);
            }
        });
    }

    private int obtenerDireccionImagen(String tagMarker){
        for (int i=0;i<TAG.length;i++){
            if (TAG[i].toString().equals(tagMarker))
                return direccionImagenes[i];
        }
        return -1;
    }
}
