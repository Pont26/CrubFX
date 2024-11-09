package com.demoncrud.model;

import java.util.Date;

import annotation.Column;
import annotation.Id;
import annotation.Table;

@Table(name = "rent_books")
public class RentBook {
	@Id(name ="id")
	private int id;
	@Column(name  = "student_id")
    private Student student;
	@Column(name = "book_id")
    private Book book;
	@Column(name = "qty")
    private int qty;
	@Column(name = "rent_day")
    private int rentDay;
	@Column(name = "rent_date")
    private Date rentDate;
	@Column(name = "return_date")
    private Date returnDate;
	@Column(name = "status")
    private Status status;

    public enum Status {
        RENTED,
        RETURNED,
        OVERDUE
    }
    
    public RentBook(int id, Student student, Book book, int qty, int rentDay, Date rentDate, Date returnDate, Status status) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.qty = qty;
        this.rentDay = rentDay;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    
    public RentBook(Student student, Book book, int qty, int rentDay, Date rentDate, Date returnDate, Status status) {
        this.student = student;
        this.book = book;
        this.qty = qty;
        this.rentDay = rentDay;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
    }
	public RentBook(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getRentDay() {
		return rentDay;
	}

	public void setRentDay(int rentDay) {
		this.rentDay = rentDay;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
