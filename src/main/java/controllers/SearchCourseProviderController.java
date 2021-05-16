package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.CourseNameIncorrectException;
import services.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCourseProviderController implements Initializable {

    @FXML
    public Label closeButton;
    public Label homePageButton;
    public Label emptyFieldMessage;
    public TextField searchCourseName;
    public Button searchButton;
    public Label courseNameIncorrect;

    @FXML
    public void handleSearchButton(MouseEvent event) throws IOException {
        try{
            if(Provider.searchCourseProvider(searchCourseName.getText()))
            {
                Parent informationPage = FXMLLoader.load(getClass().getResource("InformationCourseProvider.fxml"));
                Scene informationScene = new Scene(informationPage);
                Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                appStage.setScene(informationScene);
                appStage.show();
            }
            else
                throw new CourseNameIncorrectException();
        } catch(CourseNameIncorrectException e)
        {
            courseNameIncorrect.setText(e.getMessage());
        }
    }

    @FXML
    public void handleHomePageButton(MouseEvent event) throws IOException
    {

        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageProvider.fxml"));
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
