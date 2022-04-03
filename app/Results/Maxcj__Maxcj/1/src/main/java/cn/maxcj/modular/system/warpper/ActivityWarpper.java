package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class ActivityWarpper extends BaseControllerWrapper{

public ActivityWarpper(Map<String, Object> single) {
    super(single);
}public ActivityWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public ActivityWarpper(Page<Map<String, Object>> page) {
    super(page);
}public ActivityWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    map.put("activity_categoryName", ConstantFactory.me().getActivity_category((Integer) map.get("activity_category")));
    map.put("deptName", ConstantFactory.me().getDeptName((Integer) map.get("activity_club")));
    map.put("activity_createName", ConstantFactory.me().getUserNameById((Integer) map.get("activity_person")));
    map.put("activity_stateName", ConstantFactory.me().getActivity_state((Integer) map.get("activity_state")));
}


}