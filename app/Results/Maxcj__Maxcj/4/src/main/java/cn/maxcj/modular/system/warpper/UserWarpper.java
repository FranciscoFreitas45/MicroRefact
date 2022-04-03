package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class UserWarpper extends BaseControllerWrapper{

public UserWarpper(Map<String, Object> single) {
    super(single);
}public UserWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public UserWarpper(Page<Map<String, Object>> page) {
    super(page);
}public UserWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
    map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
    map.put("deptName", ConstantFactory.me().getDeptName((Integer) map.get("deptid")));
    map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    map.put("academy", ConstantFactory.me().getUserAcademy((Integer) map.get("academy")));
}


}