package com.alex.android.mismapas;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by alexander on 10-16-16.
 */
public class InfoWindow implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private int[] direccionImagenes;
    private Object[] tag;

    public InfoWindow(Context context, int[] direccionImagenes, Object[] tag) {
        this.context = context;
        this.direccionImagenes = direccionImagenes;
        this.tag = tag;
    }

    @Override
    public View getInfoWindow(Marker marker) {
       return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view= View.inflate(context,R.layout.info_window,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.imagen);
        TextView editText= (TextView) view.findViewById(R.id.texto);
        int imagen=obtenerDireccionImagen(marker.getTag().toString());
        if (imagen!=-1)
            imageView.setImageResource(imagen);
        editText.setText(marker.getTitle());
        return view;
    }

    private int obtenerDireccionImagen(String tagMarker){
        for (int i=0;i<tag.length;i++){
            if (tag[i].toString().equals(tagMarker))
                return direccionImagenes[i];
        }
        return -1;
    }
}
