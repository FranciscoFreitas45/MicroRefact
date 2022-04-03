package com.softserve.edu.Resources.entity;
 import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
@Entity
@Table(name = "person", uniqueConstraints = { @UniqueConstraint(columnNames = { "passport_series", "passport_number" }) })
public class Person extends Owner{

@Column(name = "first_name")
@JsonProperty("first_name")
@NotEmpty
 private  String firstName;

@Column(name = "last_name")
@JsonProperty("last_name")
@NotEmpty
 private  String lastName;

@Column(name = "middle_name")
@JsonProperty("middle_name")
 private  String middleName;

@Column(name = "passport_series")
@JsonProperty("passport_series")
@NotEmpty
@Length(max = 2, min = 2, message = "This is invalid passport series.")
 private  String passportSeries;

@Column(name = "passport_number")
@JsonProperty("passport_number")
@Length(max = 6, min = 6, message = "This is invalid passport number.")
 private  String passportNumber;

@Column(name = "identifier_number", unique = true)
@JsonProperty("identifier_number")
@Pattern(regexp = "[\\d]{10}", message = "Should be 10 digits. E.g., 1087963214")
 private  String identifierNumber;

public Person() {
}
@Override
public String ownerType(){
    return "Person";
}


public String getPassportNumber(){
    return passportNumber;
}


public String getIdentifierNumber(){
    return identifierNumber;
}


public String getPassportSeries(){
    return passportSeries;
}


public String getLastName(){
    return lastName;
}


public String getMiddleName(){
    return middleName;
}


public Person setLastName(String lastName){
    this.lastName = lastName;
    return this;
}


public Person setIdentifierNumber(String identifierNumber){
    this.identifierNumber = identifierNumber;
    return this;
}


public Person setPassportNumber(String passportNumber){
    this.passportNumber = passportNumber;
    return this;
}


public Person setFirstName(String firstName){
    this.firstName = firstName;
    return this;
}


@Override
public int hashCode(){
    int result = super.hashCode();
    result = 31 * result + firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + middleName.hashCode();
    result = 31 * result + passportSeries.hashCode();
    result = 31 * result + passportNumber.hashCode();
    result = 31 * result + identifierNumber.hashCode();
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Person))
        return false;
    if (!super.equals(o))
        return false;
    Person person = (Person) o;
    if (!firstName.equals(person.firstName))
        return false;
    if (!lastName.equals(person.lastName))
        return false;
    if (!middleName.equals(person.middleName))
        return false;
    if (!passportSeries.equals(person.passportSeries))
        return false;
    if (!passportNumber.equals(person.passportNumber))
        return false;
    return identifierNumber.equals(person.identifierNumber);
}


public Person setPassportSeries(String passportSeries){
    this.passportSeries = passportSeries;
    return this;
}


@Override
public String toString(){
    return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", middleName='" + middleName + '\'' + ", passportSeries='" + passportSeries + '\'' + ", passportNumber='" + passportNumber + '\'' + "} " + super.toString();
}


@Override
public String customToString(){
    return firstName + " " + lastName + " " + middleName + ", " + "IPN: " + identifierNumber + ", " + "Passport: " + passportSeries + " " + passportNumber + ".";
}


public Person setMiddleName(String middleName){
    this.middleName = middleName;
    return this;
}


public String getFirstName(){
    return firstName;
}


}