package com.roberto.calculadoraimc.utilidades;


import android.util.Log;

import com.roberto.calculadoraimc.entidades.Seguimiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by PCCasa on 21/02/2017.
 */

public class ComparaSeguimientoFechas implements Comparator<Seguimiento> {
    @Override
    public int compare(Seguimiento o1, Seguimiento o2) {
        int resultado=0;
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date date01 = sdf.parse(o1.getFechaRegistro());
            Date date02 = sdf.parse(o2.getFechaRegistro());
            resultado=date01.compareTo(date02);

        }catch(ParseException e) {

           Log.e(getClass().getCanonicalName(),e.getMessage());
        }

        return resultado;

    }
}
