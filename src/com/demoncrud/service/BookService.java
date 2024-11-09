package com.demoncrud.service;

import java.util.List;

import com.demoncrud.dao.impl.BookDaoImpl;
import com.demoncrud.model.Book;

public class BookService {
	private BookDaoImpl bookDao;
	
	public BookService() {
		this.bookDao = new BookDaoImpl();
	}
	
	public void update(Book book) {
		bookDao.update(book,"id");
	}
	public List<Book> getAllBook(){
		return bookDao.selectAll();
	}
    public void saveBook(Book book) {
    	this.bookDao.insert(book);
    }
    public Book getBookById(int bookId) {
    	System.out.println("Book Id service=="+bookId);
    	return this.bookDao.selectById(new Book(bookId));
    }
    public boolean delete(int id) {
    	Book book=new Book(id);
        book=this.bookDao.selectById(book);
        System.out.println("book="+book);
        if(book!=null) {
        	this.bookDao.delete(book);
        	return true;
        }else {
        	return false;
        }
    	
    }

}
