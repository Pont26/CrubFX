package controller;

import java.util.Date;
import java.util.Optional;

import com.demoncrud.model.Book;
import com.demoncrud.model.RentBook;
import com.demoncrud.model.RentBook.Status;
import com.demoncrud.model.Student;
import com.demoncrud.service.BookService;
import com.demoncrud.service.RentBookService;
import com.demoncrud.service.StudentService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private Button btnRent;
	@FXML
	private TableView<RentBook> tvRent;
	
	@FXML
	public void showDialog(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Hello");
	    alert.setContentText("This is the alert");
	    Optional<ButtonType> result = alert.showAndWait();
	}
	
	/*
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
    Book book=this.bookService.getBookById(25);
    System.out.println(student);
    System.out.println(book);
    
    this.rentBookService.saveRentBook(new RentBook(student,book,5,5,rentDate,null,Status.RENTED));
    
    }
    */


}
