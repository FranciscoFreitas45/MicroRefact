public interface SpecialityValidationService {

   public SpecialityValidationService clear();
   public SpecialityValidationService setSpeciality(Speciality speciality);
   public SpecialityValidationService validName();
   public SpecialityValidationService validLength();
   public SpecialityValidationService validPrice();
   public boolean isValid();
   public ModelMap getErrorModelMap();
}