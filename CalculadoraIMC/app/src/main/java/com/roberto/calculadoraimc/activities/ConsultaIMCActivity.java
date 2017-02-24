package com.roberto.calculadoraimc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.roberto.calculadoraimc.utilidades.ComparaSeguimientoFechas;
import com.roberto.calculadoraimc.adapters.ConsultaIMCAdapter;
import com.roberto.calculadoraimc.R;
import com.roberto.calculadoraimc.entidades.Seguimiento;

import java.util.Collections;
import java.util.List;

/**
 * Clase que implementa el ListView para visualizar el historico
 * de IMCs del usuario logeado actualmente:
 */
public class ConsultaIMCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_imc);
        //Obtenemos el ListView.
        ListView listadoIMC=(ListView)findViewById(R.id.lista_IMC);
        //Le asociamos su adapter para pintar los diferentes elementos.
        listadoIMC.setAdapter(new ConsultaIMCAdapter(this));
    }

    /**
     * Menu de la actividad de resultados de IMC
     * del usuario logeado.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,1,R.string.titulo_ordenar_IMC);
        menu.add(Menu.NONE,2,2,R.string.titulo_ordenar_fecha);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Obtenemos la lista para ordenar.
        List<Seguimiento> lista=ConsultaIMCAdapter.getRegistrosUsuario();
        switch(item.getItemId()){
            //Ordenamos por IMC.
            case 1:
                Collections.sort(lista);
                break;
            //Ordenamos por fecha de registro.
            case 2:
                Collections.sort(lista,new ComparaSeguimientoFechas());
                break;
            default:
                break;

        }
        ListView listadoIMC=(ListView)findViewById(R.id.lista_IMC);
        //Le asociamos su adapter para pintar los diferentes elementos.
        listadoIMC.setAdapter(new ConsultaIMCAdapter(this,lista));
        return true;
    }
}
