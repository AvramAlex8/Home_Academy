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

public class HomePageProviderController implements Initializable {

    @FXML
    public Label closeButton;

    @FXML
    public Button createCourse;

    @FXML
    public Button searchCourses;

    @FXML
    public void handleCreateCourseButton(MouseEvent event) throws IOException {
        Parent createPage = FXMLLoader.load(getClass().getResource("CreateCourse.fxml"));
        Scene createScene = new Scene(createPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(createScene);
        appStage.show();
    }

    @FXML
    public void handleSearchCoursesButton(MouseEvent event) throws IOException {
        Parent searchPage = FXMLLoader.load(getClass().getResource("SearchCourseProvider.fxml"));
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
