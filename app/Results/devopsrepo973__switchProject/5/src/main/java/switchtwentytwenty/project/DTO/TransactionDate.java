package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class TransactionDate implements InternalDate{

 private  Date date;

 private  SimpleDateFormat dateFormat;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

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
@Override
public String toString(){
    return this.dateFormat.format(this.date);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toString"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}