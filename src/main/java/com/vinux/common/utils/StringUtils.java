package com.vinux.common.utils;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * String工具
 * @author
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	 * 一次性判断多个或单个对象为空。
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object...objects){
		Boolean result = Boolean.FALSE ;
		for (Object object : objects) {
			if(object == null || "".equals(object.toString().trim()) 
					|| "null".equals(object.toString().trim())
					|| "[null]".equals(object.toString().trim())
					|| "[]".equals(object.toString().trim())
					|| "{}".equals(object.toString().trim())
					
				){
				result = Boolean.TRUE ; 
				break ; 
			}
		}
		return result ; 
	}
	/**
	 * 一次性判断多个或单个对象不为空。
	 * @param objects
     * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object...objects){
		return !isBlank(objects);
	}
	public static boolean isBlank(String...objects){
		Object[] object = objects ;
		return isBlank(object);
	}
	public static boolean isNotBlank(String...objects){
		Object[] object = objects ;
		return !isBlank(object);
	}
	public static boolean isBlank(String str){
		Object object = str ;
		return isBlank(object);
	}
	public static boolean isNotBlank(String str){
		Object object = str ;
		return !isBlank(object);
	}

	public static String checkNullToConvert(Object obj) {
		return StringUtils.isBlank(obj) ? "" : obj.toString();
	}
	/**
	 * 判断一个字符串在数组中存在几个
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr,String[] strings){
		if(null == baseStr || baseStr.length() == 0 || null == strings)
			return 0;
		
		int i = 0;
		for (String string : strings) {
			boolean result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i ;
	}
	
	public static String trimToEmpty(Object str){
	  return (isBlank(str) ? "" : str.toString().trim());
	}
	
    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object,? extends Object> map){
    	String result = "" ;
    	if(map == null || map.size() ==0){
    		return result ;
    	}
    	Set<? extends Object> keys = map.keySet();
    	for (Object key : keys ) {
    		result += ((String)key + "=" + (String)map.get(key) + "&");
		}
    	
    	return isBlank(result) ? result : result.substring(0,result.length() - 1);
    }
    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     * @param args
     * @return
     */
    public static Map<String, String> getToMap(String args){
    	if(isBlank(args)){
    		return new HashMap<String, String>() ;
    	}
    	args = args.trim();
    	//如果是?开头,把?去掉
    	if(args.startsWith("?")){
    		args = args.substring(1,args.length());
    	}
    	String[] argsArray = args.split("&");
    	Map<String,String> result = new HashMap<String,String>();
    	for (String ag : argsArray) {
			if(!isBlank(ag) && ag.indexOf("=")>0){
				String[] keyValue = ag.split("=");
				//如果value或者key值里包含 "="号,以第一个"="号为主 ,如  name=0=3  转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.
				String key = keyValue[0];
				String value = "" ;
				for (int i = 1; i < keyValue.length; i++) {
					value += keyValue[i]  + "=";
				}
				value = value.length() > 0 ? value.substring(0,value.length()-1) : value ;
				result.put(key,value);
			}
		}
    	return result ;
    }
    
    /**
	 * 转换成Unicode
	 * @param str
	 * @return
	 */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
            s1 = s1 + "\\u" + as[i];
        }
        return s1;
     }
    
    public static String getDoubleTOString(Double str){
		String money = str.toString();
		try {   
			Double.parseDouble(money);
		} catch (Exception e) {
			BigDecimal bDecimal = new BigDecimal(str);
			money = bDecimal.toPlainString();
		} 
		return money;
    	
    }
    
    /**
     * 把数组转换成Set 方便判断
     * @param objs
     * @return
     */
    public static TreeSet<String> arrayToSet(String[] objs){
    	TreeSet<String> result = new TreeSet<String>();
    	if(null ==objs){
    		return result ;
    	}
    	for (String obj:objs) {
    		result.add(obj);
    	}
    	return result;
    }
    
    /**
     * 字符串转urlcode
     * @param value
     * @return
     */
    public static String strToUrlcode(String value){
    	try {
			value = java.net.URLEncoder.encode(value,"utf-8");
			return value ;
		} catch (UnsupportedEncodingException e) {
			return null;
		}    
    }
    /**
     * urlcode转字符串
     * @param value
     * @return
     */
    public static String urlcodeToStr(String value){
    	try {
			value = java.net.URLDecoder.decode(value,"utf-8");
			return value ;
		} catch (UnsupportedEncodingException e) {
			return null;
		}  
    }
    
    /**
	 * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
	 * @param args
	 * @return
	 */
	public static JSONObject isJSONObject(String args) {
		JSONObject result = null ;
		
		if(isBlank(args)){
			return result ;
		}
		try {
			return JSONObject.parseObject(args);
		} catch (Exception e) {
			return result ;
		}
	}
	/**
	 * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
	 * @param args
	 * @return
	 */
	public static JSONArray isJSONArray(String args) {
		JSONArray result = null ;
		if(isBlank(args)){
			return result ;
		}
		try {
			return JSONArray.parseArray(args);
		} catch (Exception e) {
			return result ;
		}
	}
	
	public static String getBASE64(String args) {
		if(isNotBlank(args))
			return Base64.getEncoder().encodeToString(args.getBytes());
		return args;
	}
	
	public static String getStrByBASE64(String args) {
		if(isNotBlank(args))
			return new String(Base64.getDecoder().decode(args.getBytes()));
		return args;
	}
}
