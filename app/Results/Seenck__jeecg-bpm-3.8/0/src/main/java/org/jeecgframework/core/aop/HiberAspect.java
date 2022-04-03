package org.jeecgframework.core.aop;
 import org.apache.commons.lang.StringUtil;
import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.jeecgframework.core.constant.DataBaseConstant;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
@Component
public class HiberAspect extends EmptyInterceptor{

 private  Logger logger;

 private  long serialVersionUID;


public boolean onFlushDirty(Object entity,Serializable id,Object[] currentState,Object[] previousState,String[] propertyNames,Type[] types){
    TSUser currentUser = null;
    try {
        currentUser = ResourceUtil.getSessionUser();
    } catch (RuntimeException e1) {
    // logger.warn("当前session为空,无法获取用户");
    }
    if (currentUser == null) {
        return true;
    }
    // 添加数据
    for (int index = 0; index < propertyNames.length; index++) {
        /*找到名为"修改时间"的属性*/
        if (DataBaseConstant.UPDATE_DATE.equals(propertyNames[index]) || DataBaseConstant.UPDATE_TIME.equals(propertyNames[index])) {
            /*使用拦截器将对象的"修改时间"属性赋上值*/
            currentState[index] = new Date();
            continue;
        } else /*找到名为"修改人"的属性*/
        if (DataBaseConstant.UPDATE_BY.equals(propertyNames[index])) {
            /*使用拦截器将对象的"修改人"属性赋上值*/
            currentState[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE);
            continue;
        } else /*找到名为"修改人名称"的属性*/
        if (DataBaseConstant.UPDATE_NAME.equals(propertyNames[index])) {
            /*使用拦截器将对象的"修改人名称"属性赋上值*/
            currentState[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME);
            continue;
        }
    }
    return true;
}


public boolean onSave(Object entity,Serializable id,Object[] state,String[] propertyNames,Type[] types){
    TSUser currentUser = null;
    try {
        currentUser = ResourceUtil.getSessionUser();
    } catch (RuntimeException e) {
    // logger.warn("当前session为空,无法获取用户");
    }
    if (currentUser == null) {
        return true;
    }
    try {
        // 添加数据
        for (int index = 0; index < propertyNames.length; index++) {
            /*找到名为"创建时间"的属性*/
            if (DataBaseConstant.CREATE_DATE.equals(propertyNames[index]) || DataBaseConstant.CREATE_TIME.equals(propertyNames[index])) {
                /*使用拦截器将对象的"创建时间"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    state[index] = new Date();
                }
                continue;
            } else /*找到名为"创建人"的属性*/
            if (DataBaseConstant.CREATE_BY.equals(propertyNames[index])) {
                /*使用拦截器将对象的"创建人"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    state[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE);
                }
                continue;
            } else /*找到名为"创建人名称"的属性*/
            if (DataBaseConstant.CREATE_NAME.equals(propertyNames[index])) {
                /*使用拦截器将对象的"创建人名称"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    state[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME);
                }
                continue;
            } else /*找到名为"创建人名称"的属性*/
            if (DataBaseConstant.SYS_USER_CODE.equals(propertyNames[index])) {
                /*使用拦截器将对象的"创建人名称"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    state[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE);
                }
                continue;
            } else /*找到名为"创建人部门"的属性*/
            if (DataBaseConstant.SYS_ORG_CODE.equals(propertyNames[index])) {
                /*使用拦截器将对象的"创建人部门"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    state[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_ORG_CODE);
                }
                continue;
            } else /*找到名为"创建人部门"的属性*/
            if (DataBaseConstant.SYS_COMPANY_CODE.equals(propertyNames[index])) {
                /*使用拦截器将对象的"创建人部门"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    state[index] = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_COMPANY_CODE);
                }
                continue;
            } else /*找到名为"流程状态"的属性*/
            if (DataBaseConstant.BPM_STATUS.equals(propertyNames[index])) {
                /*使用拦截器将对象的"流程状态"属性赋上值*/
                if (StringUtil.isEmpty(state[index])) {
                    // 1：未提交
                    state[index] = String.valueOf(1);
                }
                continue;
            }
        }
    } catch (RuntimeException e) {
        e.printStackTrace();
    }
    return true;
}


}