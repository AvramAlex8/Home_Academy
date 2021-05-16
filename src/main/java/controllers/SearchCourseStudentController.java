package controllers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.CourseNameIncorrectException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCourseStudentController implements Initializable {
    @FXML
    public Label closeButton;
    public Button searchButton;
    public TextField searchNameField;
    public Label homePageButton;
    public Label courseNameIncorrectField;

    @FXML
    public void handleSearchButton(MouseEvent event) throws IOException {
        try
        {
            if(Student.searchCourseStudent(searchNameField.getText()))
            {
                Parent informationPage = FXMLLoader.load(getClass().getResource("InformationCourseStudent.fxml"));
                Scene informationScene = new Scene(informationPage);
                Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                appStage.setScene(informationScene);
                appStage.show();
            }
            else
                throw new CourseNameIncorrectException();
        } catch(CourseNameIncorrectException e)
        {
            courseNameIncorrectField.setText(e.getMessage());
        }
    }

    public void handleHomePageButton(MouseEvent event) throws IOException
    {

        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageStudent.fxml"));
        Scene HomeScene = new Scene(HomePage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(HomeScene);
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
