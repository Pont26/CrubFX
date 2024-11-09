package com.demoncrud.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demoncrud.model.Book;



public class BookDaoImpl extends GeneralDaoImpl<Book> {
	public BookDaoImpl() {
		super(Book.class);	
	}

	@Override
	public Book convertToObject(ResultSet rs) {
		    int id;
			try {
				id = rs.getInt("id");
				 String title = rs.getString("title");
			     String author = rs.getString("author");
			     int year = rs.getInt("year");
			     int pages = rs.getInt("pages");
			     Book book = new Book(id, title, author, year,pages);
			     return book;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}			
	}





}
