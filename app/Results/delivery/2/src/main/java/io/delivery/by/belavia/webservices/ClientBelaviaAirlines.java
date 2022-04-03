package io.delivery.by.belavia.webservices;
 import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ClientBelaviaAirlines {

 private  String ADDRESS;


public XMLGregorianCalendar validateAndSetDate(String date){
    XMLGregorianCalendar validatedDate = new XMLGregorianCalendarImpl();
    // Получаем текущую дату
    LocalDate localDate = LocalDate.now();
    // Текущий год
    int currentYear = localDate.getYear();
    // Предыдущий год
    int previousYear = currentYear - 1;
    // Следующий год
    int nextYear = currentYear + 1;
    // Текущий месяц
    int currentMonth = localDate.getMonthValue();
    // Предыдущий месяц
    int previousMonth = currentMonth - 1;
    // Следующий месяц
    int nextMonth = currentMonth + 1;
    // Текущий день
    int currentDay = localDate.getDayOfMonth();
    // Количество дней в текущем месяце
    int daysInCurrentMonth = localDate.lengthOfMonth();
    if (// Если сейчас декабрь - следующий месяц январь
    currentMonth == 12)
        nextMonth = 1;
    if (// Если сейчас январь - предыдущий месяц декабрь
    currentMonth == 1)
        previousMonth = 12;
    LocalDate previous = LocalDate.of(currentYear, previousMonth, 1);
    int daysInPreviousMonth = previous.lengthOfMonth();
    // Переданная в метод дата по параметру запроса
    // делим на массив по любой пунктуации
    String[] splittedDate = date.split("[\\p{Punct}]");
    if (// Если дата не содержит год, месяц и день
    splittedDate.length < 3)
        throw new IllegalArgumentException("Wrong date parameter - date must have " + "\"YYYY-MM-DD\" format. Date separator can be eny type");
    // Запрашиваемый год
    int askedYear = Integer.parseInt(splittedDate[0]);
    // Запрашиваемый месяц
    int askedMonth = Integer.parseInt(splittedDate[1]);
    // Запрашивемый день
    int askedDay = Integer.parseInt(splittedDate[2]);
    if (// Если запрашиваемый день больше количества дней в этом месяце
    askedDay > daysInCurrentMonth)
        throw new IllegalArgumentException("Wrong day parameter - there is no " + askedDay + " day in this month.");
    if (// Если запрашиваемый день больше количества дней в прошлом месяце
    askedDay > daysInPreviousMonth)
        throw new IllegalArgumentException("Wrong day parameter - there is no " + askedDay + " day in previous month.");
    if (currentYear != askedYear) {
        // Если текущий год не равен запрашивемому
        if (askedYear == nextYear) {
            // Если запрашиваемый год равен следующему году
            if ((currentMonth == 12) && (currentDay >= 30)) {
                // Если сейчас Декабрь и число >= 30
                if ((askedMonth == 1) && (askedDay <= 2)) {
                    // Если запрашиваемый месяц Январь и число <= 2
                    // Устанавливаем валидный год
                    validatedDate.setYear(askedYear);
                    // Устанавливаем валидный месяц
                    validatedDate.setMonth(askedMonth);
                    // Устанавливаем валидный день
                    validatedDate.setDay(askedDay);
                } else
                    // Если запрашиваемый месяц не Январь или число не больше 2
                    throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
            } else
                // Если сейчас не Декабрь или число < 30
                throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
        } else if (askedYear == previousYear) {
            // Если запрашиваемый год равен предыдущему году
            if ((currentMonth == 1) && (currentDay <= 2)) {
                // Если сейчас Январь и число <= 2
                if ((askedMonth == 12) && (askedDay >= 30)) {
                    // Если запрашиваемый месяц Декабрь и число >= 30
                    // Устанавливаем валидный год
                    validatedDate.setYear(askedYear);
                    // Устанавливаем валидный месяц
                    validatedDate.setMonth(askedMonth);
                    // Устанавливаем валидный день
                    validatedDate.setDay(askedDay);
                } else
                    // Если запрашиваемый месяц не Декабрь или число меньше 30
                    throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
            } else
                // Если сейчас не Январь или число больше 2
                throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
        } else
            // Если запрашиваемый год не равен предыдущему
            throw new IllegalArgumentException("Wrong year parameter - year may be: " + "1. Previous - if current month is january and current day is first; " + "2. Next - if current month is december and current day is 30-th.");
    } else if (currentMonth != askedMonth) {
        // Если текущий месяц не равен запрашиваемому
        if (askedMonth == nextMonth) {
            // Если запрашиваемый месяц равен следующему
            if (currentDay >= daysInCurrentMonth - 1) {
                // Если текущее число за два дня до конца месяца
                if (askedDay <= 2) {
                    // Если запрашиваемое число не больше 2
                    // Устанавливаем валидный год
                    validatedDate.setYear(currentYear);
                    // Устанавливаем валидный месяц
                    validatedDate.setMonth(askedMonth);
                    // Устанавливаем валидный день
                    validatedDate.setDay(askedDay);
                } else
                    // Если запрашиваемое число больше 2
                    throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
            } else
                // Если текущее число меньшее чем число за два дня до конца месяца
                throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
        } else if (askedMonth == previousMonth) {
            // Если запрашиваемый месяц равен предыдущему
            if (currentDay <= 2) {
                // Если текущее число не болше 2
                if (askedDay >= daysInCurrentMonth - 1) {
                    // Если запрашиваемое число >= числу за 2 дня до конца предыдущего месяца
                    // Устанавливаем валидный год
                    validatedDate.setYear(currentYear);
                    // Устанавливаем валидный месяц
                    validatedDate.setMonth(askedMonth);
                    // Устанавливаем валидный день
                    validatedDate.setDay(askedDay);
                } else
                    // Если запрашиваемое число меньшее чем число за 2 дня до конца предыдущего месяца
                    throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
            } else
                // Если текущее число больше 2
                throw new IllegalArgumentException("Wrong date parameter! Please visit " + "description page below");
        } else
            // Если запрашиваемый месяц не равен предыдущему
            throw new IllegalArgumentException("Wrong month parameter - month may be: " + "1. Previous - if current day is first or second; " + "2. Next - if current day is the day before the last day of current month.");
    } else if (Math.abs(currentDay - askedDay) > 2) {
        // Если разница между текущим числом и запрашиваемым составляет более +- 2 дня
        throw new IllegalArgumentException("Wrong day parameter - day must be " + "between +/-2 days of current date");
    } else {
        // Во всех остальных случаях устанавливаем валидную дату
        validatedDate.setYear(askedYear);
        validatedDate.setMonth(askedMonth);
        validatedDate.setDay(askedDay);
    }
    return validatedDate;
}


public List<Airport> getListOfAirports(String language){
    if (!(languageVerification(language)))
        throw new IllegalArgumentException("Wrong language parameter - must be RU or EN");
    URL url = new URL(ADDRESS);
    QName qName = new QName("http://webservices.belavia.by/", "OnlineTimeTable");
    Service service = Service.create(url, qName);
    OnlineTimeTableSoap hello = service.getPort(OnlineTimeTableSoap.class);
    AirportsResponse response = hello.getAirportsList(language);
    List<Airport> airportList = response.getAirport();
    List<String> airportIataAndName = new ArrayList<>();
    for (Airport a : airportList) {
        airportIataAndName.add(a.getIATA() + " - " + a.getName());
    }
    return airportList;
}


public boolean languageVerification(String language){
    List<String> verifiedLanguages = new ArrayList<>();
    verifiedLanguages.add("ru");
    verifiedLanguages.add("en");
    String testLanguage = language.toLowerCase();
    return verifiedLanguages.contains(testLanguage);
}


public List<Flight> getListOfFlights(String airport,String timeTableType,String date){
    URL url = new URL(ADDRESS);
    QName qName = new QName("http://webservices.belavia.by/", "OnlineTimeTable");
    Service service = Service.create(url, qName);
    OnlineTimeTableSoap timeTableSoap = service.getPort(OnlineTimeTableSoap.class);
    TimeTableType type = setTimeTabelType(timeTableType);
    XMLGregorianCalendar validatedDate = validateAndSetDate(date);
    TimeTableResponce response = timeTableSoap.getTimeTable(airport.toUpperCase(), type, validatedDate);
    ArrayOfFlight flights = response.getFlights();
    return flights.getFlight();
}


public TimeTableType setTimeTabelType(String timeTableType){
    TimeTableType type;
    if (timeTableType.equals("Arrival")) {
        type = TimeTableType.ARRIVAL;
    } else if (timeTableType.equals("Departure")) {
        type = TimeTableType.DEPARTURE;
    } else
        throw new IllegalArgumentException("Wrong type parameter - must be Arrival or Departure");
    return type;
}


}