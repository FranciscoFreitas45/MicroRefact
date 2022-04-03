package Interface;
public interface OrganRepository {

   public List<Organ> findAll(Specification<Organ> spec);
}