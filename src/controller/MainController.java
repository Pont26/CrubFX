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
    private MenuItem bookReturnItem;

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
    private AnchorPane returnViewPane;
        
	    @FXML
	    public void initialize() {
	        studentPane.setVisible(false);
	        bookPane.setVisible(true);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	        bookReturnItem.setVisible(true);
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
	        	showReport();
	        }else if(event.getSource() == bookReturnItem) {
	        	showReturnForm();
	        }
	    }
	
	    private void showStudentForm() {
	    	studentPane.setVisible(true);
	        bookPane.setVisible(false);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	        returnViewPane.setVisible(false);

	    }
	
	    private void showBookForm() {
	    	studentPane.setVisible(false);
	        bookPane.setVisible(true);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	        returnViewPane.setVisible(false);
	    }
	    
	    private void showRentForm() {
	        studentPane.setVisible(false);
	        bookPane.setVisible(false);
	        rentPane.setVisible(true);
	        tvRentPane.setVisible(false);
	        returnViewPane.setVisible(false);
	
	    }
	    
	    private void showReport() {
	    	  studentPane.setVisible(false);
	          bookPane.setVisible(false);
	          rentPane.setVisible(false);
	          tvRentPane.setVisible(true);
	         returnViewPane.setVisible(false);
	    	
	    }
	   
	    private void showReturnForm() {
	        studentPane.setVisible(false);
	        bookPane.setVisible(false);
	        rentPane.setVisible(false);
	        tvRentPane.setVisible(false);
	        returnViewPane.setVisible(true); 	   

	}
}