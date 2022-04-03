package cn.maxcj.modular.system.warpper;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class NoticeWrapper extends BaseControllerWrapper{

public NoticeWrapper(Map<String, Object> single) {
    super(single);
}public NoticeWrapper(List<Map<String, Object>> multi) {
    super(multi);
}public NoticeWrapper(Page<Map<String, Object>> page) {
    super(page);
}public NoticeWrapper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    Integer creater = (Integer) map.get("creater");
    map.put("createrName", ConstantFactory.me().getUserNameById(creater));
}


}