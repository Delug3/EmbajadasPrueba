package com.delug3.embajadas;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Embajadas extends AppCompatActivity {
    String SQLiteQuery;
    SQLiteDatabase SQLITEDATABASE;
    EditText edtlong,edtlat;
    String longitud,latitud,fecha;
    Boolean EditVacio ;
    Button btnbuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.embajadas_main);


        btnbuscar = (Button) findViewById(R.id.btnbuscar);

        edtlong = (EditText) findViewById(R.id.edtlong);

        edtlat = (EditText) findViewById(R.id.edtlat);

        btnbuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();
                String longvalor = edtlong.getText().toString();
                String latvalor = edtlat.getText().toString();


                //CharSequence text = "Buscando...";
              // int duration = Toast.LENGTH_SHORT;

              // Toast toast = Toast.makeText(context, longvalor, duration);
              // Toast toast2 = Toast.makeText(context, latvalor, duration);

                //toast.show();
                //toast2.show();


                //crear base de datos y llamar metodo para los gettext con la info de los editext

                DBCreate();

                SubmitDataSQLiteDB();

                //prueba coordenadas manuales y fecha
                Intent intent = new Intent(context,GPSMaps.class);
                intent.putExtra("LONGITUD", longvalor);
                intent.putExtra("LATITUD", latvalor);
                intent.putExtra("FECHA", fecha);

                context.startActivity(intent);


            }
        });

    }
    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("EmbajadasDB", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS EmbajadasGPS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, longitud VARCHAR, latitud VARCHAR,fecha VARCHAR);");

        //NO TOCAR! funciona perfecto
        //añadir fecha para registro en historial(despues de fix a nearbylocations)
    }

    public void SubmitDataSQLiteDB(){

        longitud = edtlong.getText().toString();
        latitud = edtlat.getText().toString();
        Date today = new Date();
        //Fecha formateada segun pdf
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM hh:mm");
        fecha = formato.format(today);

        SiEditEstaVacio( longitud,latitud,fecha);

        if(EditVacio == true)
        {

            SQLiteQuery = "INSERT INTO EmbajadasGPS (longitud,latitud,fecha) VALUES('"+longitud+"','"+latitud+"', '"+fecha+"');";

            SQLITEDATABASE.execSQL(SQLiteQuery);

            Toast.makeText(Embajadas.this,"Datos Almacenados Correctamente", Toast.LENGTH_SHORT).show();

            LimpiarEdit();

        }
        else {

            Toast.makeText(Embajadas.this,"Rellena ambos campos", Toast.LENGTH_LONG).show();
        }
    }

    public void SiEditEstaVacio(String longitud,String latitud,String fecha ){

        if(TextUtils.isEmpty(longitud) || TextUtils.isEmpty(latitud)){

            EditVacio = false ;

        }
        else {
            EditVacio = true ;
        }
    }

    public void LimpiarEdit(){

        edtlong.getText().clear();
        edtlat.getText().clear();

    }

    public void localizame(View v) {
        startActivity(new Intent(this, GPSMaps.class));

    }

    public void verHistorial(View v) {
        startActivity(new Intent(this, Historial.class));

    }
}