package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
public class LegalRecord extends DomainEntity{

 private  String title;

 private  String description;

 private  String legalName;

 private  String vatNumber;

 private  List<String> laws;

public LegalRecord() {
    super();
}
@NotBlank
public String getTitle(){
    return this.title;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getLaws(){
    return this.laws;
}


@NotBlank
@Pattern(regexp = "((AT)?U[0-9]{8}|(BE)?0[0-9]{9}|(BG)?[0-9]{9,10}|(CY)?[0-9]{8}L|(CZ)?[0-9]{8,10}|(DE)?[0-9]{9}|(DK)?[0-9]{8}|(EE)?[0-9]{9}|(EL|GR)?[0-9]{9}|(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]|(FI)?[0-9]{8}|(FR)?[0-9A-Z]{2}[0-9]{9}|(GB)?([0-9]{9}([0-9]{3})?|[A-Z]{2}[0-9]{3})|(HU)?[0-9]{8}|(IE)?[0-9]S[0-9]{5}L|(IT)?[0-9]{11}|(LT)?([0-9]{9}|[0-9]{12})|(LU)?[0-9]{8}|(LV)?[0-9]{11}|(MT)?[0-9]{8}|(NL)?[0-9]{9}B[0-9]{2}|(PL)?[0-9]{10}|(PT)?[0-9]{9}|(RO)?[0-9]{2,10}|(SE)?[0-9]{12}|(SI)?[0-9]{8}|(SK)?[0-9]{10})")
public String getVatNumber(){
    return this.vatNumber;
}


@NotBlank
public String getDescription(){
    return this.description;
}


@NotBlank
public String getLegalName(){
    return this.legalName;
}


}