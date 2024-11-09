package controller;

import java.io.IOException;
import java.util.List;
import com.demoncrud.model.Book;
import com.demoncrud.service.BookService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class BookController {
	private BookService bookService;

	@FXML
	private TextField tfTitle;
	@FXML
	private TextField tfAuthor;
	@FXML
	private TextField tfYear;
	@FXML
	private TextField tfPages;
	@FXML
	private TableView<Book> tvBook;
	@FXML
	private TableColumn<Book,Integer> colId;
	@FXML
	private TableColumn<Book,String> colTitle;
	@FXML
	private TableColumn<Book,String> colAuthor;
	@FXML
	private TableColumn<Book,Integer> colYear;
	@FXML
	private TableColumn<Book,Integer> colPages;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;	
    @FXML
	public void initialize() {
    System.out.println(" Book initializing is alrady working");
    this.bookService=new BookService();
    showBook();	
    }

		public void btnEvent(ActionEvent event) throws IOException {
		  if(event.getSource() == (btnInsert)) {
			  insertRecord();
		  }else if(event.getSource() == btnUpdate){
			  updateRecord();
		  }else if(event.getSource() == btnDelete) {
			  deleteRecord();
		  }
		  clearTextField();
		}


	
		public void showBook() {
			List<Book> list = bookService.getAllBook();
			colId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
			colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
			colAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
			colYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
			colPages.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pages"));
		    ObservableList<Book> observableList = FXCollections.observableArrayList(list);
		    tvBook.setItems(observableList);
	
		}
	
		private void insertRecord() {
			Book book=new Book(
					tfTitle.getText(),
					 tfAuthor.getText(),
					Integer.parseInt(tfYear.getText()),
					Integer.parseInt(tfPages.getText())
					);
			bookService.saveBook(book);
			showBook();
		}
	
		private void updateRecord() {
			  Book selectedBook = tvBook.getSelectionModel().getSelectedItem();
			  Book book=new Book(
					selectedBook.getId(),
					tfTitle.getText(),
					 tfAuthor.getText(),
					Integer.parseInt(tfYear.getText()),
					Integer.parseInt(tfPages.getText())
					);
			bookService.update(book);
		    showBook(); 
		}
	
		private void deleteRecord() {
			  Book selectedBook = tvBook.getSelectionModel().getSelectedItem();
			    if (selectedBook != null) {
			        try {
			            boolean deleteStatus = bookService.delete(selectedBook.getId());
			            if (deleteStatus) {
			                displayMessage(Alert.AlertType.INFORMATION, "Record deleted successfully.");
			            } else {
			                displayMessage(Alert.AlertType.ERROR, "Failed to delete the record.");
			            }
			        } catch (Exception e) {
			            displayMessage(Alert.AlertType.ERROR, "An error occurred while deleting the record.");
			        }
			        showBook();
			    } else {
			        displayMessage(Alert.AlertType.ERROR, "No book selected for deletion.");
			    }
			    showBook();
		}
	
		private void displayMessage(Alert.AlertType alertType, String message) {
		    Alert alert = new Alert(alertType);
		    alert.setTitle("Deletion Status");
		    alert.setHeaderText("Delete Message"); 
		    alert.setContentText(message);  
		    alert.showAndWait(); 
		}
	
		
	
			
		public void clearTextField() {
			    tfTitle.clear();
			    tfAuthor.clear();
			    tfYear.clear();
			    tfPages.clear();
		}
		
		@FXML
			private void handleMouseAction(MouseEvent event) {
		    Book book = tvBook.getSelectionModel().getSelectedItem();
		    if(book != null) {
		   tfTitle.setText(book.getTitle());
		   tfAuthor.setText(book.getAuthor());
		   tfYear.setText(""+book.getYear());
		   tfPages.setText(""+book.getPages());
		    }
		   
		}
			
	}
