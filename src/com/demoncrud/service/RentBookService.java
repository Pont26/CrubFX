package com.demoncrud.service;

import java.util.List;

import com.demoncrud.dao.impl.RentBookDaoImpl;
import com.demoncrud.model.RentBook;

public class RentBookService {
	private RentBookDaoImpl rentBookDao;
	
	public RentBookService() {
		this.rentBookDao = new RentBookDaoImpl();
	}
	
	public void updateRentBook(RentBook rentBook) {
		rentBookDao.update(rentBook,"id");
	}
	public List<RentBook> getAllRentBook(){
		return rentBookDao.selectAll();
	}
    public void saveRentBook(RentBook rentBook) {
    	this.rentBookDao.insert(rentBook);
    }
    public boolean deleteRentBook(int id) {
    	RentBook rentBook=new RentBook(id);
    	rentBook=this.rentBookDao.selectById(rentBook);
        System.out.println("book="+rentBook);
        if(rentBook!=null) {
        	this.rentBookDao.delete(rentBook);
        	return true;
        }else {
        	return false;
        }
    	
    }

}
