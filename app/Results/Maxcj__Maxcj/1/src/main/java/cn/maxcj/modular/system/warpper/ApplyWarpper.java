package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class ApplyWarpper extends BaseControllerWrapper{

public ApplyWarpper(Map<String, Object> single) {
    super(single);
}public ApplyWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public ApplyWarpper(Page<Map<String, Object>> page) {
    super(page);
}public ApplyWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    map.put("userid", ConstantFactory.me().getUserNameById((Integer) map.get("userid")));
    map.put("deptid", ConstantFactory.me().getDeptName((Integer) map.get("deptid")));
}


}