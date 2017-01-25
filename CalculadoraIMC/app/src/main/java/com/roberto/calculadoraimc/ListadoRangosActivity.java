package com.roberto.calculadoraimc;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


public class ListadoRangosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_rangos);
        //Obtenemos el ListView.
        ListView listView=(ListView)findViewById(R.id.lista_rangos);
        ///Seteamos el adapter asociado al ListView.
        listView.setAdapter(new ConsultaRangosAdapter(this));
        //Asociamos el listener a los distintos elementos del ListView.
        ListenerListadosRangos listenerListadosRangos= new ListenerListadosRangos(this);
        listView.setOnItemClickListener(listenerListadosRangos);

    }
}
