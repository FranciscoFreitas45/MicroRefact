package Interface;
public interface TemplateRepository {

   public List<Template> findByTemplettypeAndOrgi(String templettype,String orgi);
   public Template findByIdAndOrgi(String id,String orgi);
}