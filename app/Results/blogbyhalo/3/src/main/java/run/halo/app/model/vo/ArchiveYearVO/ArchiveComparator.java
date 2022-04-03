package run.halo.app.model.vo.ArchiveYearVO;
 import java.util.Comparator;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
public class ArchiveComparator implements Comparator<ArchiveYearVO>{


@Override
public int compare(ArchiveYearVO left,ArchiveYearVO right){
    return right.getYear() - left.getYear();
}


}