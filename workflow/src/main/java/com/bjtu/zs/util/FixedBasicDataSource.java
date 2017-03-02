package com.bjtu.zs.util;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

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
	        DriverManager.deregisterDriver(DriverManager.getDriver(url)); 
	        super.close(); 
	    }  
}
