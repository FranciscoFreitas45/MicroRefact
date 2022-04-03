package switchtwentytwenty.project.Interface;
public interface IPersonRepository {

   public Person findByID(Email id);
   public boolean existsByID(Email id);
   public void save(Person entity);
   public boolean existsByFamilyIDAndVat(FamilyID familyID,VAT vat);
}