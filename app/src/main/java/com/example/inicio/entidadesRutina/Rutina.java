package com.example.inicio.entidadesRutina;

public class Rutina {
    private int id;
    private String habito;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;
    private String sabado;
    private String domingo;

    private String L ="L";
    private String Ma ="Ma";
    private String Mi ="Mi";
    private String J ="J";
    private String V ="V";
    private String S ="S";
    private String D ="D";



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabito() {
        return habito;
    }

    public void setHabito(String habito) {
        this.habito = habito;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        if(lunes.equals("1")){
            this.lunes="L  ";
        }else{
            this.lunes="";
        }
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        if(martes.equals("1")){
        this.martes="Ma  ";
        }else{
            this.martes="";
        }

    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        if(miercoles.equals("1")){
            this.miercoles="Mi  ";
        }else{
            this.miercoles="";
        }
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        if(jueves.equals("1")){
            this.jueves="J  ";
        }else{
            this.jueves="";
        }
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        if(viernes.equals("1")){
            this.viernes="V  ";
        }else{
            this.viernes="";
        }
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        if(sabado.equals("1")){
            this.sabado="S  ";
        }else{
            this.sabado="";
        }
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        if(domingo.equals("1")){
            this.domingo="D  ";
        }else{
            this.domingo="";
        }
    }
}
