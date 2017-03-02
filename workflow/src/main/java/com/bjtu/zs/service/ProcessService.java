package com.bjtu.zs.service;

/**
 * @ClassName ProcessService
 * @Description 流程服务接口
 * @author 曾双  631710518@qq.com
 * @Date 2017年3月2日15:39:03
 */
public interface ProcessService {

	/**
	 * 通过流程定义的id启动流程
	 * 
	 * @param id:流程定义中的id
	 */
	public void startProcessById(String id);


	/**
	 * 
	 */
	public void getTodo();

}
