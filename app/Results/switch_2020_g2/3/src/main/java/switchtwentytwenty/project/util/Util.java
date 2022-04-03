package switchtwentytwenty.project.util;
 import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
public class Util {

private Util() {
}
public Date stringToDate(String date,String dateFormat){
    DateFormat format = new SimpleDateFormat(dateFormat);
    Date dateDt;
    try {
        dateDt = format.parse(date);
    } catch (ParseException ignored) {
        dateDt = null;
    }
    return dateDt;
}


public Properties loadConfigurationFile(String url){
    InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(url);
    if (inputStream == null) {
        throw new IOException("File not found");
    }
    Properties properties = new Properties();
    properties.load(inputStream);
    return properties;
}


public String[] getSystemDefaultCurrency(){
    String[] localeCouple;
    Properties properties = new Properties();
    InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("./Currency.properties");
    try {
        properties.load(inputStream);
    } catch (IOException exception) {
    }
    String locale = properties.getProperty("Portugal");
    localeCouple = locale.split("&");
    return localeCouple;
}


public void checkIfNull(List<T> list){
    for (T element : list) {
        if (element == null) {
            throw new IllegalArgumentException("Of of the elements in the list is null");
        }
    }
}


public String capitalizeFirstLetters(String designation){
    // deletes space characters at the end and at the beginning of a Sting
    designation = designation.trim().toLowerCase();
    while (designation.contains("  ")) {
        // deletes multiple space characters
        designation = designation.replace("  ", " ");
    }
    String[] split = designation.split(" ");
    for (int i = 0; i < split.length; i++) {
        split[i] = Character.toUpperCase(split[i].charAt(0)) + split[i].substring(1);
    }
    designation = String.join(" ", split);
    return designation;
}


}