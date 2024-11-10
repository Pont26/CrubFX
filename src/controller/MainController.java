package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML 
    private MenuItem bookItem;
    @FXML
    private MenuItem studentItem;
    @FXML
    private MenuItem rentItem;
    @FXML
    private MenuItem tvRentItem;
    @FXML
    private AnchorPane bookPane;
    @FXML
    private AnchorPane studentPane;
    @FXML
    private AnchorPane rentPane;
    @FXML
    private AnchorPane tvRentPane;
    
	    @FXML
	    public void initialize() {
	        studentPane.setVisible(false);
	        bookPane.setVisible(true);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	    }
	    
	    @FXML
	    public void menuItemEvent(ActionEvent event) {
	        if (event.getSource() == bookItem) {
	            showBookForm();
	        } else if (event.getSource() == studentItem) {
	            showStudentForm();
	        } else if (event.getSource() == rentItem) {
	            showRentForm();
	        }else if(event.getSource() == tvRentItem) {
	        	showRentTable();
	        }
	    }
	
	    private void showStudentForm() {
	    	studentPane.setVisible(true);
	        bookPane.setVisible(false);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	    }
	
	    private void showBookForm() {
	    	studentPane.setVisible(false);
	        bookPane.setVisible(true);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	    }
	    
	    private void showRentForm() {
	        studentPane.setVisible(false);
	        bookPane.setVisible(false);
	        rentPane.setVisible(true);
	        tvRentPane.setVisible(false);
	
	    }
	    
	    private void showRentTable() {
	    	  studentPane.setVisible(false);
	          bookPane.setVisible(false);
	          rentPane.setVisible(false);
	          tvRentPane.setVisible(true);
	    	
	    }
	}
