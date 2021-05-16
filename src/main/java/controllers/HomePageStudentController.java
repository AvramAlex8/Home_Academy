package controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageStudentController implements Initializable {

    @FXML
    public Label closeButton;

    @FXML
    public Button viewCourses;

    @FXML
    public Button searchCourses;

    @FXML
    public void handleViewCoursesButton(MouseEvent event) throws IOException {
        Parent viewPage = FXMLLoader.load(getClass().getResource("ViewCourseStudent.fxml"));
        Scene viewScene = new Scene(viewPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(viewScene);
        appStage.show();
    }

    @FXML
    public void handleSearchCoursesButton(MouseEvent event) throws IOException {
        Parent searchPage = FXMLLoader.load(getClass().getResource("SearchCourseStudent.fxml"));
        Scene searchScene = new Scene(searchPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(searchScene);
        appStage.show();
    }

    @FXML
    public void handleCloseButtonAction(MouseEvent event)
    {
        if(event.getSource() == closeButton)
        {
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
