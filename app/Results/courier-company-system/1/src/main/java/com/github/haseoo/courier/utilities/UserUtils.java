package com.github.haseoo.courier.utilities;
 import com.github.haseoo.courier.enums.EmployeeType;
import com.github.haseoo.courier.enums.UserType;
import com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions.InvalidPeselException;
import com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions.InvalidPeselFormatException;
import com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions.employees.InvalidEmployeeInstanceException;
import com.github.haseoo.courier.models;
import com.github.haseoo.courier.security.UserRole;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import com.github.haseoo.courier.enums.UserType.ADMIN;
import com.github.haseoo.courier.utilities.Constants;
import lombok.AccessLevel.PRIVATE;
@NoArgsConstructor(access = PRIVATE)
public class UserUtils {

 private  Map<Class<? extends UserModel>,UserType> userTypeMap;

 private  Map<Class<? extends UserModel>,UserRole> userRoleMap;


public EmployeeType getEmployeeType(EmployeeModel employeeModel){
    if (employeeModel instanceof CourierModel) {
        return EmployeeType.COURIER;
    }
    if (employeeModel instanceof LogisticianModel) {
        return EmployeeType.LOGISTICIAN;
    }
    throw new InvalidEmployeeInstanceException();
}


public UserRole getUserRole(UserModel userModel){
    if (userModel.getUserName().equals(ADMIN_USERNAME)) {
        return UserRole.ADMIN;
    }
    return userRoleMap.get(userModel.getClass());
}


public int getCheckDigit(String pesel){
    return Integer.parseInt(pesel.substring(pesel.length() - 1));
}


public boolean validatePeselString(String pesel){
    return pesel.matches(PESEL_REGEX) || pesel.length() != PESEL_LENGTH;
}


public UserType getUserType(UserModel userModel){
    if (userModel.getUserName().equals(ADMIN_USERNAME)) {
        return ADMIN;
    }
    return userTypeMap.get(userModel.getClass());
}


public boolean isChecksumValid(String pesel){
    int checksum = calculateChecksum(pesel);
    int checkDigit = getCheckDigit(pesel);
    checksum %= TEN_CUT;
    checksum = TEN_CUT - checksum;
    checksum %= TEN_CUT;
    return checksum == checkDigit;
}


public boolean tryPesel(String pesel){
    if (validatePeselString(pesel)) {
        return false;
    }
    return isChecksumValid(pesel);
}


public Integer calculateChecksum(String pesel){
    int checksum = 0;
    for (int i = 0; i < pesel.length() - 1; i++) {
        checksum += Integer.parseInt(pesel.substring(i, i + 1)) * peselWeights.get(i);
    }
    return checksum;
}


public void validatePesel(String pesel){
    if (validatePeselString(pesel)) {
        throw new InvalidPeselFormatException();
    }
    if (!isChecksumValid(pesel)) {
        throw new InvalidPeselException();
    }
}


}