package com.demoncrud.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demoncrud.model.Books;

import application.MainController1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Util;



public abstract class GeneralDao<T> {
public ConnectionDao connectionDao;
public MainController1 mainController;
private Class<T> classType;
	
	public GeneralDao(Class<T> classType) {
		this.classType = classType;
		this.connectionDao = new ConnectionDao();
		this.mainController = new MainController1();
	}
	
	public abstract T  convertToObject(ResultSet rs); 
    
	//insert

	  public void insert(T object) {
		 String query = generateInsert(object);
	     executeUpdate("insert",object, query,"id");
	    }
	  

	public void update(T object, Object... whereConditions){
		String query = generateUpdate(object,whereConditions);
	    executeUpdate("update", object, query, whereConditions);
	}
	
	//Delete Row

	public void deleteRow(Object object){
	    String tableName = classType.getSimpleName().toLowerCase();
	    String sql = "DELETE FROM " + tableName + " WHERE id = ?";
	    System.out.println("Constructed Query: " + sql);
	    
	    executeUpdate("DELETE", object, sql,"id");
	}
	
	
	
	//select all

	public ObservableList<T> selectAll() {
		  String tableName = classType.getSimpleName().toLowerCase();
		 String sql = "SELECT * FROM " + tableName;
		return executeQuery(sql);
		
	}
	
	
    //executeUpdate
	private void executeUpdate(String type, Object object, String query, Object... conditions) {
	    try (Connection connection = connectionDao.getConnectionWithDao();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        
	        int count = 1;

	        if (!type.equals("DELETE")) {
	            List<String> columns = Util.getField(object, false, conditions);
	            for (String column : columns) {
	                Field field = object.getClass().getDeclaredField(column);
	                field.setAccessible(true);
	                Object value = field.get(object);
	                preparedStatement.setObject(count++, value);
	               
	            }
	        }

	        
	        if(type.equals("update") || type.equals("DELETE")) {
	        List<String> whereColumns = Util.getField(object, true, conditions);
	        for (String whereColumn : whereColumns) {
	            Field field = object.getClass().getDeclaredField(whereColumn);
	            field.setAccessible(true);
	            Object value = field.get(object);
	            preparedStatement.setObject(count++, value);
	           
	        }
	        }

	        int rowsAffected = preparedStatement.executeUpdate();
	        System.out.println("Rows affected: " + rowsAffected);
	        
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.err.println("IO Error: " + e.getMessage());
	        e.printStackTrace();
	    } catch (NoSuchFieldException | IllegalAccessException e) {
	        System.err.println("Reflection Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	
	//executeQuery
	private ObservableList<T> executeQuery(String sql, Object... parameters) {
		ObservableList<T> list = FXCollections.observableArrayList();
		try (Connection connection = connectionDao.getConnectionWithDao();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        int index = 1; 
	        for (Object parameter : parameters) {
	            preparedStatement.setObject(index++, parameter); 
	        }
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            list.add(convertToObject(rs)); 
	        }
	        rs.close(); 
	    } catch (IOException | SQLException e) {
	        e.printStackTrace();
	    }        
	    return list;
	}
	
	
	 //generate insert
	 //generate insert
	  private String generateInsert(T object) {
		  String tableName = classType.getSimpleName().toLowerCase();
	        String query = "INSERT INTO " + tableName + " (";
	        
	        List<String> columns = Util.getField(object,false, "id");
	        for (String column : columns) {
	            query += column + ", ";
	        }
	            query = query.substring(0, query.length() - 2);
	       
	        query += ") VALUES (";
	        
	        for (String column1 : columns) {
	            query += "?, ";
	        }

	            query = query.substring(0, query.length() - 2);
	        query += ")";
	        
	        System.out.println("Constructed Query: " + query);
			return query;
	  }
	  

	  
	  //generateUpdate
	  private String generateUpdate(T object,Object...whereConditions) {
			 String tableName = classType.getSimpleName().toLowerCase();
			    List<String> columns = Util.getField(object, false, whereConditions);
			    
			    String query = "UPDATE " + tableName + " SET ";
			    for (String column : columns) {
			        query += column + " = ?, ";
			    }
			    query = query.substring(0, query.length() - 2); 
			    query += " WHERE ";
			    
			    List<String> whereConditionsList = Util.getField(object, true, whereConditions);
			    for (String column : whereConditionsList) {
			        query += column + " = ? AND "; 
			    }
			      query = query.substring(0, query.length() - 4); 
			    
			    System.out.println("Constructed Query: " + query);
				return query;
		}
	 


	}
	
	
	


