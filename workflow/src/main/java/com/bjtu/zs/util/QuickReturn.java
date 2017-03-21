package com.bjtu.zs.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类用于将数据封装成一定的格式返回给前台
 * 
 * @author zengshuang
 *
 */
public class QuickReturn {

	/**
	 * 返回一个对象
	 * 
	 * @param o
	 * @return
	 */
	public static Map<String, Object> mapOk(Object o) {
		Map<String, Object> map = new HashMap<>();

		map.put("size", 1);
		map.put("data", o);
		map.put("success", true);

		return map;
	}

	/**
	 * 返回一个字符串
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> mapOk(String str) {
		Map<String, Object> map = new HashMap<>();

		map.put("size", 0);
		map.put("data", str);
		map.put("success", true);

		return map;
	}

	/**
	 * 返回一个List
	 * 
	 * @param list
	 * @return
	 */
	public static Map<String, Object> mapOk(List<?> list) {
		Map<String, Object> map = new HashMap<>();

		map.put("size", list.size());
		map.put("data", list);
		map.put("success", true);

		return map;
	}

	/**
	 * 返回报错信息
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> mapError(String str) {
		Map<String, Object> map = new HashMap<>();

		map.put("size", 0);
		map.put("data", str);
		map.put("success", false);

		return map;
	}
	/**
	 * 返回两个列表
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static Map<String,Object> mapOk(List<?> list1,List<?> list2){
		Map<String,Object> map =new HashMap<>();
		
		map.put("size1", list1.size());
		map.put("size2", list2.size());
		map.put("sizeAll", list1.size()+list2.size());
		map.put("data1", list1);
		map.put("data2", list2);
		map.put("success", true);
		
		return null;
	}
}
