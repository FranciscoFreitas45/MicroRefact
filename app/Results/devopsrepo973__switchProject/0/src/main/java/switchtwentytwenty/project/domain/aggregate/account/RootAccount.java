package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.ID;
import java.util.Objects;
public class RootAccount {

 private  AccountID accountID;

 private  Designation accountDesignation;

// Constructor Methods
/**
 * Sole constructor
 *
 * @param accountDesignation - designation of the account
 */
public RootAccount(AccountID accountID, Designation accountDesignation) {
    this.accountID = accountID;
    this.accountDesignation = accountDesignation;
}
public boolean isSameDesignation(Designation designation){
    return this.accountDesignation.equals(designation);
}


public Designation getDesignation(){
    return this.accountDesignation;
}


@Override
public int hashCode(){
    return Objects.hash(accountID);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    RootAccount that = (RootAccount) o;
    return Objects.equals(accountID, that.accountID);
}


public AccountID getId(){
    return this.accountID;
}


public boolean hasSameID(ID accountID){
    return this.accountID.equals(accountID);
}


public boolean sameValueAs(Account other){
    return this.accountID.equals(other.getID()) && this.accountDesignation.equals(other.getDesignation());
}


}