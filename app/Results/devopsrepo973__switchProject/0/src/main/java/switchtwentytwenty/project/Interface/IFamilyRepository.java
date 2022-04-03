package switchtwentytwenty.project.Interface;
public interface IFamilyRepository {

   public Family findByID(FamilyID id);
   public FamilyJPA save(Family entity);
}