package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.models.auth.In;
import java.applet.AppletContext;
import java.util.List;
import java.util.Map;
public class JoinWarpper extends BaseControllerWrapper{

public JoinWarpper(Map<String, Object> single) {
    super(single);
}public JoinWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public JoinWarpper(Page<Map<String, Object>> page) {
    super(page);
}public JoinWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    map.put("activityId", ConstantFactory.me().getActivityName((Integer) map.get("activity_id")));
    map.put("userid", ConstantFactory.me().getUserNameById((Integer) map.get("userid")));
    map.put("joinState", ConstantFactory.me().getDictsByName("报名状态", (Integer) map.get("join_state")));
}


}