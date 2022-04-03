package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class ClubWarpper extends BaseControllerWrapper{

public ClubWarpper(Map<String, Object> single) {
    super(single);
}public ClubWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public ClubWarpper(Page<Map<String, Object>> page) {
    super(page);
}public ClubWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    map.put("club_category", ConstantFactory.me().getClub_category((Integer) map.get("club_category")));
}


}