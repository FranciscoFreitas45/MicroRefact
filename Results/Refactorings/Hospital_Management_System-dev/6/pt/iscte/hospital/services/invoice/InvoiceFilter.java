import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
@Component
@Getter
@Setter
@ToString
public class InvoiceFilter {

 private  Long nif;

 private  String status;

 private  LocalDate issuedBefore;

 private  LocalDate issuedAfter;

 private  LocalDate paidBefore;

 private  LocalDate paidAfter;

 private  LocalDate dueBefore;

 private  LocalDate dueAfter;

 private  String search;


public String filtros(){
    String filter = "?";
    if (nif != null) {
        filter += "nif=" + nif + "&";
    }
    if (status != null) {
        filter += "status=" + status;
    }
    if (issuedBefore != null) {
        filter += "issuedBefore=" + issuedBefore.toString() + "&";
    }
    if (issuedAfter != null) {
        filter += "issuedAfter=" + issuedAfter.toString() + "&";
    }
    if (paidBefore != null) {
        filter += "paidBefore=" + paidBefore.toString() + "&";
    }
    if (paidAfter != null) {
        filter += "paidAfter=" + paidAfter.toString() + "&";
    }
    if (dueBefore != null) {
        filter += "dueBefore=" + dueBefore.toString() + "&";
    }
    if (dueAfter != null) {
        filter += "dueAfter=" + dueAfter.toString() + "&";
    }
    if (search != null) {
        filter += "search=" + search;
    }
    return filter;
}


}