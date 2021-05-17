public class InscripcionDto {

 private String identificador;

 private String idGrupo;

 private String idProfesor;

 private String calificacion;

 private Boolean aprobado;

 private String jsonG;

 private String jsonP;

 private String calif;

 private String jsonNombres;

 private String temp_curso;


public String getJsonG(){
    return jsonG;
}


public String getCalificacion(){
    return calificacion;
}


public void setIdGrupo(String idGrupo){
    this.idGrupo = idGrupo;
}


public void setIdentificador(String identificador){
    this.identificador = identificador;
}


public void setCalif(String calif){
    this.calif = calif;
}


public String getIdGrupo(){
    return idGrupo;
}


public String getIdentificador(){
    return identificador;
}


public void setAprobado(Boolean aprobado){
    this.aprobado = aprobado;
}


public void setJsonG(String jsonG){
    this.jsonG = jsonG;
}


public String getCalif(){
    return calif;
}


public String getIdProfesor(){
    return idProfesor;
}


public String getTemp_curso(){
    return temp_curso;
}


public void setJsonP(String jsonP){
    this.jsonP = jsonP;
}


public Boolean getAprobado(){
    return aprobado;
}


public boolean isAprobado(){
    return aprobado;
}


public void setJsonNombres(String jsonNombres){
    this.jsonNombres = jsonNombres;
}


public String getJsonP(){
    return jsonP;
}


public void setIdProfesor(String idProfesor){
    this.idProfesor = idProfesor;
}


public void setCalificacion(String calificacion){
    this.calificacion = calificacion;
}


public String getJsonNombres(){
    return jsonNombres;
}


public void setTemp_curso(String temp_curso){
    this.temp_curso = temp_curso;
}


}