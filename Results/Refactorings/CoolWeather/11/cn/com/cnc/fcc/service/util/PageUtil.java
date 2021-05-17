import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class PageUtil {


public Page<T> listToPage(List<T> list,Pageable pageable){
    if (list == null) {
        list = new ArrayList<T>();
    }
    int start = (int) pageable.getOffset();
    int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
    if (start > end) {
        return new PageImpl<>(new ArrayList<T>(), pageable, list.size());
    } else {
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}


}