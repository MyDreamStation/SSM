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

		map.put("size", 1);
		map.put("data", str);
		map.put("success", true);

		return map;
	}

	/**
	 * 返回一个数组
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

		map.put("size", 1);
		map.put("data", str);
		map.put("success", false);

		return map;
	}

}
