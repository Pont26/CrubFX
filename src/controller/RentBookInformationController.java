package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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

public class RentBookInformationController {
    private RentBookService rentBookServie;
    private StudentService studentService;
    private BookService bookService;
    public int studentId = 0;
    private  Date rentDate;
    private int rentDay;

    @FXML
    private TextField tfStudentId;
    @FXML
    private TextField tfBookId;
    @FXML
    private TextField tfReturnDate;
    @FXML
    private TextField tfRentDate;
    @FXML
    private TextField tfRentDay;
    @FXML
    private TextField tfLateDay;
    @FXML
    private TextField tfStatus;
    @FXML
    private TextField tfBookQty;
    @FXML
    private Button submitBtn;

    @FXML
    public void initialize() {
        rentBookServie = new RentBookService();
        studentService = new StudentService();
        bookService = new BookService();
    }
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public void setStudentId(int studentId) {
        this.studentId = studentId;
        System.out.println("Setting studentId: " + studentId);
        showBook();
    }
    

    public void showBook() {
        tfStudentId.setText(String.valueOf(studentId));
        RentBook rentBook = rentBookServie.getRentBookByStudentId(studentId);
        tfBookId.setText(String.valueOf(rentBook.getBook().getId()));

        tfRentDate.setText(dateFormat.format(rentBook.getRentDate()));
        tfRentDay.setText(String.valueOf(rentBook.getRentDay()));
        tfStatus.setText(rentBook.getStatus().toString());
        tfBookQty.setText(String.valueOf(rentBook.getQty()));

        rentDay = rentBook.getRentDay();
        calculateReturnDay();
        calculateLateDay();
    }


    public void btnEvent(ActionEvent event) throws IOException {
    	updateRentBook();
    }

    private void updateRentBook() {
    	   try {
               int studentId = Integer.parseInt(tfStudentId.getText());
               int bookId = Integer.parseInt(tfBookId.getText());
               Student student = this.studentService.getStudentById(studentId);
               Book book = this.bookService.getBookById(bookId);

               Date rentDate = dateFormat.parse(tfRentDate.getText());
               Date returnDate = dateFormat.parse(tfReturnDate.getText());

                rentDay = Integer.parseInt(tfRentDay.getText());

               RentBook.Status status = RentBook.Status.valueOf(tfStatus.getText());

               int bookQty = Integer.parseInt(tfBookQty.getText());

               RentBook rentBook = new RentBook(student, book, bookQty, rentDay, rentDate, returnDate, status);
               rentBookServie.updateRentBook(rentBook);
               displayMessage(Alert.AlertType.CONFIRMATION,"Return Book Sucessed");
           } catch (ParseException e) {
               e.printStackTrace();
           }
    	
    }
    public void calculateLateDay() {
        try {
            // Today's date as the actual return date
            Date actualReturnDate = new Date();
            
            // calculate the data that should return
            String calculatedReturnDateStr = tfReturnDate.getText();
            Date calculatedReturnDate = dateFormat.parse(calculatedReturnDateStr);

            // Calculate the difference 
            long differenceInMillis = actualReturnDate.getTime() - calculatedReturnDate.getTime();
            
            // Convert difference to days
            long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);
            
            if (differenceInDays > 0) {
                tfLateDay.setText(String.valueOf(differenceInDays));
            } else {
                tfLateDay.setText("0");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    
    public void calculateReturnDay() {
        try {
        	  Date rentDate = dateFormat.parse(tfRentDate.getText());
              Calendar calendar = Calendar.getInstance();
              calendar.setTime(rentDate);
              calendar.add(Calendar.DAY_OF_YEAR, rentDay); 

      
              tfReturnDate.setText(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            displayMessage(Alert.AlertType.ERROR, "Error calculating return date");
            e.printStackTrace();
        }
    }

    
    private void displayMessage(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Deletion Status");
        alert.setHeaderText("Delete Message");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
