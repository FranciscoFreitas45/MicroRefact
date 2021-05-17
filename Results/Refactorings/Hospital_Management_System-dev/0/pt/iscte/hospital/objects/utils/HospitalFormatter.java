import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
public class HospitalFormatter {

 public  String PHONE;


public String formatValue(Number value,String formatString){
    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
    formatSymbols.setDecimalSeparator('.');
    formatSymbols.setGroupingSeparator(' ');
    DecimalFormat formatter = new DecimalFormat(formatString, formatSymbols);
    return formatter.format(value);
}


}