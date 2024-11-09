package com.demoncrud.model;

import java.util.ArrayList;
import java.util.List;

import annotation.Column;
import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

@Table(name="book")
public class Book {
	@Id(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="author")
	private String author;
	@Column(name="year")
	private int year;
	@Column(name="pages")
	private int pages;

	
	public Book(int id) {
		this.id=id;
	}
	
	public Book(int id, String title, String author, int year, int pages) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
	}
	public Book( String title, String author, int year, int pages) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
	}
	
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
	

}
