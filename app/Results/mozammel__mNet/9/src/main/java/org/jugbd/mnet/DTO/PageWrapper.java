package org.jugbd.mnet.DTO;
 import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
public class PageWrapper {

 public  int MAX_PAGE_ITEM_DISPLAY;

 private  Page<T> page;

 private  List<PageItem> items;

 private  int currentNumber;

 private  String url;

 private  int number;

 private  boolean current;

public PageWrapper(Page<T> page, String url) {
    this.page = page;
    this.url = url;
    items = new ArrayList<>();
    currentNumber = page.getNumber();
    int start, size;
    if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
        start = 0;
        size = page.getTotalPages();
    } else {
        if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
            start = 0;
            size = MAX_PAGE_ITEM_DISPLAY;
        } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
            start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
            size = MAX_PAGE_ITEM_DISPLAY;
        } else {
            start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
            size = MAX_PAGE_ITEM_DISPLAY;
        }
    }
    for (int i = 0; i < size; i++) {
        items.add(new PageItem(start + i, (start + i) == currentNumber));
    }
}
public int getNumber(){
    return this.number;
}


public String getUrl(){
    return url;
}


public List<PageItem> getItems(){
    return items;
}


public int getSize(){
    return page.getSize();
}


public List<T> getContent(){
    return page.getContent();
}


public int getTotalPages(){
    return page.getTotalPages();
}


}