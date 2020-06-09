package com.ovopark.sdk.openplatform.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
    * @ClassName: IgnoreSign
    * @Description: TODO(实体类字段配置此注解后，签名 时，忽略该列值)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date  2017年12月4日 下午2:08:40 
    *
 */
@Retention(RetentionPolicy.RUNTIME) //注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
//如果一个注解没有指定@Target注解，则此注解可以用于除了类型参数和类型使用以外的任何地方
@Target({java.lang.annotation.ElementType.FIELD}) //可用于成员变量、枚举常量
public @interface IgnoreSign {
	
}
