public interface UserValidationService {

   public UserValidationService setUser(User user);
   public UserValidationService validLicenseNumber();
   public UserValidationService validSpeciality(String speciality);
   public boolean isValid();
   public ModelMap getErrorModelMap();
   public UserValidationService clear();
   public UserValidationService validName();
   public UserValidationService validPassword();
   public UserValidationService samePassword(String passwordRetyped);
}