package com.example.app_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Menu_act extends AppCompatActivity {

    private ViewFlipper vf;
    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);

        vf = (ViewFlipper)findViewById(R.id.slider);

        for(int i = 0; i < images.length; i++)
        {
            flip_image(images[i]);
        }
    }


    // función que me permitirá configurar el slider
    public void flip_image(int i)
    {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);

        vf.addView(view);           // añadimos al viewflipper el contenido del slider
        vf.setFlipInterval(2800);   // duración intervalo de imagenes
        vf.setAutoStart(true);     // inicia de forma automatica.

        // Sentido al slider
        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    // Intent Explícito.

    public void Info(View v)
    {
        Intent i = new Intent(this, Info_act.class);
        startActivity(i);
    }

    public void Maps(View v)
    {
        Intent i = new Intent(this, Maps_act.class);
        startActivity(i);
    }
    public void Insumos(View v)
    {
        Intent z = new Intent(this, Insumos_act.class);
        startActivity(z);
    }
    public void Clientes(View v)
    {
        //Enviar datos al spinner
        ArrayList<String> clientes = new ArrayList<String>();
        ArrayList<String> planes = new ArrayList<String>();

        clientes.add("Roberto");
        clientes.add("Ivan");

        planes.add("xtreme");
        planes.add("mindFullness");

        Intent z = new Intent(this,Clientes_act.class);
        z.putExtra("listaclientes", clientes); //Preparando los datos para ser enviados
        z.putExtra("listaplanes", planes);
        startActivity(z);
    }

}