package com.ovopark.sdk.openplatform.kit;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.ovopark.sdk.openplatform.annotation.IgnoreSign;


public class PoJoKit {
	
	/**
	    * @Title: getNeedFields
	    * @Description: TODO(获取新增的ORM字段)
	    * @param @param clazz
	    * @param @return    参数
	    * @return Field[]    返回类型
	    * @throws
	 */
	public static Field[] getNeedFields(Class<?> clazz) {
	    Map<String,Object> list = new HashMap<String,Object>();
	    while ((null != clazz) && (clazz != Object.class)) {
	      Field[] fs = clazz.getDeclaredFields();
	      for (int i = 0; i < fs.length; i++) {
	        if (isIgnoredField(fs[i]))
	          continue;
	        if (list.containsKey(fs[i].getName()))
	          continue;
	        list.put(fs[i].getName(), fs[i]);
	      }
	      clazz = clazz.getSuperclass();
	    }
	    return (Field[])list.values().toArray(new Field[list.size()]);
	}
	/**
	    * @Title: isIgnoredField
	    * @Description: TODO(忽略的字段)
	    * @param @param f
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean isIgnoredField(Field f) {
	    if (Modifier.isStatic(f.getModifiers()))
	      return true;
	    if (Modifier.isFinal(f.getModifiers())) 
	      return true;
	    //是否忽略签名字段
	    IgnoreSign ignore = (IgnoreSign)f.getAnnotation(IgnoreSign.class);
	    if(ignore!=null)
	      return true;
		return false;
	  }
}
