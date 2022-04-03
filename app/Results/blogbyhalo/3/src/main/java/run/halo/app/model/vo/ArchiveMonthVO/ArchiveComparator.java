package run.halo.app.model.vo.ArchiveMonthVO;
 import java.util.Comparator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
public class ArchiveComparator implements Comparator<ArchiveMonthVO>{


@Override
public int compare(ArchiveMonthVO left,ArchiveMonthVO right){
    int compare = right.getYear() - left.getYear();
    if (compare != 0) {
        return compare;
    }
    return right.getMonth() - left.getMonth();
}


}