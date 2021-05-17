import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
public class ExtjsPageRequest {

 private  int page;

 private  int limit;

 private  String sort;

 private  String dir;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public Pageable getPageable(){
    Pageable pageable = null;
    if (!sort.trim().equals("") && !dir.trim().equals("")) {
        Sort pageSort = new Sort(Direction.DESC, sort);
        if (!dir.equals("DESC")) {
            pageSort = new Sort(Direction.ASC, sort);
        }
        pageable = PageRequest.of(page - 1, limit, pageSort);
    } else {
        pageable = PageRequest.of(page - 1, limit);
    }
    return pageable;
}


public void setSort(String sort){
    this.sort = sort;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSort"));

.queryParam("sort",sort);
restTemplate.put(builder.toUriString(),null);
}


}