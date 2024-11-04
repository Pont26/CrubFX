package com.demoncrud.dao;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demoncrud.model.Books;

import application.MainController1;



public class BookDao extends GeneralDao<Books> {
	
private MainController1 mainController;
	public BookDao() {
		super(Books.class);
		mainController=new MainController1();
		
	}

	public void insertRecord() {
	    String query = "INSERT INTO books (id, title, author, year, pages) VALUES (" +
	    		mainController.getTfId().getText() + ", '" + mainController.getTfTitle().getText() + 
                "', '" + mainController.getTfAuthor().getText() + 
                "', " + mainController.getTfYear().getText() + 
                ", " + mainController.getTfPages().getText() + ")";
	    executeUpdate(query);
	    mainController.showBook();
	    
		mainController.getTfId().clear();
		mainController.getTfTitle().clear();
		mainController.getTfAuthor().clear();
	    mainController.getTfYear().clear();
	    mainController.getTfPages().clear();
	}
	
	public void updateRecord() {
	    String query = "UPDATE books SET title = '" + mainController.getTfTitle().getText() + 
	                   "', author = '" + mainController.getTfAuthor().getText() + 
	                   "', year = " + mainController.getTfYear().getText() + 
	                   ", pages = " + mainController.getTfPages().getText() + 
	                   " WHERE id = " + mainController.getTfId().getText();
	
	        executeUpdate(query);
	        mainController.showBook(); 

	        mainController.getTfId().clear();
	        mainController.getTfTitle().clear();
	        mainController.getTfAuthor().clear();
	        mainController.getTfYear().clear();
	        mainController.getTfPages().clear();
	 
	}
	
	public void deleteRecord() {
		String query = "DELETE FROM books WHERE id =  "+ mainController.getTfId().getText();
		executeUpdate(query);
		mainController.showBook();
		
	}
	
	public void executeUpdate(String query) {
		 connectionDao = new ConnectionDao();

		try {
			Connection connection = connectionDao.getConnectionWithDao();
			Statement st = connection.createStatement();
			st.executeUpdate(query);
			
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public Books convertToObject(ResultSet rs) {
		    int id;
			try {
				id = rs.getInt("id");
				 String title = rs.getString("title");
			     String author = rs.getString("author");
			     int year = rs.getInt("year");
			     int pages = rs.getInt("pages");
			     Books book = new Books(id, title, author, year,pages);
			     return book;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	      
	        
	       
				
	}

}
