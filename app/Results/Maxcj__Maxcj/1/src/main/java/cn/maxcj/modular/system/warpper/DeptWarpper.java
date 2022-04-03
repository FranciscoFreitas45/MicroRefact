package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class DeptWarpper extends BaseControllerWrapper{

public DeptWarpper(Map<String, Object> single) {
    super(single);
}public DeptWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public DeptWarpper(Page<Map<String, Object>> page) {
    super(page);
}public DeptWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    Integer pid = (Integer) map.get("pid");
    if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
        map.put("pName", "--");
    } else {
        map.put("pName", ConstantFactory.me().getDeptName(pid));
    }
}


}