package run.halo.app.model.vo;
 import java.util.Comparator;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@ToString
@EqualsAndHashCode
public class ArchiveYearVO {

 private  Integer year;

 private  List<PostListVO> posts;


@Override
public int compare(ArchiveYearVO left,ArchiveYearVO right){
    return right.getYear() - left.getYear();
}


}