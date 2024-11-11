package controller;


import java.util.Date;
import java.util.List;

import com.demoncrud.model.RentBook;
import com.demoncrud.model.RentBook.Status;
import com.demoncrud.service.RentBookService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RentViewTableController {
	private RentBookService rentBookService;
	

	//rent view table
	@FXML
	private TableView<RentBook> tvRent;
	@FXML
	private TableColumn<RentBook,Integer> colRentId;
	@FXML
	private TableColumn<RentBook,Integer> colStudentId;
	@FXML
	private TableColumn<RentBook,Integer> colBookId;
	@FXML
	private TableColumn<RentBook,Integer> colQty;
	@FXML
	private TableColumn<RentBook,Integer> colRentDay;
	@FXML
	private TableColumn<RentBook,Date> colRentDate;
	@FXML
	private TableColumn<RentBook,Date> colReturnDate;
	@FXML
	private TableColumn<RentBook,Status> colStatus;

	
	@FXML
	public void initialize() {
	this.rentBookService=new RentBookService();
	showRentBook();	
	}
	
	public void showRentBook() {
		List<RentBook> list = rentBookService.getAllRentBook();
		colRentId.setCellValueFactory(new PropertyValueFactory<RentBook, Integer>("id"));
		colStudentId.setCellValueFactory(new PropertyValueFactory<RentBook, Integer>("studentId"));
		colBookId.setCellValueFactory(new PropertyValueFactory<RentBook, Integer>("bookId"));
		colQty.setCellValueFactory(new PropertyValueFactory<RentBook, Integer>("qty"));
		colRentDay.setCellValueFactory(new PropertyValueFactory<RentBook, Integer>("rentDay"));
		colRentDate.setCellValueFactory(new PropertyValueFactory<RentBook, Date>("rentDate"));
		colReturnDate.setCellValueFactory(new PropertyValueFactory<RentBook, Date>("returnDate"));
		colStatus.setCellValueFactory(new PropertyValueFactory<RentBook, Status>("status"));
	    ObservableList<RentBook> observableList = FXCollections.observableArrayList(list);
	    tvRent.setItems(observableList);

	}
	
}

