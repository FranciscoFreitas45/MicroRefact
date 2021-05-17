public interface GrupoRequest {

   public void setGrupos(List<Grupo> grupos,int pk_id_curso);
   public void setFk_id_grupo(Grupo fk_id_grupo,int pk_id_curso);
   public List<Grupo> getGrupos(int pk_id_curso);
   public Grupo getFk_id_grupo(int pk_id_curso);
}