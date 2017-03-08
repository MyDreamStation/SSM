package com.bjtu.zs.util;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * @ClassName FixedBasicDataSource
 * @Description 解决DBCP数据库连接内存泄漏，导致无法关闭tomcat的问题
 * @author 曾双 631710518@qq.com
 * @Date 2017年3月2日16:59:06
 *
 */
public class FixedBasicDataSource extends BasicDataSource {

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void close() throws SQLException {
//		DriverManager.deregisterDriver(DriverManager.getDriver(url));
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()){
			DriverManager.deregisterDriver(drivers.nextElement());
		}
		super.close();
	}
}
