package com.example.app_fitness;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import clase.AdminSQLite;

public class Insumos_act extends AppCompatActivity {

    private EditText edcodigo, ednombre, edprecio, edstock;
    private Button bguardar, bmostrar, beliminar, bact;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);

        edcodigo = (EditText) findViewById(R.id.edtxcodigo);
        ednombre = (EditText) findViewById(R.id.edtxnombre);
        edprecio = (EditText) findViewById(R.id.edtxprecio);
        edstock = (EditText) findViewById(R.id.edtxstock);
        bguardar = (Button) findViewById(R.id.btnguardar);
        bmostrar = (Button) findViewById(R.id.btnmostrar);
        beliminar = (Button) findViewById(R.id.btneliminar);
        bact = (Button) findViewById(R.id.btnact);

    }

    public void AñadirInsumos(View view)
    {
        AdminSQLite admin = new AdminSQLite(this, "ficheros", null , 1);
        SQLiteDatabase DB = admin.getWritableDatabase();

        if(VerificarDatos() == true)
        {
            ContentValues cont = new ContentValues();//Ingresar varios valores
            cont.put("codigo", edcodigo.getText().toString());
            cont.put("nombre", ednombre.getText().toString());
            cont.put("precio", edprecio.getText().toString());
            cont.put("stock", edstock.getText().toString());

            DB.insert("insumos", null, cont);
            DB.close();

            Toast.makeText(this,"Has guardado un insumo", Toast.LENGTH_LONG).show();

            LimpiarTexto();
        }
        else
        {
            Toast.makeText(this, "Debe ingresar todos los datos del insumo", Toast.LENGTH_LONG).show();
        }
    }

    public void MostrarInsumos(View view)
    {
        AdminSQLite admin = new AdminSQLite(this, "ficheros", null, 1);
        SQLiteDatabase DB = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        if(!codigo.isEmpty())
        {
            Cursor fila = DB.rawQuery("SELECT nombre, precio, stock FROM insumos WHERE codigo="+codigo, null);

            if(fila.moveToFirst())
            {
                ednombre.setText(fila.getString(0));
                edprecio.setText(fila.getString(1));
                edstock.setText(fila.getString(2));
            }
            else
            {
                Toast.makeText(this,"No hay campos en la tabla de insumos", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"No hay insumo con el codigo asociado", Toast.LENGTH_LONG).show();
        }

        DB.close();
    }

    public void EliminarInsumos(View view)
    {
        AdminSQLite admin = new AdminSQLite(this, "ficheros", null, 1);
        SQLiteDatabase DB = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        DB.delete("insumos", "codigo="+codigo, null);
        DB.close();

        Toast.makeText(this, "Se ha eliminado un insumo", Toast.LENGTH_LONG).show();

        LimpiarTexto();
    }

    public void ActualizarInsumos(View view)
    {
        AdminSQLite admin = new AdminSQLite(this, "ficheros", null, 1);
        SQLiteDatabase DB = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        ContentValues cont = new ContentValues();
        cont.put("codigo", edcodigo.getText().toString());
        cont.put("nombre", ednombre.getText().toString());
        cont.put("precio", edprecio.getText().toString());
        cont.put("stock", edstock.getText().toString());

        if(VerificarDatos())
        {
            DB.update("insumos", cont, "codigo="+ codigo, null);
            DB.close();

            Toast.makeText(this,"Has actualizado un insumo", Toast.LENGTH_LONG).show();

            LimpiarTexto();
        }
        else
        {
            Toast.makeText(this, "Debe ingresar codigo de insumo", Toast.LENGTH_LONG).show();
        }

        LimpiarTexto();
    }

    public boolean VerificarDatos()
    {
        if (!edcodigo.getText().toString().isEmpty()
            ||!ednombre.getText().toString().isEmpty()
            ||!edprecio.getText().toString().isEmpty()
            ||!edstock.getText().toString().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void LimpiarTexto()
    {
        edcodigo.setText("");
        ednombre.setText("");
        edprecio.setText("");
        edstock.setText("");
    }
}