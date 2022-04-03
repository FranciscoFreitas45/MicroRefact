package com.cocay.sicecd.dto;
 public class CursoDto {

 private String clave;

 private String tipo;

 private String horas;

 private String nombre;

 private String fInicio;

 private String fTermino;

 private String idCurso;

 private String tipoCurso;

 private int total;


public void setTotal(int total){
    this.total = total;
}


public void setHoras(String horas){
    this.horas = horas;
}


public String getNombre(){
    return nombre;
}


public String getTipoCurso(){
    return tipoCurso;
}


public void setTipo(String tipo){
    this.tipo = tipo;
}


public void setIdCurso(String idCurso){
    this.idCurso = idCurso;
}


public String getHoras(){
    return horas;
}


public String getIdCurso(){
    return idCurso;
}


public String getTipo(){
    return tipo;
}


public void setTipoCurso(String tipoCurso){
    this.tipoCurso = tipoCurso;
}


public String getClave(){
    return clave;
}


public void setClave(String clave){
    this.clave = clave;
}


public String getfInicio(){
    return fInicio;
}


public void setfTermino(String fTermino){
    this.fTermino = fTermino;
}


public String getfTermino(){
    return fTermino;
}


public int getTotal(){
    return total;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setfInicio(String fInicio){
    this.fInicio = fInicio;
}


}