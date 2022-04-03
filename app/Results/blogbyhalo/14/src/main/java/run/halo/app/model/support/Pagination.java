package run.halo.app.model.support;
 import java.util.List;
import lombok.Data;
@Data
public class Pagination {

 private  List<RainbowPage> rainbowPages;

 private  String nextPageFullPath;

 private  String prevPageFullPath;

 private  Boolean hasPrev;

 private  Boolean hasNext;


}