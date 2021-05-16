package controllers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.CourseNameExistsException;
import exceptions.EmptyFieldException;
import services.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static services.Provider.nameCourseProvider;
import static services.Provider.priceProvider;
import static services.Provider.descriptionProvider;
import static services.Provider.contentProvider;


public class InformationCourseProviderController implements Initializable {

    private String oldName;

    @FXML
    public Label closeButton;
    public TextField nameField;
    public TextField priceField;
    public TextArea descriptionField;
    public TextArea contentField;
    public Label homePageButton;
    public Label emptyFieldMessage;
    public Button deleteButton;
    public Button editButton;
    public Button seeCommentsProviderButton;

    @FXML
    public void handleSeeCommentsProviderButton(MouseEvent event) throws IOException {
        Parent commentPage = FXMLLoader.load(getClass().getResource("SeeCommentsProvider.fxml"));
        Scene commentScene = new Scene(commentPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(commentScene);
        appStage.show();
    }

    @FXML
    public void handleEditButton(MouseEvent event) throws IOException, EmptyFieldException {
        try{
            Provider.editCourse(oldName, nameField.getText(), priceField.getText(), descriptionField.getText(), contentField.getText());
            Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageProvider.fxml"));
            Scene HomeScene = new Scene(HomePage);
            Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(HomeScene);
            appStage.show();
        } catch(EmptyFieldException | CourseNameExistsException e)
        {
            emptyFieldMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleDeleteButton(MouseEvent event) throws IOException {
        Provider.deleteCourse(nameField.getText());
        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageProvider.fxml"));
        Scene HomeScene = new Scene(HomePage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(HomeScene);
        appStage.show();
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
        nameField.setText(nameCourseProvider);
        priceField.setText(priceProvider);
        descriptionField.setText(descriptionProvider);
        contentField.setText(contentProvider);
        oldName = nameField.getText();
    }
}
