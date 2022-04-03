package com.cocay.sicecd.DTO;
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


public String getNombre(){
    return nombre;
}


public String getTipoCurso(){
    return tipoCurso;
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


public String getClave(){
    return clave;
}


public String getfInicio(){
    return fInicio;
}


public String getfTermino(){
    return fTermino;
}


public int getTotal(){
    return total;
}


}