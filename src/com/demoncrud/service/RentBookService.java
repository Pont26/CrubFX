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
		rentBookDao.updateRentBook(rentBook);
	}
	public List<RentBook> getAllRentBook(){
		return rentBookDao.selectAll();
	}
    public void saveRentBook(RentBook rentBook) {
    	this.rentBookDao.insert(rentBook);
    }
    public RentBook getRentBookById(int id) {
        RentBook rentBook = new RentBook(id);
        RentBook result = this.rentBookDao.selectById(rentBook);
        if (result == null) {
            System.out.println("No RentBook found for id: " + id);
        } else {
            System.out.println("RentBook found: " + result);
        }
        return result;
    }

    public RentBook getRentBookByStudentId(int studentId) {

           RentBook result = this.rentBookDao.getRentBookByStudentId(studentId);
           if (result == null) {
               System.out.println("No student found in RentBook: " + studentId);
           } else {
               System.out.println("Student id found in RentBook : " + result);
           }
           return result;
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
