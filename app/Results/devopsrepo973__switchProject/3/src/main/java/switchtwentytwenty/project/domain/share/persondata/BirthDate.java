package switchtwentytwenty.project.domain.share.persondata;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidDateException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class BirthDate implements InternalDate,ValueObject{

 private  Date date;

 private  SimpleDateFormat dateFormat;

// Constructor Methods
/**
 * Sole constructor
 *
 * @param date
 */
public BirthDate(String date) throws InvalidDateException {
    this.date = stringToDate(date);
    checkDate();
}
public Date stringToDate(String date){
    DateFormat format = new SimpleDateFormat(Constants.BIRTH_DATE_FORMAT);
    // é uma flag para que a data esteja sempre entre os limites corretos
    format.setLenient(false);
    try {
        return format.parse(date);
    } catch (ParseException ignored) {
        throw new InvalidDateException("Invalid date");
    }
}


@Override
public int hashCode(){
    return Objects.hash(date);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    BirthDate birthDate1 = (BirthDate) o;
    return Objects.equals(date, birthDate1.date);
}


@Override
public String toString(){
    return this.dateFormat.format(this.date);
}


public void checkDate(){
    Date todayDate = new Date();
    if (!todayDate.after(date)) {
        throw new InvalidDateException("Date is from the future!");
    }
}


}