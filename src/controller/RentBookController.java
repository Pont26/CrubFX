package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.demoncrud.model.Book;
import com.demoncrud.model.RentBook;
import com.demoncrud.model.RentBook.Status;
import com.demoncrud.model.Student;
import com.demoncrud.service.BookService;
import com.demoncrud.service.RentBookService;
import com.demoncrud.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RentBookController {
	private RentBookService rentBookService;
	private StudentService studentService;
	private BookService bookService;
	//Rent pan
	@FXML
	private TextField tfBookId;
	@FXML
	private TextField tfStudentId;
	@FXML
	private TextField tfQty;
	@FXML
	private TextField tfRentDay;
	@FXML
	private TextField tfRentDate;
	@FXML
	private TextField tfReturnDate;
	@FXML
	private TextField tfStatus;
	@FXML
	private Button btnRent;
	@FXML
	private TableView<RentBook> tvRent;
	

	
	
    @FXML
	public void initialize() {
    System.out.print("Rent Book initializing is alrady working");
    this.rentBookService=new RentBookService();
    this.studentService=new StudentService();
    this.bookService=new BookService();
    Date rentDate=new Date();
    System.out.println(rentDate);
    //get Student Id from UI
    Student student=this.studentService.getStudentById(1);
    Book book=this.bookService.getBookById(2);
    System.out.println("student:" +student);
    System.out.println("book: " +book);
    RentBook.Status status = (Status.RENTED);

    
//    this.rentBookService.saveRentBook(new RentBook(book,student,5,5,rentDate,null,status));
    List<RentBook> rentBooks=this.rentBookService.getAllRentBook();
    for(RentBook rentBook:rentBooks) {
    	System.out.println(rentBook);
    }
    
    }
    
	public void btnEvent(ActionEvent event) throws IOException {
		
		}
	
	/*
    @FXML
	public void initialize() {
    System.out.println("Student initializing is alrady working");
    this.rentBookService=new RentBookService();
    }
    
	public void btnEvent(ActionEvent event) throws IOException {
		 insertRecord();
		 clearTextField();
		}
	
	private void insertRecord() {
		
	    this.bookService=new BookService();
	    int bookId = Integer.parseInt(tfBookId.getText());
        Book book=this.bookService.getBookById(bookId);
        
        this.studentService=new StudentService();
        int studentId = Integer.parseInt(tfStudentId.getText());
        Student student=this.studentService.getStudentById(studentId);
        Date rentDate=new Date();
        
        RentBook.Status status = RentBook.Status.valueOf(tfStatus.getText().toUpperCase());
   

		RentBook rentBook=new RentBook(
				book,
				student,
				Integer.parseInt(tfQty.getText()),
				Integer.parseInt(tfRentDay.getText()),
				rentDate,
				null,
				status
				);
		rentBookService.saveRentBook(rentBook);
	}
	
	private void showSuccessDialog(String message) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Success");
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
	public void clearTextField() {
		tfBookId.clear();
		tfStudentId.clear();
		tfQty.clear();
		tfRentDay.clear();
		tfRentDate.clear();
		tfReturnDate.clear();
		tfStatus.clear();

	}
	*/


}
