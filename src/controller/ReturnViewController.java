package controller;

import java.io.IOException;

import com.demoncrud.service.BookService;
import com.demoncrud.service.StudentService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReturnViewController {
    private StudentService studentService;
    private BookService bookService;
    private int studentId;

    @FXML
    private TextField tfStudentId;
    @FXML
    private TextField tfBookId;
    @FXML
    private Button btnReturn;

    @FXML
    public void initialize() {
        studentService = new StudentService();
        bookService = new BookService();
    }

    public void btnEvent(ActionEvent event) throws IOException {
        try {
            int textstudentId = Integer.parseInt(tfStudentId.getText());
            int textbookId = Integer.parseInt(tfBookId.getText());
            studentId = studentService.getStudentById(textstudentId).getId();

            if (studentId > 0) {
                int bookId = bookService.getBookById(textbookId).getId();
                if (bookId > 0) {
                    rentBookInformation(event);
                } else {
                    displayMessage(Alert.AlertType.WARNING, "This Book Id does not exist");
                }
            } else {
                displayMessage(Alert.AlertType.WARNING, "This Student Id does not exist");
            }
        } catch (IndexOutOfBoundsException e) {
            displayMessage(Alert.AlertType.WARNING, "This Id does not exist");
        }
    }

    private void rentBookInformation(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/RentBookInformation.fxml"));
            Parent root = loader.load();

            RentBookInformationController rentBookController = loader.getController();
            rentBookController.setStudentId(studentId);

            System.out.println("Setting studentId to: " + studentId);
            Scene scene = new Scene(root, 500, 600);
            primaryStage.setScene(scene);
            primaryStage.show();

          // Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
          // currentStage.close();
          
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("error is here");
        }
    }

    private void displayMessage(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Error");
        alert.setHeaderText("Warning Message");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
