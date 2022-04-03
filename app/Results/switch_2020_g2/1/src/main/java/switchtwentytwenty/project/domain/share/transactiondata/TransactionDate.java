package switchtwentytwenty.project.domain.share.transactiondata;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class TransactionDate implements InternalDate{

 private  Date date;

 private  SimpleDateFormat dateFormat;

// Constructor Methods
/**
 * Constructor string
 *
 * @param dateString - date in string
 * @throws ParseException - wrong format
 */
public TransactionDate(String dateString) throws ParseException {
    // "February 942, 1996" will NOT be treated as being equivalent to the 941st day after February 1, 1996
    dateFormat.setLenient(false);
    this.date = this.dateFormat.parse(dateString);
}/**
 * Constructor date
 *
 * @param date - date instance
 * @throws ParseException - wrong format
 */
public TransactionDate(Date date) throws ParseException {
    if (date == null) {
        throw new NullPointerException("Date is null");
    }
    String dateString = this.dateFormat.format(date);
    this.date = this.dateFormat.parse(dateString);
}
public int compare(TransactionDate other){
    return this.date.compareTo(other.date);
}


@Override
public int hashCode(){
    return Objects.hash(date, dateFormat);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    TransactionDate date1 = (TransactionDate) o;
    return Objects.equals(date, date1.date) && Objects.equals(dateFormat, date1.dateFormat);
}


public boolean isBetween(TransactionDate startDate,TransactionDate endDate){
    // inclusive given dates
    return startDate.compare(this) * this.compare(endDate) >= 0;
}


@Override
public String toString(){
    return this.dateFormat.format(this.date);
}


}