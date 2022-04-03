package com.cocay.sicecd.dto;
 public class ErroresDTO {

 private  String cdMensaje;

 private  int stEstado;


public int getStEstado(){
    return stEstado;
}


public void setCdMensaje(String cdMensaje){
    this.cdMensaje = cdMensaje;
}


public void setStEstado(int stEstado){
    this.stEstado = stEstado;
}


public String getCdMensaje(){
    return cdMensaje;
}


}