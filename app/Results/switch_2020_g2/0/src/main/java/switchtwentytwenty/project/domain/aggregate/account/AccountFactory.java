package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.dto.todomaindto.AccountJpaToDomainDTO;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.util.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Set;
public class AccountFactory {

// Constructor methods
/**
 * Sole constructor
 */
private AccountFactory() {
}
public String getClassPath(String accountType){
    Properties properties = Util.loadConfigurationFile(Constants.ACCOUNT_TYPE_CONFIG_FILE);
    Set<String> keys = properties.stringPropertyNames();
    for (String key : keys) {
        if (accountType.equals(key)) {
            return properties.getProperty(key);
        }
    }
    throw new IllegalArgumentException("Invalid account type");
}


public Account createBankAccount(AccountID bankAccountID,AccountDesignation designation,String accountType){
    try {
        String classDirectory = getClassPath(accountType);
        Class<?> className = Class.forName(classDirectory);
        Constructor<?> constructor = className.getConstructor(AccountID.class, AccountDesignation.class);
        return (Account) constructor.newInstance(bankAccountID, designation);
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IOException exception) {
        throw new AccountNotCreatedException("Failure to create account");
    }
}


public Account createCashAccount(AccountID accountID,AccountDesignation accountDesignation,MoneyValue initialAmount){
    return new CashAccount(accountID, initialAmount, accountDesignation);
}


public Account createAccount(AccountJpaToDomainDTO accountJpaToDomainDTO){
    String accountType = accountJpaToDomainDTO.getAccountType();
    AccountID accountID = accountJpaToDomainDTO.getAccountID();
    AccountDesignation designation = accountJpaToDomainDTO.getAccountDesignation();
    if (accountType.equals(Constants.CASH_ACCOUNT_TYPE)) {
        MoneyValue cashAmount = accountJpaToDomainDTO.getCashAmount();
        return createCashAccount(accountID, designation, cashAmount);
    } else {
        return createBankAccount(accountID, designation, accountType);
    }
}


}