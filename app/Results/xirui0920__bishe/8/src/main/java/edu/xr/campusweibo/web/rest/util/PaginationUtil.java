package edu.xr.campusweibo.web.rest.util;
 import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URISyntaxException;
public class PaginationUtil {

private PaginationUtil() {
}
public HttpHeaders generatePaginationHttpHeaders(Page page,String baseUrl){
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Total-Count", "" + Long.toString(page.getTotalElements()));
    String link = "";
    if ((page.getNumber() + 1) < page.getTotalPages()) {
        link = "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
    }
    // prev link
    if ((page.getNumber()) > 0) {
        link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
    }
    // last and first link
    int lastPage = 0;
    if (page.getTotalPages() > 0) {
        lastPage = page.getTotalPages() - 1;
    }
    link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
    link += "<" + generateUri(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
    headers.add(HttpHeaders.LINK, link);
    return headers;
}


public String generateUri(String baseUrl,int page,int size){
    return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
}


}