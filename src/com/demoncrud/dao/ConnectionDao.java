package com.demoncrud.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDao {
	public Connection getConnectionWithDao() throws FileNotFoundException  {
		InputStream stream = getClass().getClassLoader().getResourceAsStream("db.properties");
		if(stream==null) {
			throw new FileNotFoundException("File db.properties not found");
		}
		
		Properties prop=new Properties();
		try {
			prop.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = prop.getProperty("db.url");
		String user = prop.getProperty("db.user");
		String password = prop.getProperty("db.password");
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(url,user,password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}

}
