package controller;

import java.io.IOException;
import java.util.Date;


import com.demoncrud.model.Book;
import com.demoncrud.model.RentBook;
import com.demoncrud.model.Student;
import com.demoncrud.service.BookService;
import com.demoncrud.service.RentBookService;
import com.demoncrud.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	private TextField tfStatus;
	@FXML
	private Button btnRent;

	

	
	
    @FXML
	public void initialize() {
    System.out.println("rent book initializing is alrady working");
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
				student,
				book,
				Integer.parseInt(tfQty.getText()),
				Integer.parseInt(tfRentDay.getText()),
				rentDate,
				null,
				status
				);
		rentBookService.saveRentBook(rentBook);
		showSuccessDialog("Sucessfully rented");
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
		tfStatus.clear();

	}

	public TextField getTfBookId() {
		return tfBookId;
	}

	public void setTfBookId(TextField tfBookId) {
		this.tfBookId = tfBookId;
	}

	public TextField getTfStudentId() {
		return tfStudentId;
	}

	public void setTfStudentId(TextField tfStudentId) {
		this.tfStudentId = tfStudentId;
	}

}
