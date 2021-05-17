public interface ProfesorRep {

   public List<Profesor> findByCompleteNameList(String nombre,String apellido_paterno,String apellido_materno);
}