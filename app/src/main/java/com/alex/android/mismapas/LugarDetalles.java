package com.alex.android.mismapas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by alexander on 10-16-16.
 */
public class LugarDetalles extends AppCompatActivity{
    public static String Extra_Titulo="TITULO";
    public static String Extra_Imagen="IMAGEN";
    public static String Extra_Descrip="DESCRIPCION";
    private String titulo,descripcion;
    private int imagen;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.detalle_lugar);
            /*Toolbar toolbar = (Toolbar) findViewById(R.id.);
            setSupportActionBar(toolbar);*/
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Bundle bundle=getIntent().getExtras();
            imagen=bundle.getInt(Extra_Imagen,-1);
            if (imagen != -1) {
                ImageView imageView = (ImageView) findViewById(R.id.imagen);
                imageView.setImageResource(imagen);
            }
            titulo=bundle.getString(Extra_Titulo);
            if (titulo!=null){
                TextView editText= (TextView) findViewById(R.id.titulo);
                editText.setText(titulo);
            }
            descripcion=bundle.getString(Extra_Descrip);
            if (titulo!=null){
                TextView editText= (TextView) findViewById(R.id.descripcion);
                editText.setText(descripcion);
            }
        }
}
