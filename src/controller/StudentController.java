package controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.demoncrud.model.Student;
import com.demoncrud.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
public class StudentController {
	private StudentService studentService;


	@FXML
	private TextField tfName;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfAddress;

	@FXML
	private TableView<Student> tvStudent;
	@FXML
	private TableColumn<Student,Integer> colId1;
	@FXML
	private TableColumn<Student,String> colName;
	@FXML
	private TableColumn<Student,String> colEmail;
	@FXML
	private TableColumn<Student,String> colAddress;
	
	@FXML
	private Button btnInsert1;
	@FXML
	private Button btnUpdate1;
	@FXML
	private Button btnDelete1;

		    @FXML
			public void initialize() {
		    System.out.println("Student initializing is alrady working");
		    this.studentService=new StudentService();
		    showStudent();	
		    }
		
			public void btnEvent(ActionEvent event) throws IOException {
			  if(event.getSource() == (btnInsert1)) {
				  insertRecord();
			  }else if(event.getSource() == btnUpdate1){
				  updateRecord();
			  }else if(event.getSource() == btnDelete1) {
				  deleteRecord();
			  }
			  clearTextField();
			}
		
			@FXML
			public void showDialog(ActionEvent event) {
			    Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Hello");
			    alert.setContentText("This is the alert");
			    Optional<ButtonType> result = alert.showAndWait();
			}
		
		
			public void showStudent() {
				List<Student> list = studentService.getAllStudent();
				colId1.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
				colName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
				colEmail.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
				colAddress.setCellValueFactory(new PropertyValueFactory<Student,String>("address"));
			    ObservableList<Student> observableList = FXCollections.observableArrayList(list);
			    tvStudent.setItems(observableList);
		
			}
			
			private void insertRecord() {
				Student student=new Student(
						tfName.getText(),
						 tfEmail.getText(),
						tfAddress.getText()
						);
				studentService.saveStudent(student);
				showStudent();
			}
			
			private void updateRecord() {
			    Student selectedId = tvStudent.getSelectionModel().getSelectedItem();
				Student student=new Student(
						selectedId.getId(),
						tfName.getText(),
						 tfEmail.getText(),
						tfAddress.getText()
						);
				studentService.update(student);
				showStudent(); 
			}
			
			private void deleteRecord() {
				 Student selectedStudent = tvStudent.getSelectionModel().getSelectedItem();
				    if (selectedStudent != null) {
				        try {
				            boolean deleteStatus = studentService.delete(selectedStudent.getId());
				            if (deleteStatus) {
				                displayMessage(Alert.AlertType.INFORMATION, "Record deleted successfully.");
				            } else {
				                displayMessage(Alert.AlertType.ERROR, "Failed to delete the record.");
				            }
				        } catch (Exception e) {
				            displayMessage(Alert.AlertType.ERROR, "An error occurred while deleting the record.");
				        }
				        showStudent();
				    } else {
				        displayMessage(Alert.AlertType.ERROR, "No Student selected for deletion.");
				    }
				    showStudent();
			}
			
		
			private void displayMessage(Alert.AlertType alertType, String message) {
			    Alert alert = new Alert(alertType);
			    alert.setTitle("Deletion Status");
			    alert.setHeaderText("Delete Message"); 
			    alert.setContentText(message);  
			    alert.showAndWait(); 
			}
			
		
				
			public void clearTextField() {
				    tfName.clear();
				    tfEmail.clear();
				    tfAddress.clear();
			}
			
			@FXML
				private void handleMouseAction2(MouseEvent event) {
			    Student student = tvStudent.getSelectionModel().getSelectedItem();
			    if(student != null) {
			   tfName.setText(student.getName());
			   tfEmail.setText(student.getEmail());
			   tfAddress.setText(""+student.getAddress());
			    }
			   
			}
		
		}
