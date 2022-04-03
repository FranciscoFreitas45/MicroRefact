package com.cocay.sicecd.dto;
 import com.cocay.sicecd.Interface.ProfesorDto;
import com.cocay.sicecd.Interface.InscripcionDto;
public class FiltroCorreoDTO {

 private  CursoDto cursoDto;

 private  ProfesorDto profesorDto;

 private  InscripcionDto inscripcionDto;

 private  String nombre;

 private  String clave;

 private  String tipo;

 private  String fInicio;

 private  String fTermino;

 private  String nombres;

 private  String rfc;

 private  String estado;

 private  String turno;

 private  String idGrupo;


public String getNombres(){
    return nombres;
}


public void setInscripcionDto(InscripcionDto inscripcionDto){
    this.inscripcionDto = inscripcionDto;
}


public void setNombres(String nombres){
    this.nombres = nombres;
}


public String getClave(){
    return clave;
}


public void setClave(String clave){
    this.clave = clave;
}


public void setfTermino(String fTermino){
    this.fTermino = fTermino;
}


public void setCursoDto(CursoDto cursoDto){
    this.cursoDto = cursoDto;
}


public void setProfesorDto(ProfesorDto profesorDto){
    this.profesorDto = profesorDto;
}


public void setTurno(String turno){
    this.turno = turno;
}


public String getfTermino(){
    return fTermino;
}


public void setEstado(String estado){
    this.estado = estado;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setfInicio(String fInicio){
    this.fInicio = fInicio;
}


public CursoDto getCursoDto(){
    return cursoDto;
}


public void setIdGrupo(String idGrupo){
    this.idGrupo = idGrupo;
}


public String getNombre(){
    return nombre;
}


public InscripcionDto getInscripcionDto(){
    return inscripcionDto;
}


public void setTipo(String tipo){
    this.tipo = tipo;
}


public String getIdGrupo(){
    return idGrupo;
}


public String getEstado(){
    return estado;
}


public String getTurno(){
    return turno;
}


public String getTipo(){
    return tipo;
}


public void setRfc(String rfc){
    this.rfc = rfc;
}


public String getRfc(){
    return rfc;
}


public String getfInicio(){
    return fInicio;
}


public ProfesorDto getProfesorDto(){
    return profesorDto;
}


}