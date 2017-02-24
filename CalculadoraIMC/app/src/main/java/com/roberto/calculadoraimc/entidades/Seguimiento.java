package com.roberto.calculadoraimc.entidades;

/**
 * Created by PCCasa on 13/02/2017.
 */

public class Seguimiento implements Comparable<Seguimiento> {

    //Atributo del valor de IMC a registrar.
    private String imc;

    //Atributo fecha de registro del IMC en base de datos.
    private String fechaRegistro;

    //Identificador del usuario logeado en a aplicación.
    private int idUsuario;

    private String literalIMC;
    /**
     * Constructor de la clase Seguimiento.
     * @param imc
     * @param fechaRegistro
     * @param idUsuario
     */
    public Seguimiento(String imc,String literalIMC,String fechaRegistro, int idUsuario) {
        this.imc = imc;
        this.literalIMC=literalIMC;
        this.fechaRegistro = fechaRegistro;
        this.idUsuario = idUsuario;
    }

    /**
     * Devuelve el valor del IMC registrado en base de datos.
     * @return
     */
    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    /**
     * Devuelve el literal asociado al IMC calculado.
     * @return
     */
    public String getLiteralIMC() {
        return literalIMC;
    }

    public void setLiteralIMC(String literalIMC) {
        this.literalIMC = literalIMC;
    }

    /**
     * Devuelve la fecha en la que se registro el IMC en base de datos.
     * @return
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Devuelve el identificador del usuario que realiza el registro del IMC.
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Compara los valores de IMC.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Seguimiento o) {
        int resultado=0;
        float IMCAtributo=Float.parseFloat(this.getImc());
        float IMCo=Float.parseFloat(o.getImc());
        if(IMCAtributo<IMCo){
            resultado=-1;
        }else if(IMCAtributo>IMCo){
            resultado=1;
        }else{
            resultado=0;
        }

        return resultado;

    }
}
