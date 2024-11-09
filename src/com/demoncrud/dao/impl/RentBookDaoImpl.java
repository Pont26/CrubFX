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
					student,
					book,
					rs.getInt("qty"),
					rs.getInt("rentDay"),
					rs.getDate("rentDate"),
					rs.getDate("returnDate"),
					Status.valueOf(rs.getString("status"))
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public void insert(RentBook rentBook) {
		String querry="insert into rent_books (student_id,book_id,qty,rent_day,rent_date,return_date,status) values(?,?,?,?,?,?,?)";
		super.executeUpdate(querry, rentBook.getStudent().getId(),
				                    rentBook.getBook().getId(),
				                    rentBook.getQty(),
				                    rentBook.getRentDay(),
				                    rentBook.getRentDate(),
				                    rentBook.getRentDate(),
				                    rentBook.getStatus());
	}

}
