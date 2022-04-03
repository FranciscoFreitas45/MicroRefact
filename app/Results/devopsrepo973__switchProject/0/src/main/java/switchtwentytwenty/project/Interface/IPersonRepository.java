package switchtwentytwenty.project.Interface;
public interface IPersonRepository {

   public Person findByID(Email id);
   public void save(Person entity);
   public PersonList findByFamilyID(FamilyID familyID);
}