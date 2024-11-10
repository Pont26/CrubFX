package controller;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RentViewController {
	

	//rent view table
	@FXML
	private TableView<?> tvRent;
	@FXML
	private TableColumn<?,?> colRentId;
	@FXML
	private TableColumn<?,?> colBookId;
	@FXML
	private TableColumn<?,?> colStudentId;
	@FXML
	private TableColumn<?,?> colQty;
	@FXML
	private TableColumn<?,?> colRentDate;
	@FXML
	private TableColumn<?,?> colDueDate;
	@FXML
	private TableColumn<?,?> colRentDays;
	@FXML
	private TableColumn<?,?> colReturn;

}
