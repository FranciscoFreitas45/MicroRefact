public interface InscripcionRequest {

   public List<Inscripcion> getInscripciones(int pk_id_grupo);
   public void setInscripciones(List<Inscripcion> inscripciones,int pk_id_grupo);
}