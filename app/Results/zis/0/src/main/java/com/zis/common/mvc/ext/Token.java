package com.zis.common.mvc.ext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * token 注解
 * 使用方法:进入提交页面的上一个controller，创建token = true。
 * 并在对应进入的页面获取TOKEN，提交过程中带入TOKEN
 * 在save的controller 上检查token = true 
 * @author think
 *
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Token {
	
    /**
     * 创建token
     * @return
     */
    boolean generate() default false;
    
    /**
     * 检查token
     * @return
     */
    boolean checking() default false;  
}  