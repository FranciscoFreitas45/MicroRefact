import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.User;
public interface UserValidationService {


public UserValidationService validSex()


public UserValidationService validAddress()


public UserValidationService validPatientNumber()


public UserValidationService validAccount()


public UserValidationService notValidImageSize()


public UserValidationService validCity()


public UserValidationService validSpeciality(String speciality)


public UserValidationService validDocumentType()


public UserValidationService notValidImageType()


public UserValidationService validPassword()


public UserValidationService validEmail()


public UserValidationService validLicenseNumber()


public UserValidationService setUser(User user)


public UserValidationService validPhone()


public UserValidationService validEmail2()


public UserValidationService validNationality()


public UserValidationService validNif()


public boolean isValid()


public UserValidationService clear()


public UserValidationService validDocumentNumber()


public UserValidationService validNifUnique()


public UserValidationService notValidPhotoUpload()


public ModelMap getErrorModelMap()


public UserValidationService validPostCode()


public UserValidationService validPatientNumberUnique()


public UserValidationService validName()


public UserValidationService validDocumentNumberUnique()


public UserValidationService validBirthday()


public UserValidationService validUsername()


public UserValidationService samePassword(String passwordRetyped)


}