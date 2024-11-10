package com.demoncrud.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.demoncrud.model.Book;
import com.demoncrud.model.RentBook;
import com.demoncrud.model.RentBook.Status;
import com.demoncrud.model.Student;

public class RentBookDaoImpl extends GeneralDaoImpl<RentBook> {
    private StudentDaoImpl studentDao;
    private BookDaoImpl bookDao;
	public RentBookDaoImpl() {
		super(RentBook.class);
		this.studentDao=new StudentDaoImpl();
		this.bookDao=new BookDaoImpl();
	}

	@Override
	public RentBook convertToObject(ResultSet rs) {
		try {
			int student_id=rs.getInt("student_id");
			Student student=this.studentDao.selectById(new Student(student_id));
			int book_id=rs.getInt("book_id");
			Book book=this.bookDao.selectById(new Book(book_id));
			return new RentBook(
					rs.getInt("id"),
					book,
					student,
					rs.getInt("qty"),
					rs.getInt("rent_day"),
					rs.getDate("rent_date"),
					rs.getDate("return_date"),
					Status.valueOf(rs.getString("status"))
					);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public void insert(RentBook rentBook) {
	    String query = "INSERT INTO rent_books (student_id, book_id, qty, rent_day, rent_date, return_date, status) VALUES (?, ?, ?, ?, ?, ?, CAST(? AS rentstatus))";

	    super.executeUpdate(query, 
	                        rentBook.getStudent().getId(),
	                        rentBook.getBook().getId(),
	                        rentBook.getQty(),
	                        rentBook.getRentDay(),
	                        rentBook.getRentDate(),
	                        rentBook.getReturnDate(),
	                        rentBook.getStatus().name()); 
	}



}
