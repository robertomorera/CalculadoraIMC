package com.roberto.calculadoraimc.entidades;

import android.util.Log;

import com.roberto.calculadoraimc.utilidades.Constantes;
import com.roberto.calculadoraimc.excepciones.IMCException;

/**
 * Created by PCCasa on 21/12/2016.
 */

public class CalculadoraIMC {
    /*
       Atributo estatura
     */
    private float estatura;
    /*
      Atributo peso
     */
    private float peso;

    /*
     Constructor público de la clase CalculadoraIMC
     */
    public CalculadoraIMC(float estatura, float peso) {
        this.estatura = estatura;
        this.peso = peso;
    }

    /*
     Método para calcular el IMC.
     @throws IMCException.
     @return float IMC
     */
    public float calcularIMC() throws IMCException {

        //Comprobamos las posibles indeterminaciones.

        float IMC = 0;

        if (estatura == 0 && peso == 0) {

            Log.d(getClass().getCanonicalName(), Constantes.MENSAJE_NAN);
            throw new IMCException(Constantes.MENSAJE_NAN);

        } else if (estatura == 0) {
            Log.d(getClass().getCanonicalName(), Constantes.MENSAJE_INFINITO);
            throw new IMCException(Constantes.MENSAJE_INFINITO);
        } else {

            IMC = (peso) / (estatura * estatura);
            Log.d(getClass().getCanonicalName(), Constantes.LOG_IMC + IMC);
        }
        return IMC;

    }

}