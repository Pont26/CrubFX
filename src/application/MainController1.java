package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.demoncrud.dao.BookDao;
import com.demoncrud.dao.ConnectionDao;
import com.demoncrud.dao.GeneralDao;
import com.demoncrud.model.Books;
import com.demoncrud.view.BookTableView;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MainController1 {
	private BookDao bookDao;
	public ConnectionDao connectionDao;

	
	@FXML
	private Label label;
	@FXML
	private TextField tfId;
	@FXML
	private TextField tfTitle;
	@FXML
	private TextField tfAuthor;
	@FXML
	private TextField tfYear;
	@FXML
	private TextField tfPages;
	@FXML
	private TableView<Books> tvBook;
	@FXML
	private TableColumn<Books,Integer> colId;
	@FXML
	private TableColumn<Books,String> colTitle;
	@FXML
	private TableColumn<Books,String> colAuthor;
	@FXML
	private TableColumn<Books,Integer> colYear;
	@FXML
	private TableColumn<Books,Integer> colPages;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public TextField getTfId() {
		return tfId;
	}
	public void setTfId(TextField tfId) {
		this.tfId = tfId;
	}
	public TextField getTfTitle() {
		return tfTitle;
	}
	public void setTfTitle(TextField tfTitle) {
		this.tfTitle = tfTitle;
	}
	public TextField getTfAuthor() {
		return tfAuthor;
	}
	public void setTfAuthor(TextField tfAuthor) {
		this.tfAuthor = tfAuthor;
	}
	public TextField getTfYear() {
		return tfYear;
	}
	public void setTfYear(TextField tfYear) {
		this.tfYear = tfYear;
	}
	public TextField getTfPages() {
		return tfPages;
	}
	public void setTfPages(TextField tfPages) {
		this.tfPages = tfPages;
	}
	public TableView<Books> getTvBooks() {
		return tvBook;
	}
	public void setTvBooks(TableView<Books> tvBooks) {
		this.tvBook = tvBooks;
	}
	public TableColumn<Books, Integer> getColId() {
		return colId;
	}
	public void setColId(TableColumn<Books, Integer> colId) {
		this.colId = colId;
	}
	public TableColumn<Books, String> getColTitle() {
		return colTitle;
	}
	public void setColTitle(TableColumn<Books, String> colTitle) {
		this.colTitle = colTitle;
	}
	public TableColumn<Books, String> getColAuthor() {
		return colAuthor;
	}
	public void setColAuthor(TableColumn<Books, String> colAuthor) {
		this.colAuthor = colAuthor;
	}
	public TableColumn<Books, Integer> getColYear() {
		return colYear;
	}
	public void setColYear(TableColumn<Books, Integer> colYear) {
		this.colYear = colYear;
	}
	public TableColumn<Books, Integer> getColPages() {
		return colPages;
	}
	public void setColPages(TableColumn<Books, Integer> colPages) {
		this.colPages = colPages;
	}
	public Button getBtnInsert() {
		return btnInsert;
	}
	public void setBtnInsert(Button btnInsert) {
		this.btnInsert = btnInsert;
	}
	public Button getBtnUpdate() {
		return btnUpdate;
	}
	public void setBtnUpdate(Button btnUpdate) {
		this.btnUpdate = btnUpdate;
	}
	public Button getBtnDelete() {
		return btnDelete;
	}
	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void initialize() {
  showBook();	
    }



	public void btnEvent(ActionEvent event) throws IOException {
	    bookDao = new BookDao();
	  if(event.getSource() == (btnInsert)) {
		  bookDao.insertRecord();
			clearTextField();
	  }else if(event.getSource() == btnUpdate){
		  bookDao.updateRecord();
			clearTextField();		 
	  }else if(event.getSource() == btnDelete) {
		  bookDao.deleteRecord();
		 
	  }
	}


	public void showBook() {
	    bookDao = new BookDao();
		ObservableList<Books> list = bookDao.selectAll();
		colId.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
		colTitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
		colYear.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
		colPages.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));
		tvBook.setItems(list);

	}
	
	private void insertRecord() {
		bookDao =new BookDao();
	    String query = "INSERT INTO books (id, title, author, year, pages) VALUES (" +
                tfId.getText() + ", '" + tfTitle.getText() + 
                "', '" + tfAuthor.getText() + 
                "', " + tfYear.getText() + 
                ", " + tfPages.getText() + ")";
	   executeUpdate(query);
		  showBook();
	}
	
	private void updateRecord() {
	    String query = "UPDATE books SET title = '" + tfTitle.getText() + 
	                   "', author = '" + tfAuthor.getText() + 
	                   "', year = " + tfYear.getText() + 
	                   ", pages = " + tfPages.getText() + 
	                   " WHERE id = " + tfId.getText();
		   executeUpdate(query);
	    showBook(); 
	}

	private void deleteRecord() {
		String query = "DELETE FROM books WHERE id =  "+ tfId.getText();
		executeUpdate(query);
		showBook();
		
	}
	
	private void executeUpdate(String query) {
	 connectionDao = new ConnectionDao();

	try {
		Connection connection = connectionDao.getConnectionWithDao();
		Statement st = connection.createStatement();
		st.executeUpdate(query);		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


	}
	
	
	public void clearTextField() {
		 tfId.clear();
		    tfTitle.clear();
		    tfAuthor.clear();
		    tfYear.clear();
		    tfPages.clear();
	}
	
	@FXML
		private void handleMouseAction(MouseEvent event) {
	    Books book = tvBook.getSelectionModel().getSelectedItem();
	    if(book != null) {
	   tfId.setText(""+(book.getId()));
	   tfTitle.setText(book.getTitle());
	   tfAuthor.setText(book.getAuthor());
	   tfYear.setText(""+book.getYear());
	   tfPages.setText(""+book.getPages());
	    }
	   
	}

	
}
