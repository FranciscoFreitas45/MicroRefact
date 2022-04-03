package switchtwentytwenty.project.domain.share.transactiondata;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class SystemDateEntry implements InternalDate{

 private  Date date;

 private  SimpleDateFormat dateFormat;

// Constructor Methods
/**
 * Constructor date
 *
 * @param date - date instance
 * @throws ParseException - wrong format
 */
public SystemDateEntry(Date date) throws ParseException {
    if (date == null) {
        throw new NullPointerException("Date is null");
    }
    String dateString = this.dateFormat.format(date);
    this.date = this.dateFormat.parse(dateString);
}
@Override
public String toString(){
    return this.dateFormat.format(this.date);
}


}