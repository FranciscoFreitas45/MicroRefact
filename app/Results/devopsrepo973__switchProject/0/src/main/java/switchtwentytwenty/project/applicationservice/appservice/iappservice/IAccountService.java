package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
public interface IAccountService {


public MoneyValue checkChildCashAccountBalance(String parentID,String childID,String accountID)
;

public MoneyValue checkAccountBalance(String accountID,String holderID)
;

public AccountOutDTO addBankAccount(String designation,String personIDString,String accountType)
;

public PersonalCashAccountOutDTO createPersonalCashAccount(String personIDString,double cashAmount,String designation)
;

public List<PersonalCashAccountOutDTO> getListOfAccounts(String personID)
;

public FamilyCashAccountOutDTO getFamilyCashAccount(String username,String role)
;

public FamilyCashAccountOutDTO createFamilyCashAccount(String familyID,String adminID,double cashAmount,String designation)
;

public MoneyValue getCashAccountBalance(String holderID,String accountID)
;

}