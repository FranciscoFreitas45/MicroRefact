package switchtwentytwenty.project.domain.share.familydata;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class RegistrationDate implements InternalDate,ValueObject{

 private  SimpleDateFormat dateFormat;

 private  Date date;

// Constructor Methods
/**
 * Sole Constructor
 */
public RegistrationDate() {
    this.date = new Date(System.currentTimeMillis());
}
@Override
public int hashCode(){
    return Objects.hash(date);
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    RegistrationDate that = (RegistrationDate) other;
    return Objects.equals(date, that.date);
}


public void setDate(Date dateToInput){
    this.date = new Date(dateToInput.getTime());
}


public Date getDate(){
    return new Date(this.date.getTime());
}


@Override
public String toString(){
    return this.dateFormat.format(this.date);
}


}