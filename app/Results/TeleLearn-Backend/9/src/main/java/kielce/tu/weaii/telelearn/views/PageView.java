package kielce.tu.weaii.telelearn.views;
 import lombok.Value;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Value
public class PageView {

 private int currentPage;

 private long totalItems;

 private List<T> content;


public PageView<R> of(Page<T> page,Function<T,R> ofView){
    return new PageView<>(page.getNumber(), page.getTotalElements(), page.getContent().stream().map(ofView).collect(Collectors.toList()));
}


}