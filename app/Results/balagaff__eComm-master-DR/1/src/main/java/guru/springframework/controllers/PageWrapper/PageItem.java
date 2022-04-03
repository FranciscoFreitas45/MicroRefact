package guru.springframework.controllers.PageWrapper;
 import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
public class PageItem {

 private  int number;

 private  boolean current;

public PageItem(int number, boolean current) {
    this.number = number;
    this.current = current;
}
public int getNumber(){
    return this.number;
}


public boolean isCurrent(){
    return this.current;
}


}