package com.bjtu.zs.service;

/**
 * @ClassName ProcessService
 * @description 流程服务接口
 * @author zengshuang
 * @date 2017年3月2日15:39:03
 */
public interface ProcessService {

	/**
	 * 通过流程定义的id启动流程
	 * 
	 * @param id:流程定义中的id
	 */
	public void startProcessById(String id);


	public void getTodo();

}
