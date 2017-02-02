package com.roberto.calculadoraimc;

/**
 * Created by PCCasa on 01/02/2017.
 */

public class Codificar {



    /**
     * Haced el cuerpo de los métodos
     * y un pequeño main para probarse
     * de forma que luego podamos
     * usar esta clase para codificar
     * decodificar ficheros
     *
     * Un método codifica el mensaje
     * y llamando al método decodifica,
     * recuperaría el mensaje origina
     *
     * @author Administrador
     *
     */

    public static String codifica (String cadena)
    {
        String cad_codificada = "";
        String aux = "";
        int i_aux = 0 ;
        char c_aux = 0;

        for(int i  = 0; i < cadena.length(); i++)
        {
            i_aux = (int) cadena.charAt(i);
            c_aux = (char) ((i_aux + 9) - i);
            aux = aux + c_aux;
        }

        for (int i = aux.length() - 1; i > -1; i--)
        {
            c_aux = aux.charAt(i);
            cad_codificada = cad_codificada + c_aux;
        }

        return cad_codificada;
    }


    public static String decodifica (String cadena)
    {
        String cad_decodificada = "";
        String aux = "";
        int i_aux = 0;
        char c_aux = 0;

        for (int i = cadena.length() - 1; i > -1; i--)
        {
            c_aux = cadena.charAt(i);
            aux = aux + c_aux;
        }


        for(int i  = 0; i < aux.length(); i++)
        {
            i_aux = (int) aux.charAt(i);
            c_aux = (char) ((i_aux + i) - 9);
            cad_decodificada = cad_decodificada + c_aux;
        }



        return cad_decodificada;
    }

}