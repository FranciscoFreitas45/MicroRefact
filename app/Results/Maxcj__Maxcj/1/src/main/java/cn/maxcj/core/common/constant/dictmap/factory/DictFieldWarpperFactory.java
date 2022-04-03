package cn.maxcj.core.common.constant.dictmap.factory;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.constant.factory.IConstantFactory;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import java.lang.reflect.Method;
public class DictFieldWarpperFactory {


public Object createFieldWarpper(Object parameter,String methodName){
    IConstantFactory constantFactory = ConstantFactory.me();
    try {
        Method method = IConstantFactory.class.getMethod(methodName, parameter.getClass());
        return method.invoke(constantFactory, parameter);
    } catch (Exception e) {
        try {
            Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
            return method.invoke(constantFactory, Integer.parseInt(parameter.toString()));
        } catch (Exception e1) {
            throw new ServiceException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
        }
    }
}


}